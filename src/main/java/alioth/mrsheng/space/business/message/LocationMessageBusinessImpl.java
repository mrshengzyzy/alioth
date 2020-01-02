package alioth.mrsheng.space.business.message;

import alioth.mrsheng.space.domain.message.Message;
import alioth.mrsheng.space.domain.message.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 地理位置信息
 */
@Service
public class LocationMessageBusinessImpl implements IMessageBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationMessageBusinessImpl.class);

    @Override
    public boolean match(String selector) {
        return MessageType.LOCATION.equals(selector);
    }

    @Override
    public Message handle(String xmlMessage) {
        LOGGER.info("[WeChat] Receive Location message:{}", xmlMessage);
        return null;
    }
}
