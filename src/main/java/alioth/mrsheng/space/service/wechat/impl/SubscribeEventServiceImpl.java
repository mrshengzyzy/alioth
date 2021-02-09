package alioth.mrsheng.space.service.wechat.impl;

import alioth.mrsheng.space.core.JacksonUtils;
import alioth.mrsheng.space.domain.wechat.event.EventType;
import alioth.mrsheng.space.domain.wechat.event.SubscribeEvent;
import alioth.mrsheng.space.domain.wechat.message.Message;
import alioth.mrsheng.space.service.wechat.IEventService;
import alioth.mrsheng.space.service.wechat.MessageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SubscribeEventServiceImpl implements IEventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscribeEventServiceImpl.class);

    @Override
    public boolean match(String eventType) {
        return EventType.SUBSCRIBE.equals(eventType);
    }

    @Override
    public Message handle(String xmlMessage) {
        try {

            // 转换为特定消息对象
            SubscribeEvent message = JacksonUtils.xmlToBean(xmlMessage, SubscribeEvent.class);

            // 接收公众号
            String toUserName = message.getToUserName();

            // 发送方的OpenId
            String fromUserName = message.getFromUserName();

            // 回复内容
            String content = "哇哦,来了\n试着跟我聊天吧!";

            // 构造回复消息
            return MessageFactory.buildTextMessage(toUserName, fromUserName, content);

        } catch (Exception e) {
            LOGGER.error("ClickEventMessageBusiness handle error:", e);
        }
        return null;
    }
}
