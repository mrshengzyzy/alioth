package alioth.mrsheng.space.business.fish;

import alioth.mrsheng.space.Environment;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class FishDBHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(FishDBHelper.class);

    // 数据文件地址
    private static final String dbFile = Environment.FISH_DB;

    private static final String AT = "@";

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * 1 将原始行标记为无效
     * 2 文件末尾追加数据
     */
    public static void update(FishFood fishFood) {
        try (RandomAccessFile file = new RandomAccessFile(dbFile, "rw")) {
            long preLinePointer;
            while (true) {

                // 记录上一次指针位置
                preLinePointer = file.getFilePointer();

                // 读取一行
                String str = file.readLine();
                if (str == null) {
                    break;
                }

                // 忽略无效行
                if (StringUtils.isBlank(str.trim()) || str.startsWith(AT)) {
                    continue;
                }

                // 提取对象并比较
                FishFood old = dbToBean(str);
                if (fishFood.getFood().equalsIgnoreCase(old.getFood())) {

                    // 指针移动到上一行,写入删除符,删除符号将覆盖第一个字符
                    file.seek(preLinePointer);
                    file.write(AT.getBytes());

                    // 指针移动到文件末尾,追加修改过后的数据
                    file.seek(file.length());
                    file.write((beanToDb(fishFood) + LINE_SEPARATOR).getBytes());

                    break;
                }
            }
        } catch (Exception e) {
            LOGGER.error("update error:", e);
        }
    }

    /**
     * 文件末尾追加新行
     */
    public static void write(FishFood fishFood) {
        try (RandomAccessFile file = new RandomAccessFile(dbFile, "rw")) {

            // 指针移动到文件末尾,追加数据
            file.seek(file.length());
            file.write((beanToDb(fishFood) + LINE_SEPARATOR).getBytes());
        } catch (Exception e) {
            LOGGER.error("write error:", e);
        }
    }

    /**
     * 读取所有数据
     */
    public static List<FishFood> readAll() {
        ArrayList<FishFood> list = new ArrayList<>();
        try (RandomAccessFile file = new RandomAccessFile(dbFile, "r")) {
            String str;
            while ((str = file.readLine()) != null) {

                // 忽略无效行
                if (StringUtils.isBlank(str.trim()) || str.startsWith(AT)) {
                    continue;
                }

                // 否则是真正有效的数据
                list.add(dbToBean(str));
            }
        } catch (Exception e) {
            LOGGER.error("readAll error:", e);
        }
        return list;
    }

    /**
     * 读取特定数据
     */
    public static FishFood read(String food) {
        try (RandomAccessFile file = new RandomAccessFile(dbFile, "r")) {
            String str;
            while ((str = file.readLine()) != null) {

                // 忽略无效行
                if (StringUtils.isBlank(str.trim()) || str.startsWith(AT)) {
                    continue;
                }

                // 还原数据
                FishFood old = dbToBean(str);
                if (food.equalsIgnoreCase(old.getFood())) {
                    return old;
                }
            }
        } catch (Exception e) {
            LOGGER.error("read error:", e);
        }
        return null;
    }

    private static String beanToDb(FishFood fishFood) {
        return fishFood.getFood() + AT + fishFood.getTimestamp() + AT + fishFood.getUseTimestamp() + AT + fishFood.getStatus();
    }

    private static FishFood dbToBean(String db) {
        String[] arr = db.split(AT);
        return new FishFood(arr[0], arr[1], arr[2], arr[3]);
    }
}
