package alioth.mrsheng.space.service.fish;

import alioth.mrsheng.space.core.sqlite.DBHelper;
import alioth.mrsheng.space.domain.fish.FishFood;
import cn.hutool.db.Entity;

// 辅助操作数据库
public class FishFoodHelper extends DBHelper {

    // 表名称
    public static final String TABLE_NAME = "fish_food";

    // 默认查询条件
    public static final Entity EMPTY = Entity.create(TABLE_NAME);

    // 查询条件
    public static final String ID = "id";
    public static final String STATUS = "status";

    // 操作对象是 Entity,因此这里统一做转换
    public static Entity beanToEntity(FishFood fishFood) {
        Entity entity = Entity.parse(fishFood, true, false);
        entity.setTableName(FishFoodHelper.TABLE_NAME);
        return entity;
    }
}
