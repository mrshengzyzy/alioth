package cn.hutool.db;

import cn.hutool.db.ds.DSFactory;
import cn.hutool.setting.Setting;

import java.sql.SQLException;

public class MainTest {

    public static void main(String[] args) {
        Setting setting = Setting.create();
        setting.put("url", "jdbc:sqlite:/root/alioth/alioth.db");
        DSFactory factory = DSFactory.create(setting);
        DSFactory.setCurrentDSFactory(factory);

        try {
            Db.use().insert(
                    Entity.create("user")
                            .set("name", "unitTestUser")
                            .set("age", 66)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
