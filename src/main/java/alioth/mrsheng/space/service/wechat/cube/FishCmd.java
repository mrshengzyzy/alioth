package alioth.mrsheng.space.service.wechat.cube;

import alioth.mrsheng.space.service.wechat.MessageFactory;
import alioth.mrsheng.space.domain.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 鱼食是一个包含10个字符的字符串
 * 每份鱼食时效是24小时
 * 限制喂鱼为每半小时一次
 */
class FishCmd {

    private static final Logger LOGGER = LoggerFactory.getLogger(FishCmd.class);

    static final String CMD = "Fish|投食|喂鱼";

    /**
     * 收到两种指令
     * 1、获取鱼食
     * 2、喂鱼
     */
    static Message execute(String toUser, String fromUser, String content) {

//        // 获取指令中的 鱼食编码
//        String passFood = content.contains(CubeDispatcher.CMD) ? content.split(CubeDispatcher.CMD)[1].trim() : "";
//
//        // 根据指令编码确定操作
//        String message = StringUtils.isBlank(passFood) ? newFishFood() : feedFishFood(passFood);

        return MessageFactory.buildTextMessage(toUser, fromUser, "");
    }

//    static String newFishFood() {
//        String food = CommonUtils.uuid(10);
//        FishDBHelper.write(new FishFood(food));
//        LOGGER.info("create new fish food {}", food);
//        return food;
//    }
//
//    static String feedFishFood(String food) {
//        FishFood fishFood = FishDBHelper.read(food);
//        fishFood.setUseTimestamp(String.valueOf(System.currentTimeMillis()));
//        fishFood.setStatus(FishFood.QUEUE);
//        FishDBHelper.update(fishFood);
//        LOGGER.info("fish food {} was feed", food);
//        return food + " 已加入投放队列\n<a href=\"106.12.211.195/fish\">点击查看</a>";
//    }
}
