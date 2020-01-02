package alioth.mrsheng.space.core.utils;


import alioth.mrsheng.space.domain.message.ImageMessage;
import alioth.mrsheng.space.domain.message.MessageType;
import alioth.mrsheng.space.domain.message.TextMessage;

import java.util.Date;

public final class MessageFactory {

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
