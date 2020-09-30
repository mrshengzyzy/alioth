package alioth.mrsheng.space.service;

import alioth.mrsheng.space.persistence.FishFood;
import alioth.mrsheng.space.persistence.IFishFoodRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IFishFoodServiceTest {

    @Autowired
    private IFishFoodRepository repository;

    @Test
    public void save() {

        List<FishFood> foodList = new ArrayList<FishFood>() {{
            add(new FishFood("1"));
            add(new FishFood("22"));
            add(new FishFood("333"));
            add(new FishFood("4444"));
            add(new FishFood("55555"));
            add(new FishFood("666666"));
            add(new FishFood("7777777"));
            add(new FishFood("88888888"));
            add(new FishFood("999999999"));
            add(new FishFood("0000000000"));
        }};

        foodList.forEach(food -> {

            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            food.setCreateTimestamp(System.currentTimeMillis() / 1000);

            repository.save(food);
        });
    }

    @Test
    public void queue() {
        List<FishFood> foodList = repository.findByStatusOrderByCreateTimestamp(2);
        System.out.println(foodList);
    }
}
