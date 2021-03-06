package alioth.mrsheng.space.service.wechat.impl;

import alioth.mrsheng.space.domain.wechat.message.Message;
import alioth.mrsheng.space.domain.wechat.message.MessageType;
import alioth.mrsheng.space.service.wechat.IMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 视频消息处理
 */
@Service
public class VideoMessageBusinessImpl implements IMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoMessageBusinessImpl.class);

    @Override
    public boolean match(String selector) {
        return MessageType.VIDEO.equals(selector);
    }

    @Override
    public Message handle(String xmlMessage) {
        LOGGER.info("VideoMessageBusiness handle message:{}", xmlMessage);
        return null;
    }
}
