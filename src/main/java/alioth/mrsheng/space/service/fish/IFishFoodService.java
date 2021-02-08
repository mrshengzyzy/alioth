package alioth.mrsheng.space.service.fish;

import alioth.mrsheng.space.domain.fish.FishFood;

import java.util.List;

public interface IFishFoodService {

    List<FishFood> findAll() throws Exception;

    List<FishFood> find(FishFood where) throws Exception;

    String createFood() throws Exception;

    // 入队等待投放
    boolean queueFood(String id) throws Exception;

    // 投食
    boolean useFood(String id) throws Exception;
}
