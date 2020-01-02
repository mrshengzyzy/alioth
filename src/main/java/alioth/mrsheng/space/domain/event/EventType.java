package alioth.mrsheng.space.domain.event;

/**
 * 菜单点击事件类型
 */
public final class EventType {

    // 点击菜单
    public static final String CLICK = "CLICK";

    // 跳转菜单
    public static final String VIEW = "VIEW";

    // 扫码推事件
    public static final String SCANCODE_PUSH = "scancode_push";

    // 扫码推事件且弹出“消息接收中”提示框的事件
    public static final String SCANCODE_WAITMSG = "scancode_waitmsg";

    // 系统拍照发图的事件
    public static final String PIC_SYSPHOTO = "pic_sysphoto";

    // 拍照或者相册发图的事件
    public static final String PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";

    // 相册发图的事件
    public static final String PIC_WEIXIN = "pic_weixin";

    // 地理位置选择器的事件
    public static final String LOCATION_SELECT = "location_select";

}
