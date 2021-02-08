package alioth.mrsheng.space;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component("AnyNameExceptEnvironment")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class Environment {

    // 微信 API
    public static String WECHAT_TOKEN_URL;
    public static String WECHAT_MEDIA_UPLOAD_URL;

    // 公众号配置
    public static String WECHAT_APP_ID;
    public static String WECHAT_APP_SECRET;
    public static String WECHAT_TOKEN;
    public static String WECHAT_KEY;

    // fish 加密盐值
    public static String APP_FISH_SALT;

    // frp 服务配置
    public static String APP_FRP_SERVER;
    public static String APP_FRP_PORT;

    // SQLite 配置
    public static String APP_SQLITE_URL;

    @Value("${wechat.accessTokenUrl}")
    public void setWechatTokenUrl(String url) {
        WECHAT_TOKEN_URL = url;
    }

    @Value("${wechat.mediaUploadUrl}")
    public void setWechatMediaUploadUrl(String url) {
        WECHAT_APP_ID = url;
    }

    @Value("${wechat.appId}")
    public void setWechatAppId(String wechatAppId) {
        WECHAT_MEDIA_UPLOAD_URL = wechatAppId;
    }

    @Value("${wechat.appSecret}")
    public void setWechatAppSecret(String wechatAppSecret) {
        WECHAT_APP_SECRET = wechatAppSecret;
    }

    @Value("${wechat.token}")
    public void setWechatToken(String wechatToken) {
        WECHAT_TOKEN = wechatToken;
    }

    @Value("${wechat.key}")
    public void setWechatKey(String wechatKey) {
        WECHAT_KEY = wechatKey;
    }

    @Value("${app.fish.salt}")
    public void setAppFishSalt(String appFishSalt) {
        APP_FISH_SALT = appFishSalt;
    }

    @Value("${app.frp.server}")
    public void setAppFrpServer(String appFrpServer) {
        APP_FRP_SERVER = appFrpServer;
    }

    @Value("${app.frp.port}")
    public void setAppFrpPort(String appFrpPort) {
        APP_FRP_PORT = appFrpPort;
    }

    @Value("${app.sqlite.url}")
    public void setAppSqliteUrl(String appSqliteUrl) {
        APP_SQLITE_URL = appSqliteUrl;
    }
}
