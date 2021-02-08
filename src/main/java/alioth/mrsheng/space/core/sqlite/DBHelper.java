package alioth.mrsheng.space.core.sqlite;

import alioth.mrsheng.space.Environment;
import cn.hutool.db.Db;
import cn.hutool.db.ds.DSFactory;
import cn.hutool.setting.Setting;

import java.sql.SQLException;
import java.util.UUID;

public class DBHelper {

    public static void Init() {
        Setting setting = Setting.create();
        setting.put("url", Environment.APP_SQLITE_URL);
        DSFactory factory = DSFactory.create(setting);
        DSFactory.setCurrentDSFactory(factory);

        // 建表语句
        initFishFoodTable();
    }

    /**
     * 初始化 fish_food 表
     * 同时会自动创建数据库
     */
    private static void initFishFoodTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `fish_food` (\n" +
                "  `id` varchar(255) NOT NULL,\n" +
                "  `create_timestamp` bigint NOT NULL,\n" +
                "  `status` integer NOT NULL,\n" +
                "  `use_timestamp` bigint NOT NULL,\n" +
                "  PRIMARY KEY (`id`)\n" +
                ");";
        try {
            Db.use().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用 uuid 返回随机字符串
     *
     * @param length 返回字符串长度
     */
    public static String uuid(Integer length) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid.substring(0, length).toUpperCase();
    }
}
