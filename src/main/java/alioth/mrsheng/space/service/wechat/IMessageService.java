package alioth.mrsheng.space.service.wechat;

import alioth.mrsheng.space.core.ioc.MatchingBean;
import alioth.mrsheng.space.domain.wechat.message.Message;

public interface IMessageService extends MatchingBean<String> {

    /**
     * 处理收到的 xml 消息
     *
     * @param xmlMessage 明文 xml 消息
     * @return 处理后的消息对象
     */
    Message handle(String xmlMessage);
}
