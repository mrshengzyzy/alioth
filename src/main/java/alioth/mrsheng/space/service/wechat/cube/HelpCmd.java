package alioth.mrsheng.space.service.wechat.cube;

import alioth.mrsheng.space.Environment;
import alioth.mrsheng.space.service.wechat.MessageFactory;
import alioth.mrsheng.space.domain.message.Message;

class HelpCmd {

    static final String CMD = "CMD|COMMAND|HELP|帮助|命令|指令";

    static Message execute(String toUser, String fromUser) {
        String message = "<a href=\"" + Environment.APP_FRP_SERVER + "/index\">查看所有指令</a>";
        return MessageFactory.buildTextMessage(toUser, fromUser, message);
    }
}
