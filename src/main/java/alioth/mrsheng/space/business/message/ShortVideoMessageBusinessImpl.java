package alioth.mrsheng.space.business.message;

import alioth.mrsheng.space.domain.message.Message;
import alioth.mrsheng.space.domain.message.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 小视频消息处理
 */
@Service
public class ShortVideoMessageBusinessImpl implements IMessageBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShortVideoMessageBusinessImpl.class);

    @Override
    public boolean match(String selector) {
        return MessageType.SHORT_VIDEO.equals(selector);
    }

    @Override
    public Message handle(String xmlMessage) {
        LOGGER.info("ShortVideoMessageBusiness handle message:{}", xmlMessage);
        return null;
    }
}
