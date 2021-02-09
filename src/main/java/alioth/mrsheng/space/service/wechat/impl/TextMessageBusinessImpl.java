package alioth.mrsheng.space.service.wechat.impl;

import alioth.mrsheng.space.core.JacksonUtils;
import alioth.mrsheng.space.domain.wechat.message.Message;
import alioth.mrsheng.space.domain.wechat.message.MessageType;
import alioth.mrsheng.space.domain.wechat.message.TextMessage;
import alioth.mrsheng.space.service.wechat.IMessageService;
import alioth.mrsheng.space.service.wechat.cube.CubeDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TextMessageBusinessImpl implements IMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TextMessageBusinessImpl.class);

    @Resource
    private CubeDispatcher cubeDispatcher;

    @Override
    public boolean match(String selector) {
        return MessageType.TEXT.equals(selector);
    }

    @Override
    public Message handle(String xmlMessage) {
        try {
            // 转换为文本消息
            TextMessage message = JacksonUtils.xmlToBean(xmlMessage, TextMessage.class);

            // 接收公众号
            String toUserName = message.getToUserName();

            // 发送方的OpenId
            String fromUserName = message.getFromUserName();

            // 交给消息模块处理
            return cubeDispatcher.dispatch(toUserName, fromUserName, message.getContent());

        } catch (Exception e) {
            LOGGER.error("[WeChat] Handle text message error:", e);
        }
        return null;
    }
}
