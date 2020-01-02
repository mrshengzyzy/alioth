package alioth.mrsheng.space;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;
import java.util.TimeZone;

/**
 * 环境变量配置
 * 引用 application.properties 配置,这样就不用到处写 @Value() 了
 */
public class Environment {

    private static final Logger LOGGER = LoggerFactory.getLogger(Environment.class);

    // 服务器IP地址
    public static String SERVER_IP;

    // frps 监听web服务地址
    public static String FRP_WEB_PORT;

    // 北京时间
    public static final TimeZone ZONE = TimeZone.getTimeZone("GMT+8:00");

    // 开发者ID 和 开发者密码
    public static String APP_ID;
    public static String APP_SECRET;

    // 公众平台自定义的token 和 AES key
    public static String TOKEN;
    public static String KEY;

    // fish模块加密盐值
    public static String FISH_SALT;
    public static String FISH_DB;

    static {
        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("application.properties"));
            APP_ID = properties.getProperty("wechat.app.id");
            APP_SECRET = properties.getProperty("wechat.app.secret");
            TOKEN = properties.getProperty("wechat.token");
            KEY = properties.getProperty("wechat.key");
            FISH_SALT = properties.getProperty("fish.salt");
            FISH_DB = properties.getProperty("fish.db");
            SERVER_IP = properties.getProperty("alioth.server.ip");
            FRP_WEB_PORT = properties.getProperty("frp.web.port");
        } catch (IOException e) {
            LOGGER.error("load properties error:", e);
        }
    }

    /**
     * 公众号域名配置
     * 最新来源请参考技术文档页面说明
     */
    // 使用该域名将访问官方指定就近的接入点
    private static final String HOST = "https://api.weixin.qq.com";

//    // 上海接入点
//    public static final String HOST = "https://sh.api.weixin.qq.com";
//    // 深圳接入点
//    public static final String HOST = "https://sz.api.weixin.qq.com";
//    // 香港接入点
//    public static final String HOST = "https://hk.api.weixin.qq.com";

    // access_token请求URL
    public static final String TOKEN_URL = HOST + "/cgi-bin/token";

    // 素材操作URL
    public static final String UPLOAD_MEDIA_URL = HOST + "/cgi-bin/media/upload";
}
