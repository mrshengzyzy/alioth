package alioth.mrsheng.space.service.wechat.impl;

import alioth.mrsheng.space.core.JacksonUtils;
import alioth.mrsheng.space.domain.message.Message;
import alioth.mrsheng.space.domain.message.MessageType;
import alioth.mrsheng.space.domain.message.VoiceMessage;
import alioth.mrsheng.space.service.wechat.IMessageService;
import alioth.mrsheng.space.service.wechat.MessageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class VoiceMessageBusinessImpl implements IMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoiceMessageBusinessImpl.class);

    @Override
    public boolean match(String selector) {
        return MessageType.VOICE.equals(selector);
    }

    @Override
    public Message handle(String xmlMessage) {
        try {
            // 转换为语音消息
            VoiceMessage message = JacksonUtils.xmlToBean(xmlMessage, VoiceMessage.class);

            // 接收公众号
            String toUserName = message.getToUserName();

            // 发送方的OpenId
            String fromUserName = message.getFromUserName();

            // 获取消息格式
            String format = message.getFormat();
            String content = "上传消息格式为:" + format;

            // 构造回复消息
            return MessageFactory.buildTextMessage(toUserName, fromUserName, content);

        } catch (IOException e) {
            LOGGER.error("VoiceMessageBusiness handle error:", e);
        }
        return null;
    }
}
