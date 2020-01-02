package alioth.mrsheng.space.domain;

import alioth.mrsheng.space.Environment;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 获取access_token请求对象
 * 请求入参都是固定值
 */
public class AccessTokenRequest {

    @JsonProperty("grant_type")
    private String grantType = "client_credential";

    @JsonProperty("appid")
    private String appId = Environment.APP_ID;

    @JsonProperty("secret")
    private String secret = Environment.APP_SECRET;

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
