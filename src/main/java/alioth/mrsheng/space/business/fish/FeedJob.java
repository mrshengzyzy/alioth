package alioth.mrsheng.space.business.fish;

import alioth.mrsheng.space.Environment;
import alioth.mrsheng.space.core.utils.CommonUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;

@Service
@EnableScheduling
public class FeedJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedJob.class);

    // 单位是毫秒
    @Scheduled(fixedRate = 20 * 60 * 1000)
    public void doJob() {

//        // 拿到所有正在排队的数据
//        List<FishFood> foodList = FishDBHelper.readAll();
//        foodList.removeIf(fishFood -> !fishFood.getStatus().equals(FishFood.QUEUE));
//
//        if (CollectionUtils.isEmpty(foodList)) {
//            LOGGER.info("No food wait to feed");
//            return;
//        }
//
//        // 按照投放时间升序排序
//        foodList.sort(Comparator.comparing(o -> Long.valueOf(o.getUseTimestamp())));
//
//        // 去除排队时间最久的鱼食
//        FishFood fishFood = foodList.get(0);
//        String food = fishFood.getFood();
//        LOGGER.info("Prepare send food {} to raspberry", food);
//
//        try {
//            // 将加密后的 food 发送给8118端口
//            // frps 将监听此端口,然后将数据转发到树莓派上
//            String url2 = "http://" + Environment.SERVER_IP + ":" + Environment.FRP_WEB_PORT + "/fish?food=" + CommonUtils.aesEncrypt(food);
//            CloseableHttpClient client = HttpClients.createDefault();
//            HttpGet httpGet = new HttpGet(url2);
//            CloseableHttpResponse response = client.execute(httpGet);
//            String responseStr = EntityUtils.toString(response.getEntity());
//
//            // 树莓派将返回解密后的food,一致说明处理成功
//            if (responseStr.equals(food)) {
//                updateFood(fishFood);
//                LOGGER.info("Food {} feed success", food);
//            } else {
//                LOGGER.info("Food {} feed response error: {}", food, responseStr);
//            }
//        } catch (Exception e) {
//            LOGGER.error("Food {} feed error:", food, e);
//        }
    }

//    private void updateFood(FishFood fishFood) {
////        fishFood.setStatus(FishFood.USED);
////        FishDBHelper.update(fishFood);
//    }
}
