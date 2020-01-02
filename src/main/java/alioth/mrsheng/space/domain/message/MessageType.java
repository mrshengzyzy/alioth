package alioth.mrsheng.space.domain.message;

/**
 * 消息类型
 */
public final class MessageType {

    // 文本消息
    public static final String TEXT = "text";

    // 图片消息
    public static final String IMAGE = "image";

    // 语音消息
    public static final String VOICE = "voice";

    // 视频消息
    public static final String VIDEO = "video";

    // 小视频消息
    public static final String SHORT_VIDEO = "shortvideo";

    // 地理位置消息
    public static final String LOCATION = "location";

    // 链接消息
    public static final String LINK = "link";

    /**
     * 表示这个消息是由Event(点击菜单事件)触发
     * 后续转交给EventMessage消息处理
     */
    public static final String EVENT = "event";
}
