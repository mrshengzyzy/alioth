package alioth.mrsheng.space.service.wechat.impl;

import alioth.mrsheng.space.core.JacksonUtils;
import alioth.mrsheng.space.domain.event.EventType;
import alioth.mrsheng.space.domain.event.ScanEvent;
import alioth.mrsheng.space.domain.message.Message;
import alioth.mrsheng.space.service.wechat.IEventService;
import alioth.mrsheng.space.service.wechat.MessageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 扫描二维码处理
 * EventType.SCANCODE_PUSH:不返回结果给用户(即使给了服务器响应)
 * EventType.SCANCODE_WAITMSG:可以给用户一个返回结果
 */
@Service
public class ScanEventServiceImpl implements IEventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScanEventServiceImpl.class);

    @Override
    public boolean match(String eventType) {
        return EventType.SCANCODE_PUSH.equals(eventType) || EventType.SCANCODE_WAITMSG.equals(eventType);
    }

    @Override
    public Message handle(String xmlMessage) {
        try {

            // 转换为特定消息对象
            ScanEvent message = JacksonUtils.xmlToBean(xmlMessage, ScanEvent.class);

            // 获取扫描信息
            ScanEvent.ScanInfo scanInfo = message.getScanCodeInfo();
            String type = scanInfo.getScanType();
            String result = scanInfo.getScanResult();

            // 接收公众号
            String toUserName = message.getToUserName();

            // 发送方的OpenId
            String fromUserName = message.getFromUserName();

            // 回复内容
            String content = "扫描类型:" + type + "\r\n扫描结果:" + result;

            // 构造回复消息
            return MessageFactory.buildTextMessage(toUserName, fromUserName, content);

        } catch (Exception e) {
            LOGGER.error("ClickEventMessageBusiness handle error:", e);
        }
        return null;
    }
}
