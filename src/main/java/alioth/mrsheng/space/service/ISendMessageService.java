package alioth.mrsheng.space.service;

/**
 * 发送消息服务
 */
public interface ISendMessageService {

    /**
     * 发送文本消息
     *
     * @param token   access_token
     * @param message 消息内容
     */
    void sendTextMessage(String token, String message);
}
