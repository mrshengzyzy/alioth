package alioth.mrsheng.space.service.wechat;

import alioth.mrsheng.space.domain.message.ImageMessage;
import alioth.mrsheng.space.domain.message.MessageType;
import alioth.mrsheng.space.domain.message.TextMessage;
import cn.hutool.core.util.StrUtil;

import java.util.Date;

public final class MessageFactory {

    private static final String MESSAGE_TYPE_START = "<MsgType><![CDATA[";
    private static final String MESSAGE_TYPE_END = "]]></MsgType>";
    private static final String EVENT_START = "<Event><![CDATA[";
    private static final String EVENT_END = "]]></Event>";
    private static final String ENCRYPT_FLAG = "<Encrypt><![CDATA[";

    /**
     * 判断是否是加密消息
     * 混合消息模式结构:
     * <xml>
     * ....<ToUserName><![CDATA[gh_ad4bfafc3d29]]></ToUserName>
     * ....<FromUserName><![CDATA[odF6gwKlabfIitGNVKNU6ewiGlR8]]></FromUserName>
     * ....<CreateTime>1604487000</CreateTime>
     * ....<MsgType><![CDATA[text]]></MsgType>
     * ....<Content><![CDATA[反反复复]]></Content>
     * ....<MsgId>22970734916554735</MsgId>
     * ....<Encrypt><![CDATA[xxxxxxxxxxx]]></Encrypt>
     * </xml>
     * 加密消息模式结构:
     * <xml>
     * ....<ToUserName><![CDATA[gh_ad4bfafc3d29]]></ToUserName>
     * ....<Encrypt><![CDATA[xxxxxxxxxxx]]></Encrypt>
     * </xml>
     * 明文消息模式结构:
     * <xml>
     * ....<ToUserName><![CDATA[gh_ad4bfafc3d29]]></ToUserName>
     * ....<FromUserName><![CDATA[odF6gwKlabfIitGNVKNU6ewiGlR8]]></FromUserName>
     * ....<CreateTime>1604487000</CreateTime>
     * ....<MsgType><![CDATA[text]]></MsgType>
     * ....<Content><![CDATA[反反复复]]></Content>
     * ....<MsgId>22970734916554735</MsgId>
     * </xml>
     */
    public static boolean isEncryptMessage(String str) {
        return StrUtil.contains(str, ENCRYPT_FLAG);
    }

    /**
     * 提取消息类型
     * 直接解析消息两个特殊字符串之间部分作为消息类型,减少一次反序列化的操作
     * <xml>
     * ....<ToUserName><![CDATA[gh_ad4bfafc3d29]]></ToUserName>
     * ....<FromUserName><![CDATA[odF6gwKlabfIitGNVKNU6ewiGlR8]]></FromUserName>
     * ....<CreateTime>1604460665</CreateTime>
     * ....<MsgType><![CDATA[text]]></MsgType>
     * ....<Content><![CDATA[2333]]></Content>
     * ....<MsgId>22970355837120646</MsgId>
     * </xml>
     */
    public static String getMessageType(String realClearXml) {
        return realClearXml.substring(realClearXml.indexOf(MESSAGE_TYPE_START) + 18, realClearXml.indexOf(MESSAGE_TYPE_END));
    }

    /**
     * 提取事件类型
     * <xml>
     * ....<ToUserName><![CDATA[toUser]]></ToUserName>
     * ....<FromUserName><![CDATA[FromUser]]></FromUserName>
     * ....<CreateTime>123456789</CreateTime>
     * ....<MsgType><![CDATA[event]]></MsgType>
     * <Event><![CDATA[subscribe]]></Event>
     * 其他字段
     * </xml>
     */
    public static String getEventType(String realClearXml) {
        return realClearXml.substring(realClearXml.indexOf(EVENT_START) + 16, realClearXml.indexOf(EVENT_END));
    }

    /**
     * 创建文本消息
     *
     * @param from    公众号
     * @param to      接收人OpenID
     * @param content 回复的消息内容
     */
    public static TextMessage buildTextMessage(String from, String to, String content) {
        TextMessage message = new TextMessage();
        message.setToUserName(to);
        message.setFromUserName(from);
        message.setCreateTime((new Date()).getTime());
        message.setMsgType(MessageType.TEXT);
        message.setContent(content);
        return message;
    }

    /**
     * 创建图片消息
     *
     * @param from    公众号
     * @param to      接收人OpenID
     * @param mediaId 通过素材管理中的接口上传多媒体文件，得到的id
     */
    public static ImageMessage buildImageMessage(String from, String to, String mediaId) {
        ImageMessage message = new ImageMessage();
        message.setToUserName(to);
        message.setFromUserName(from);
        message.setCreateTime((new Date()).getTime());
        message.setMsgType(MessageType.IMAGE);
        ImageMessage.Image image = new ImageMessage.Image();
        image.setMediaId(mediaId);
        message.setImage(image);
        return message;
    }
}
