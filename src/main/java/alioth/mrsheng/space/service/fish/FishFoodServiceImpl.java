package alioth.mrsheng.space.service.fish;

import alioth.mrsheng.space.domain.fish.FishFood;
import alioth.mrsheng.space.domain.fish.FishFoodStatus;
import alioth.mrsheng.space.service.fish.FishFoodHelper;
import alioth.mrsheng.space.service.fish.IFishFoodService;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishFoodServiceImpl implements IFishFoodService {

    @Override
    public List<FishFood> findAll() throws Exception {
        return Db.use().findAll(FishFoodHelper.EMPTY, FishFood.class);
    }

    @Override
    public List<FishFood> find(FishFood where) throws Exception {
        Entity entity = FishFoodHelper.beanToEntity(where);
        return Db.use().findAll(entity, FishFood.class);
    }

    @Override
    public String createFood() throws Exception {
        String id = FishFoodHelper.uuid(8);
        FishFood food = new FishFood();
        food.setId(id);
        food.setCreateTimestamp(System.currentTimeMillis() / 1000);
        food.setUseTimestamp(0);
        food.setStatus(FishFoodStatus.UNUSED.getCode());
        Db.use().insert(FishFoodHelper.beanToEntity(food));
        return id;
    }

    @Override
    public boolean queueFood(String id) throws Exception {
        Db.use().execute("UPDATE `fish_food` SET `status` = ? WHERE `id` = ?", FishFoodStatus.QUEUE.getCode(), id);
        return true;
    }

    @Override
    public boolean useFood(String id) throws Exception {
        Db.use().execute("UPDATE `fish_food` SET `status` = ? WHERE `id` = ?", FishFoodStatus.USED.getCode(), id);
        return false;
    }
}

