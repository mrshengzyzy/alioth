package alioth.mrsheng.space.service.impl;

import alioth.mrsheng.space.Environment;
import alioth.mrsheng.space.core.utils.CommonUtils;
import alioth.mrsheng.space.core.utils.JacksonUtils;
import alioth.mrsheng.space.core.utils.SimpleRestClient;
import alioth.mrsheng.space.domain.AccessTokenRequest;
import alioth.mrsheng.space.domain.AccessTokenResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import alioth.mrsheng.space.service.IAccessTokenService;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class AccessTokenServiceImpl implements IAccessTokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessTokenServiceImpl.class);

    @Resource
    private SimpleRestClient simpleRestClient;

    /**
     * token
     */
    private String token;

    /**
     * token有效期
     * 单位秒
     */
    private Integer expire;

    /**
     * token失活时间点
     * 其值等于请求token时间+expire
     */
    private Long deadLine;

    /**
     * 安全窗口,当有效时长小于本值时,强制刷新token
     * 目前的值是1800,单位是秒
     * 也就是说,如果距离token失效还剩10分钟,那么会重新刷新token
     */
    private Integer safeWindow = 600;

    @Override
    public synchronized String get() {

        // 请求token时间
        Long now = System.currentTimeMillis();

        // 第一次调用或token存活时间已经小于窗口期刷新token
        if (StringUtils.isBlank(token) || (deadLine - now <= safeWindow * 1000)) {
            refreshToken(now);
        }
        return token;
    }

    /**
     * 强制刷新token
     */
    private void refreshToken(Long now) {
        try {
            String tokenResponse = simpleRestClient.get(Environment.TOKEN_URL, new AccessTokenRequest());
            LOGGER.info("refreshToken response{}", tokenResponse);
            if (StringUtils.isBlank(tokenResponse)) {
                LOGGER.error("refreshToken response is empty");
                return;
            }
            AccessTokenResponse response = JacksonUtils.stringToBean(tokenResponse, AccessTokenResponse.class);
            if (null != response && StringUtils.isBlank(response.getErrorCode())) {
                token = response.getToken();
                expire = response.getExpires();
                deadLine = now + expire * 1000;
            } else {
                LOGGER.error("refreshToken logic error,{}", CommonUtils.toString(response));
            }
        } catch (IOException e) {
            LOGGER.error("refreshToken error:", e);
        }
    }
}
