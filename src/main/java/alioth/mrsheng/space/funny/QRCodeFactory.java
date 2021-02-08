package alioth.mrsheng.space.funny;

import cn.hutool.core.lang.UUID;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成二维码
 * 参考:
 * https://coolshell.cn/articles/10590.html
 */
public class QRCodeFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(QRCodeFactory.class);

    // 二维码颜色
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private static final String UTF8 = "UTF-8";

    // 二维码图片格式
    private static final String FORMAT = "png";

    /**
     * 默认生成200*200的二维码
     */
    public static File get(String text) {
        return get(text, 200, 200);
    }

    public static File get(String text, int width, int height) {
        try {

            // 设置内容字符编码,二维码边框宽度
            Map<EncodeHintType, String> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, UTF8);
            hints.put(EncodeHintType.MARGIN, "1");

            // 生成的BitMatrix是Zxing库定义的一个二维码的数据类
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);

            // 生成临时文件并写入img文件夹
            String directory = ResourceUtils.getURL("classpath:").getPath();
            String imgDirectory = directory + "static" + File.separator + "img";
            File file = File.createTempFile(UUID.fastUUID().toString(true), ".png", new File(imgDirectory));
            writeToFile(bitMatrix, FORMAT, file);
            return file;
        } catch (Exception e) {
            LOGGER.error("Create QR Code error:", e);
        }
        return null;
    }

    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    private static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }
}
