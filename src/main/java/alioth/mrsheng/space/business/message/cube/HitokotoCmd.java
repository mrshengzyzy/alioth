package alioth.mrsheng.space.business.message.cube;

import alioth.mrsheng.space.core.utils.MessageFactory;
import alioth.mrsheng.space.domain.message.Message;
import alioth.mrsheng.space.funny.HitokotoFunny;
import org.apache.commons.lang3.StringUtils;

public class HitokotoCmd {

    static Message execute(String toUser, String fromUser) {
        String message = HitokotoFunny.get();
        if (StringUtils.isBlank(message)) {
            message = "不知道你在说啥!";
        } else {
            message += "\n数据来自 <a href=\"https://hitokoto.cn/api\">一言 API</a>";
        }
        return MessageFactory.buildTextMessage(toUser, fromUser, message);
    }
}
