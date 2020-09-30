package alioth.mrsheng.space.business.fish;

import alioth.mrsheng.space.core.utils.CommonUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FishBusiness {

    public FishFoodStatistics queryAll() {

//        FishFoodStatistics result = new FishFoodStatistics();
//        List<FishFoodStatistics.Bean> beanList = new ArrayList<>();
//
//        int unusedCount = 0, usedCount = 0, queueCount = 0;
//        int index = 1;
//
//        // 读取所有记录
//        List<FishFood> foodList = FishDBHelper.readAll();
//        for (FishFood fishFood : foodList) {
//
//            // 根据状态计算数量
//            String status = fishFood.getStatus();
//            if (status.equals(FishFood.UNUSE)) {
//                unusedCount++;
//            } else if (status.equals(FishFood.USED)) {
//                usedCount++;
//            } else {
//                queueCount++;
//            }
//
//            FishFoodStatistics.Bean bean = new FishFoodStatistics.Bean();
//            bean.setIndex(index++);
//            bean.setFood(mask(fishFood.getFood()));
//            bean.setCreateDateTime(CommonUtils.timestampToDateString(fishFood.getTimestamp()));
//            bean.setUseDateTime(CommonUtils.timestampToDateString(fishFood.getUseTimestamp()));
//            bean.setStatusDesc(FishFood.MAPPER.get(status));
//            bean.setStatus(status);
//
//            beanList.add(bean);
//        }
//
//        result.setTotalCount(foodList.size());
//        result.setUnusedCount(unusedCount);
//        result.setUsedCount(usedCount);
//        result.setQueueCount(queueCount);
//        result.setBeanList(beanList);
//        return result;
        return null;
    }

    private String mask(String food) {
        return food.substring(0, 2) + "****" + food.substring(6, 10);
    }
}
