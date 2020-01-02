package alioth.mrsheng.space.business;

import alioth.mrsheng.space.Environment;
import alioth.mrsheng.space.core.ioc.FactoryList;
import alioth.mrsheng.space.core.utils.JacksonUtils;
import alioth.mrsheng.space.core.wxaes.WXBizMsgCrypt;
import alioth.mrsheng.space.domain.message.Message;
import alioth.mrsheng.space.domain.message.MessageType;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import alioth.mrsheng.space.business.event.IEventBusiness;
import alioth.mrsheng.space.business.message.IMessageBusiness;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class WeChatBusinessImpl implements IWeChatBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatBusinessImpl.class);

    // 默认响应字符串
    private static final String SUCCESS_RESULT = "success";

    /**
     * 直接提取消息中两个字符串中间部分作为消息类型
     * 这样的话就可以减少一次反序列化的操作了
     */
    private static final String MESSAGE_TYPE_START = "<MsgType><![CDATA[";
    private static final String MESSAGE_TYPE_END = "]]></MsgType>";

    /**
     * 注入了所有实现了 IMessageBusiness 的实例
     */
    @Resource
    private FactoryList<IMessageBusiness, String> messageBusinessFactoryList;

    /**
     * 注入了所有实现了 IEventBusiness 的实例
     */
    @Resource
    private List<IEventBusiness> eventBusinessList;

    @Resource
    private WXBizMsgCrypt wxBizMsgCrypt;

    @Override
    public boolean check(String signature, String timestamp, String nonce) {
        boolean pass = false;
        try {

            // 1.将token、timestamp、nonce进行字典序排序
            String[] arr = new String[]{Environment.TOKEN, timestamp, nonce};
            Arrays.sort(arr);

            // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
            StringBuilder content = new StringBuilder();
            for (String anArr : arr) {
                content.append(anArr);
            }
            String tmpStr = DigestUtils.sha1Hex(content.toString());

            // 3.将sha1加密后的字符串可与signature对比
            pass = tmpStr.equals(signature);
        } catch (Exception e) {
            LOGGER.error("check signature error:", e);
        }
        return pass;
    }

    @Override
    public String dispatch(final String xmlString, String signature, String timestamp, String nonce) {
        try {

            // 是否采用了加密模式或者兼容模式
            boolean encrypt = false;
            if (StringUtils.isNotBlank(signature) && StringUtils.isNotBlank(timestamp) && StringUtils.isNotBlank(nonce)) {
                encrypt = true;
            }

            // 去除xml报文中的空格
            String realClearXml = xmlString.replace(" ", "");

            // 如果加密模式则解密
            if (encrypt) {
                realClearXml = wxBizMsgCrypt.decryptMsg(signature, timestamp, nonce, realClearXml);
            }

            // 直接提取MessageType
            String messageType = realClearXml.substring(realClearXml.indexOf(MESSAGE_TYPE_START) + 18, realClearXml.indexOf(MESSAGE_TYPE_END));

            // 消息处理结果
            Message resultMessage;

            // 根据消息类型区分处理器
            if (MessageType.EVENT.equals(messageType)) {
                resultMessage = eventMessageHandler(realClearXml);
            } else {
                resultMessage = commonMessageHandler(messageType, realClearXml);
            }

            if (resultMessage != null) {
                // 根据加密与否响应的xml格式报文
                String resultXml = JacksonUtils.beanToXml(resultMessage);
                return encrypt ? wxBizMsgCrypt.encryptMsg(resultXml, timestamp, nonce) : resultXml;
            }
        } catch (Exception e) {
            LOGGER.error("dispatch xml message error:", e);
        }

        // 无论如何都应该返回结果以避免微信服务器重试
        return SUCCESS_RESULT;
    }

    /**
     * 普通消息处理
     * 公众号输入的内容均属于普通消息
     */
    private Message commonMessageHandler(String messageType, String realClearXml) {

        LOGGER.info("[WeChat] WeChatBusiness start handle message {}-{}", messageType, realClearXml);

        // 先找匹配的实现类
        IMessageBusiness business = messageBusinessFactoryList.getBean(messageType);

        return (null == business) ? null : business.handle(realClearXml);
    }

    /**
     * 事件消息处理
     * 一般指的是菜单点击事件消息
     */
    private Message eventMessageHandler(String realClearXml) {

        // 通知所有的实现类,由实现类中的match()方法判断是否需要处理
        for (IEventBusiness business : eventBusinessList) {

            // 理论上只有一个实现类会返回true
            if (business.match(realClearXml)) {
                return business.handle(realClearXml);
            }
        }
        return null;
    }
}
