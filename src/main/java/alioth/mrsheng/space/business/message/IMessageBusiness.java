package alioth.mrsheng.space.business.message;

import alioth.mrsheng.space.core.ioc.MatchingBean;
import alioth.mrsheng.space.domain.message.Message;

/**
 * 消息处理接口
 */
public interface IMessageBusiness extends MatchingBean<String> {

    /**
     * 处理用户发送的消息
     *
     * @param xmlMessage xml格式消息
     */
    Message handle(String xmlMessage);
}
