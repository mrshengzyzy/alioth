package alioth.mrsheng.space.core.utils;

import alioth.mrsheng.space.Environment;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CommonUtils {

    /**
     * 重写的toString方法
     * 优先返回Json:{"name":"John Doe", "age":33, "smoker":false}
     * 异常则返回普通字符串:Person[name=John Doe,age=33,smoker=false]
     */
    public static String toString(Object source) {
        if (null == source) {
            return "null";
        }
        try {
            return ObjectMapperFactory.getObjectMapper().writeValueAsString(source);
        } catch (Exception e) {
            return ToStringBuilder.reflectionToString(source, ToStringStyle.SHORT_PREFIX_STYLE);
        }
    }

    /**
     * 使用uuid返回随机字符串
     *
     * @param length 返回字符串长度
     * @
     */
    public static String uuid(Integer length) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid.substring(0, length).toUpperCase();
    }

    public static String uuid() {
        return uuid(32);
    }

    /**
     * 将时间戳转换为可读格式
     */
    public static String timestampToDateString(String timestamp) {
        return StringUtils.isBlank(timestamp) ? "" : timestampToDateString(Long.valueOf(timestamp));
    }

    public static String timestampToDateString(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(Environment.ZONE);
        return sdf.format(new Date(timestamp));
    }
}
