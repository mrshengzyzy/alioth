package alioth.mrsheng.space.business.message.cube;

import alioth.mrsheng.space.core.utils.MessageFactory;
import alioth.mrsheng.space.domain.media.MediaType;
import alioth.mrsheng.space.domain.message.Message;
import alioth.mrsheng.space.funny.ShadowSocksFunny;
import alioth.mrsheng.space.service.IMediaService;

import java.io.File;

class ShadowSocksCmd {

    static final String CMD = "SS|SHADOWSOCKS|";

    static Message execute(String toUser, String fromUser, IMediaService mediaService) {
        File qrCode = ShadowSocksFunny.getQRCode();
        if (null == qrCode) {
            return MessageFactory.buildTextMessage(toUser, fromUser, "获取ShadowSocks服务信息出错了,等下再试试!");
        }
        String mediaId = mediaService.uploadTempMedia(MediaType.IMAGE, qrCode);
        return MessageFactory.buildImageMessage(toUser, fromUser, mediaId);
    }
}
