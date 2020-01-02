package alioth.mrsheng.space.funny;

import alioth.mrsheng.space.core.utils.CommonUtils;
import alioth.mrsheng.space.core.utils.JacksonUtils;
import cn.hutool.http.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * 国际空间站实时位置
 * 从以下两个地址读取到更多信息
 * http://www.celestrak.com/NORAD/elements/stations.txt
 * https://spaceflight.nasa.gov/realdata/sightings/SSapplications/Post/JavaSSOP/orbit/ISS/SVPOST.html
 */
public class IssFunny {

    private static final Logger LOGGER = LoggerFactory.getLogger(IssFunny.class);

    /**
     * 返回一对坐标
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> get() {
        try {
            String response = HttpUtil.get("http://api.open-notify.org/iss-now.json");
            Map result = JacksonUtils.stringToBean((response), Map.class);
            Map<String, String> resultMap = new HashMap(2);
            resultMap.put("locationX", (String) ((Map) result.get("iss_position")).get("latitude"));
            resultMap.put("locationY", (String) ((Map) result.get("iss_position")).get("longitude"));
            return resultMap;
        } catch (Exception e) {
            LOGGER.error("Get ISS location error:", e);
        }
        return null;
    }

    public static File getPic() {
        try {

            // 临时文件
            String directory = ResourceUtils.getURL("classpath:").getPath();
            String imgDirectory = directory + "static" + File.separator + "img";
            File file = File.createTempFile(CommonUtils.uuid(5), ".png", new File(imgDirectory));

            // 图片地址
            URL url = new URL("http://www.heavens-above.com/orbitdisplay.aspx?width=400&height=400");
            URLConnection con = url.openConnection();

            // 准备写入文件
            InputStream is = con.getInputStream();
            OutputStream os = new FileOutputStream(file);
            byte[] bs = new byte[8192];
            int len;
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }

            // 不要忘记关闭连接
            os.close();
            is.close();

            return file;
        } catch (Exception e) {
            LOGGER.error("Get ISS location pic error:", e);
        }
        return null;
    }
}
