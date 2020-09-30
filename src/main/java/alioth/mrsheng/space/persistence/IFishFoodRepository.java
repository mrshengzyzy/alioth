package alioth.mrsheng.space.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IFishFoodRepository extends CrudRepository<FishFood, String> {

    List<FishFood> findByStatusOrderByCreateTimestamp(int status);
}
