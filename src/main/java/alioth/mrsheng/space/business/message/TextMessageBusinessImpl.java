package alioth.mrsheng.space.business.message;

import alioth.mrsheng.space.core.utils.JacksonUtils;
import alioth.mrsheng.space.domain.message.Message;
import alioth.mrsheng.space.domain.message.MessageType;
import alioth.mrsheng.space.domain.message.TextMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import alioth.mrsheng.space.business.message.cube.CubeDispatcher;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 收到文本处理逻辑
 * 当前处理逻辑是将输入倒序返回
 */
@Service
public class TextMessageBusinessImpl implements IMessageBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(TextMessageBusinessImpl.class);

    @Resource
    private CubeDispatcher cubeDispatcher;

    @Override
    public boolean match(String selector) {
        // 仅处理文本类型的消息
        return MessageType.TEXT.equals(selector);
    }

    @Override
    public Message handle(String xmlMessage) {

        LOGGER.info("[WeChat] Receive text message:\n{}", xmlMessage);

        try {
            // 转换为文本消息
            TextMessage message = JacksonUtils.xmlToBean(xmlMessage, TextMessage.class);

            // 接收公众号
            String toUserName = message.getToUserName();

            // 发送方的OpenId
            String fromUserName = message.getFromUserName();

            // 发送内容
            String content = StringUtils.trim(message.getContent());

            // 构造回复消息
            return cubeDispatcher.dispatch(toUserName, fromUserName, content);

        } catch (IOException e) {
            LOGGER.error("[WeChat] Handle text message error:", e);
        }
        return null;
    }
}
