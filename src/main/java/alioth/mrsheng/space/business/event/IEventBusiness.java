package alioth.mrsheng.space.business.event;

import alioth.mrsheng.space.domain.message.Message;

/**
 * Event消息处理接口
 * 注意这里并没有实现 MatchingBean 接口,提供不同的处理方式而已
 */
public interface IEventBusiness {

    /**
     * 由子类判断是否该处理该消息
     */
    boolean match(String xmlMessage);

    /**
     * 处理用户点击事件
     */
    Message handle(String xmlMessage);
}
