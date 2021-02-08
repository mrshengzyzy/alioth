package alioth.mrsheng.space.service.wechat.cube;

import alioth.mrsheng.space.service.wechat.MessageFactory;
import alioth.mrsheng.space.domain.message.Message;
import alioth.mrsheng.space.funny.IssFunny;

import java.util.Map;

class IssCmd {

    static final String CMD = "ISS|International space station|空间站";

    static Message execute(String toUser, String fromUser) {

        Map<String, String> map = IssFunny.get();
        if (null == map) {
            return MessageFactory.buildTextMessage(toUser, fromUser, "未能获取到空间站实时位置,等下再试试!");
        }

        String message = "空间站当前位置:\n"
                + "经度: " + map.get("locationY") + "\n"
                + "纬度: " + map.get("locationX") + "\n"
                + "<a href=\"http://www.heavens-above.com/orbitdisplay.aspx?width=400&amp;height=400\">实时位置</a>";

        return MessageFactory.buildTextMessage(toUser, fromUser, message);
    }
}
