package alioth.mrsheng.space.service.wechat.impl;

import alioth.mrsheng.space.core.JacksonUtils;
import alioth.mrsheng.space.domain.message.ImageMessage;
import alioth.mrsheng.space.domain.message.Message;
import alioth.mrsheng.space.domain.message.MessageType;
import alioth.mrsheng.space.service.wechat.IMessageService;
import alioth.mrsheng.space.service.wechat.MessageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ImageMessageServiceImpl implements IMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageMessageServiceImpl.class);

    @Override
    public boolean match(String selector) {
        return MessageType.IMAGE.equals(selector);
    }

    @Override
    public Message handle(String xmlMessage) {
        try {

            // 转换为图片消息
            ImageMessage message = JacksonUtils.xmlToBean(xmlMessage, ImageMessage.class);
            LOGGER.info("[WeChat - ImageMessage] Receive {}", message);

            // 把收到的图片返回
            // 接收公众号
            String toUserName = message.getToUserName();

            // 发送方的OpenId
            String fromUserName = message.getFromUserName();

            // 素材MediaId
            String mediaId = message.getMediaId();

            // 将受到图片再次返回
            return MessageFactory.buildImageMessage(toUserName, fromUserName, mediaId);

        } catch (IOException e) {
            LOGGER.error("ImageMessageBusiness handle error:", e);
        }
        return null;
    }
}
