package alioth.mrsheng.space.service.wechat.impl;

import alioth.mrsheng.space.domain.wechat.message.Message;
import alioth.mrsheng.space.domain.wechat.message.MessageType;
import alioth.mrsheng.space.service.wechat.IMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LinkMessageBusinessImpl implements IMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkMessageBusinessImpl.class);

    @Override
    public boolean match(String selector) {
        return MessageType.VIDEO.equals(selector);
    }

    @Override
    public Message handle(String xmlMessage) {
        LOGGER.info("LinkMessageBusiness handle message:{}", xmlMessage);
        return null;
    }
}
