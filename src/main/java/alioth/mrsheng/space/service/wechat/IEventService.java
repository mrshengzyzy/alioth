package alioth.mrsheng.space.service.wechat;

/**
 * Event 消息属于特殊的 Message
 * 所有事件消息的 MsgType 都是固定值 event,区别消息类型用的是 Event 字段
 */
public interface IEventService extends IMessageService {
}
