package alioth.mrsheng.space.service.wechat.impl;

import alioth.mrsheng.space.core.ErrorCodeMessage;
import alioth.mrsheng.space.core.JacksonUtils;
import alioth.mrsheng.space.core.ioc.FactoryList;
import alioth.mrsheng.space.core.wxaes.WXBizMsgCrypt;
import alioth.mrsheng.space.domain.wechat.message.Message;
import alioth.mrsheng.space.domain.wechat.message.MessageType;
import alioth.mrsheng.space.service.wechat.IEventService;
import alioth.mrsheng.space.service.wechat.IMessageService;
import alioth.mrsheng.space.service.wechat.IWeChatService;
import alioth.mrsheng.space.service.wechat.MessageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WeChatBusinessImpl implements IWeChatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatBusinessImpl.class);

    @Resource
    private FactoryList<IMessageService, String> messageServiceList;

    @Resource
    private FactoryList<IEventService, String> eventServiceList;

    @Resource
    private WXBizMsgCrypt wxBizMsgCrypt;

    @Override
    public boolean check(String signature, String timestamp, String nonce) {
        try {
            return wxBizMsgCrypt.verifyToken(signature, timestamp, nonce);
        } catch (Exception e) {
            LOGGER.error("signature check error:", e);
        }
        return false;
    }

    @Override
    public String dispatch(final String xmlString, String signature, String timestamp, String nonce) {
        try {

            // 去除xml报文中的空格
            String realClearXml = xmlString.replace(" ", "");

            // 判断是否为加密模式
            boolean encrypt = MessageFactory.isEncryptMessage(realClearXml);
            if (encrypt) {
                realClearXml = wxBizMsgCrypt.decryptMsg(signature, timestamp, nonce, realClearXml);
            }
            LOGGER.info("dispatch plaintext message:\n{}", realClearXml);

            // 根据消息类型区分处理器并获取处理结果
            String messageType = MessageFactory.getMessageType(realClearXml);
            Message resultMessage;
            if (MessageType.EVENT.equals(messageType)) {
                IEventService eventService = eventServiceList.getBean(MessageFactory.getEventType(realClearXml));
                resultMessage = eventService == null ? null : eventService.handle(realClearXml);
            } else {
                IMessageService messageService = messageServiceList.getBean(messageType);
                resultMessage = messageService == null ? null : messageService.handle(realClearXml);
            }

            // 消息处理器正确返回了响应,则根据加密模式返回消息结果
            if (resultMessage != null) {
                String resultXml = JacksonUtils.beanToXml(resultMessage);
                return encrypt ? wxBizMsgCrypt.encryptMsg(resultXml, timestamp, nonce) : resultXml;
            }
        } catch (Exception e) {
            LOGGER.error("dispatch handle error:", e);
        }

        // 无论如何都应该返回结果以避免微信服务器重试
        return ErrorCodeMessage.SUCCESS.getMessage();
    }
}
