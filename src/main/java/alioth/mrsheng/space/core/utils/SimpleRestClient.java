package alioth.mrsheng.space.core.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 一个简单的http请求客户端
 */
@Service
public class SimpleRestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleRestClient.class);

    /**
     * GET方法调用
     */
    public String get(String address, Object data) {
        return call(address, "GET", data);
    }

    /**
     * POST方法调用
     */
    public String post(String address, Object data) {
        return call(address, "POST", data);
    }

    private String call(String url, String method, Object data) {

        // 连接超时时长默认5秒
        try (CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(RequestConfig.custom().setConnectTimeout(5000).build()).build()) {

            // 获取参数
            HttpRequestBase request = getRequestByMethod(url, method, data);

            // 执行并获取结果
            CloseableHttpResponse response = httpClient.execute(request);

            // 成功响应的话以字符串方式返回结果
            if (null != response && response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            LOGGER.error("invoke http client error:", e);
        }

        return null;
    }

    /**
     * 根据入参生成请求对象
     *
     * @param url     请求地址
     * @param method  请求方式
     * @param request 请求参数
     */
    @SuppressWarnings("unchecked")
    private HttpRequestBase getRequestByMethod(String url, String method, Object request) throws Exception {

        String realUrl;
        if ("GET".equals(method)) {
            if (null != request) {
                StringBuilder sb = new StringBuilder("?");

                // 将对象转换为Map
                Map<String, Object> paramMap = JacksonUtils.stringToBean(JacksonUtils.beanToString(request), Map.class);

                // 遍历map拼接参数
                paramMap.forEach((key, value) -> sb.append(key).append("=").append(value.toString()).append("&"));

                realUrl = url + sb.substring(0, sb.length() - 1);
            } else {
                realUrl = url;
            }
            LOGGER.info("SimpleRestClient get url[{}]", realUrl);
            return new HttpGet(realUrl);
        }

        if ("POST".equals(method)) {
            HttpPost post = new HttpPost(url);
            StringEntity stringEntity = new StringEntity(JacksonUtils.beanToString(request), ContentType.create("application/json", "UTF-8"));
            post.setEntity(stringEntity);
            return post;
        }

        throw new UnsupportedOperationException("http method[" + method + "] not supported");
    }
}
