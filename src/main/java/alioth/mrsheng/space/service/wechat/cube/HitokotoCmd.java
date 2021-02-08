package alioth.mrsheng.space.service.wechat.cube;

import alioth.mrsheng.space.service.wechat.MessageFactory;
import alioth.mrsheng.space.domain.message.Message;
import alioth.mrsheng.space.funny.HitokotoFunny;
import cn.hutool.core.util.StrUtil;

public class HitokotoCmd {

    static Message execute(String toUser, String fromUser) {
        String message = HitokotoFunny.get();
        if (StrUtil.isBlank(message)) {
            message = "不知道你在说啥!";
        } else {
            message += "\n数据来自 <a href=\"https://hitokoto.cn/api\">一言 API</a>";
        }
        return MessageFactory.buildTextMessage(toUser, fromUser, message);
    }
}
