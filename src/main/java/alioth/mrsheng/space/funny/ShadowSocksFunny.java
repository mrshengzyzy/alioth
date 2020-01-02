package alioth.mrsheng.space.funny;

import alioth.mrsheng.space.core.utils.HttpsSkipSSLClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 爬取 ishadowx.com 并返回一个SS地址
 */
public class ShadowSocksFunny {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShadowSocksFunny.class);

    /**
     * 返回一个Map格式的SS地址
     * 包含服务器名称、端口号、密码、加密类型、有效期（单位小时）
     */
    public static Map<String, String> get() {
        try {
            Map<String, String> map = new HashMap<>();

            // 创建httpClient对象
            CloseableHttpClient client = HttpsSkipSSLClient.get();

            // 创建GET方式请求对象
            HttpGet httpGet = new HttpGet("http://c.ishadowx.com/");

            // 执行请求操作,并拿到结果(同步阻塞)
            CloseableHttpResponse response = client.execute(httpGet);

            // 获取结果实体
            String html = EntityUtils.toString(response.getEntity());

            // 调用JSoup转换为文档
            Element body = Jsoup.parse(html).body();

            // 获取所有免费节点
            Elements elements = body.getElementsByClass("portfolio-item");

            // 随机获取一个节点
            int index = (int) (Math.random() * (elements.size()));
            Element element = elements.get(index);

            // 筛选所有配置信息
            Elements h4 = element.select("h4");
            for (Element el : h4) {
                // 选取配置值填充到Map中
                String text = el.text();
                if (text.contains(":")) {
                    String config = text.split(":")[1];
                    if (text.startsWith("IP Address:")) {
                        map.put("address", config);
                    } else if (text.startsWith("Port:")) {
                        map.put("port", config);
                    } else if (text.startsWith("Password:")) {
                        map.put("password", config);
                    } else if (text.startsWith("Method:")) {
                        map.put("method", config);
                    }
                }
            }

            // 查找有效期,默认6小时
            if (map.size() != 0) {
                Elements valid = body.getElementsMatchingOwnText("change its password in every");
                if (!CollectionUtils.isEmpty(valid)) {
                    String text = valid.get(0).text();
                    String hour = text.substring(text.indexOf("in every") + 8, text.indexOf("in every") + 11).trim();
                    map.put("valid", hour);
                } else {
                    map.put("valid", "6");
                }
            }
            return map;
        } catch (Exception e) {
            LOGGER.error("get shadow socks server error", e);
        }
        return null;
    }

    /**
     * 返回一个二维码配置的SS地址
     * 参考：
     * https://blog.csdn.net/lecepin/article/details/52063843
     */
    public static File getQRCode() {

        // 获取SS地址
        Map<String, String> map = get();
        if (map == null) {
            return null;
        }

        // 协议格式为：ss://method[-auth]:password@hostname:port
        String protocol = map.get("method") + ":" + map.get("password") + "@" + map.get("address") + ":" + map.get("port");
        LOGGER.info("convert 2 qrcode info {}", protocol);

        // ss://BASE64-ENCODED-STRING-WITHOUT-PADDING
        String baseProtocol = "ss://" + Base64.getEncoder().withoutPadding().encodeToString(protocol.getBytes());

        // 返回二维码文件
        return QRCodeFactory.get(baseProtocol);
    }
}
