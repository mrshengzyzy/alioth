package alioth.mrsheng.space.service.impl;

import alioth.mrsheng.space.Environment;
import alioth.mrsheng.space.core.utils.JacksonUtils;
import alioth.mrsheng.space.domain.media.MediaUploadResponse;
import alioth.mrsheng.space.service.IAccessTokenService;
import alioth.mrsheng.space.service.IMediaService;
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

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@Service
public class MediaBusinessImpl implements IMediaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MediaBusinessImpl.class);

    @Resource
    private IAccessTokenService tokenService;

    @Override
    public String uploadTempMedia(String type, File file) {
        try {

            // 操作地址
            String url = buildMediaURL(Environment.UPLOAD_MEDIA_URL, type);

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

        } catch (IOException e) {
            LOGGER.error("upload file error:", e);
        }
        return null;
    }

    /**
     * 实际操作URL
     */
    private String buildMediaURL(String baseURL, String type) {
        return baseURL + "?access_token=" + tokenService.get() + "&type=" + type;
    }
}
