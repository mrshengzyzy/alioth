package alioth.mrsheng.space.service.wechat.cube;

import alioth.mrsheng.space.domain.media.MediaType;
import alioth.mrsheng.space.domain.message.Message;
import alioth.mrsheng.space.funny.RandomPictureFunny;
import alioth.mrsheng.space.service.wechat.ISupportService;
import alioth.mrsheng.space.service.wechat.MessageFactory;
import cn.hutool.core.util.StrUtil;

import java.io.File;

class PictureCmd {

    static final String CMD = "PIC|PICTURE|图片";

    static Message execute(String toUser, String fromUser, String content, ISupportService mediaService) {

        int width = 0;
        int height = 0;

        // 首先将指令符号(@)替换为空格
        // 格式: 图片@400    600
        content = content.replaceAll(CubeDispatcher.CMD, " ");

        // 使用空格分隔,长度不固定
        String[] cmds = content.split(" ");

        try {
            // 第一个是指令忽略
            for (int i = 1; i < cmds.length; i++) {
                String temp = cmds[i];
                if (StrUtil.isNotBlank(temp)) {
                    if (width == 0) {
                        width = Integer.valueOf(temp);
                        continue;
                    }
                    height = Integer.valueOf(temp);
                    break;
                }
            }
        } catch (Exception e) {
            width = 400;
            height = 400;
        }

        File qrCode = RandomPictureFunny.get(width, height);
        String mediaId = mediaService.uploadTempMedia(MediaType.IMAGE, qrCode);
        return MessageFactory.buildImageMessage(toUser, fromUser, mediaId);
    }
}
