package alioth.mrsheng.space.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 获取 access_token 响应对象
 */
public class AccessTokenResponse extends Response {

    // 有效性时长,单位秒
    @JsonProperty("expires_in")
    private Integer expires;

    // 拿到的token
    @JsonProperty("access_token")
    private String token;

    public Integer getExpires() {
        return expires;
    }

    public void setExpires(Integer expires) {
        this.expires = expires;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
