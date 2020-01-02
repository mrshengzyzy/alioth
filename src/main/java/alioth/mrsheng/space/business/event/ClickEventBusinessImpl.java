package alioth.mrsheng.space.business.event;

import alioth.mrsheng.space.core.utils.JacksonUtils;
import alioth.mrsheng.space.core.utils.MessageFactory;
import alioth.mrsheng.space.domain.event.ClickViewEvent;
import alioth.mrsheng.space.domain.event.EventType;
import alioth.mrsheng.space.domain.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 普通点击菜单处理
 */
@Service
public class ClickEventBusinessImpl extends AbstractEventBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClickEventBusinessImpl.class);

    @Override
    public boolean match(String xmlMessage) {
        return EventType.CLICK.equals(getEvent(xmlMessage));
    }

    @Override
    public Message handle(String xmlMessage) {
        try {

            // 转换为特定消息对象
            ClickViewEvent message = JacksonUtils.xmlToBean(xmlMessage, ClickViewEvent.class);

            // 获取点击的具体按钮,如果有多个菜单显然这里需要分别处理
            String button = message.getEventKey();

//            // 如果点击了xxx按钮处理逻辑
//            if ("xxx".equals(button)) {
//                 xxxBusiness.doSomething();
//            }

            // 接收公众号
            String toUserName = message.getToUserName();

            // 发送方的OpenId
            String fromUserName = message.getFromUserName();

            return MessageFactory.buildTextMessage(toUserName, fromUserName, "");
        } catch (Exception e) {
            LOGGER.error("ClickEventMessageBusiness handle error:", e);
        }
        return null;
    }
}
