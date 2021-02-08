package alioth.mrsheng.space.service.wechat;

public interface IWeChatService {

    /**
     * 微信token验证业务
     *
     * @param signature 签名
     * @param timestamp 时间戳
     * @param nonce     随机数
     */
    boolean check(String signature, String timestamp, String nonce);

    /**
     * 解析用户发送的xml数据并根据消息类型分发
     * 要求5秒内给予微信服务器响应,如果无法保证5秒内回复的话,需要主动调用客服消息接口
     *
     * @param xmlString 用户数据,可能是加密后的字符串
     */
    String dispatch(String xmlString, String signature, String timestamp, String nonce);
}
