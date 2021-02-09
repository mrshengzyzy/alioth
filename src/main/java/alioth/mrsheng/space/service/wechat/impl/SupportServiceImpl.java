package alioth.mrsheng.space.service.wechat.impl;

import alioth.mrsheng.space.Environment;
import alioth.mrsheng.space.core.JacksonUtils;
import alioth.mrsheng.space.domain.wechat.AccessTokenResponse;
import alioth.mrsheng.space.domain.wechat.media.MediaUploadResponse;
import alioth.mrsheng.space.service.wechat.ISupportService;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class SupportServiceImpl implements ISupportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SupportServiceImpl.class);

    /**
     * 判断是否该刷新 access_token
     * expire 代表 token 过期时长
     * deadLine 代表 token 失效时间点,其值= requestT + expire
     * safeWindow 代表安全窗口期,一旦 token 进入窗口期,表示可以刷新 token
     */
    private String token;
    private Integer expire;
    private Long deadLine;
    private Integer safeWindow = 600;

    @Override
    public String uploadTempMedia(String type, File file) {
        try {

            // 素材上传地址
            String url = Environment.WECHAT_TOKEN_URL + "?access_token=" + getAccessToken() + "&type=" + type;

            // 创建httpClient客户端
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // 创建POST请求对象
            HttpPost httpPost = new HttpPost(url);

            // 设置Entity
            HttpEntity entity = MultipartEntityBuilder.create().addBinaryBody("media", file).build();
            httpPost.setEntity(entity);

            // 设置Header
            httpPost.setHeader("Content-Type", ContentType.MULTIPART_FORM_DATA.toString());

            // 获取返回结果
            CloseableHttpResponse response = httpClient.execute(httpPost);
            String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");

            // 转化为特定响应对象
            MediaUploadResponse mediaUploadResponse = JacksonUtils.stringToBean(responseBody, MediaUploadResponse.class);
            return mediaUploadResponse == null ? null : mediaUploadResponse.getMediaId();

        } catch (Exception e) {
            LOGGER.error("upload file error:", e);
        }
        return null;
    }

    @Override
    public String getAccessToken() throws Exception {
        Long now = System.currentTimeMillis();

        // 1. token 已存在
        // 2. token 位于安全窗口之外
        if (!StrUtil.isBlank(token) && (deadLine - now > safeWindow * 1000)) {
            return token;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("grant_type", "client_credential");
        params.put("appid", Environment.WECHAT_APP_ID);
        params.put("secret", Environment.WECHAT_APP_SECRET);
        String tokenResponse = HttpUtil.get(Environment.WECHAT_TOKEN_URL, params);
        if (StrUtil.isBlank(tokenResponse)) {
            throw new Exception("refreshToken response is empty");
        }

        LOGGER.info("[getAccessToken]refreshToken response: {}", tokenResponse);
        AccessTokenResponse response = JacksonUtils.stringToBean(tokenResponse, AccessTokenResponse.class);
        if (null == response || !StrUtil.isBlank(response.getErrorCode())) {
            throw new Exception(StrUtil.toString(response));
        }

        token = response.getToken();
        expire = response.getExpires();
        deadLine = now + expire * 1000;
        return token;
    }
}
