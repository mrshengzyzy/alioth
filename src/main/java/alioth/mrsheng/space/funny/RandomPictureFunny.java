package alioth.mrsheng.space.funny;

import alioth.mrsheng.space.core.utils.CommonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 随机从 picsum.photos 获取一张图片
 */
public class RandomPictureFunny {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomPictureFunny.class);

    public static File get() {
        return get(400, 200);
    }

    public static File get(int width, int height) {
        try {

            // 创建httpClient对象
            CloseableHttpClient client = HttpClients.createDefault();

            // 创建GET方式请求对象
            HttpGet httpGet = new HttpGet("https://picsum.photos/" + width + "/" + height + "?random");

            // 执行请求操作,并拿到结果(同步阻塞)
            CloseableHttpResponse response = client.execute(httpGet);

            // 获取结果实体
            HttpEntity entity = response.getEntity();

            // 获取输入流
            InputStream in = entity.getContent();

            // 创建临时文件
            File file = File.createTempFile(CommonUtils.uuid(5), ".jpg");

            // 在虚拟机终止时,请求删除此抽象路径名表示的文件或目录
            file.deleteOnExit();

            FileOutputStream fos = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            in.close();
            fos.flush();
            fos.close();
            return file;
        } catch (Exception e) {
            LOGGER.error("get random picture error:", e);
        }
        return null;
    }
}
