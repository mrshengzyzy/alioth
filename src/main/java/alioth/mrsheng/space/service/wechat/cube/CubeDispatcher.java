package alioth.mrsheng.space.service.wechat.cube;

import alioth.mrsheng.space.domain.message.Message;
import alioth.mrsheng.space.service.wechat.ISupportService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * cubeDispatcher 用来处理收到的字符串消息
 * 指令格式为:
 * 指令@指令内容, 如 投食@SX34DS
 * 部分指令无内容, 如 ISS
 */
@Service
public class CubeDispatcher {

    // 指令分隔符
    public static final String CMD = "@";

    @Resource
    private ISupportService supportService;

    /**
     * 根据收到的文本信息作出不同的响应
     *
     * @param toUser   公众号的OpenId
     * @param fromUser 发送者的OpenId
     * @param content  公众号收到的文本内容
     */
    public Message dispatch(String toUser, String fromUser, String content) {

        content = StrUtil.trim(content);

        if (match(content, PictureCmd.CMD)) {
            return PictureCmd.execute(toUser, fromUser, content, supportService);
        }

        if (match(content, IssCmd.CMD)) {
            return IssCmd.execute(toUser, fromUser);
        }

        if (match(content, FishCmd.CMD)) {
            return FishCmd.execute(toUser, fromUser, content);
        }

        if (match(content, HelpCmd.CMD)) {
            return HelpCmd.execute(toUser, fromUser);
        }

        // 使用 一言API 作为默认响应
        return HitokotoCmd.execute(toUser, fromUser);
    }

    /**
     * 判断输入内容是否是特定指令
     * content如果包含@,那么指令是冒号之前部分,不包含的话表示是一个无参指令
     * cmd指令指的是每个指令处理器(xxxCmd类)定义的标准CMD字段
     */
    private static boolean match(String content, String cmd) {

        // 筛选指令
        String tempCmd = content;
        if (content.contains(CubeDispatcher.CMD)) {
            tempCmd = content.split(CubeDispatcher.CMD)[0];
        }

        // 判断输入指令是否与预设指令匹配
        for (String c : cmd.split("\\|")) {
            if (c.equalsIgnoreCase(tempCmd)) {
                return true;
            }
        }
        return false;
    }
}
