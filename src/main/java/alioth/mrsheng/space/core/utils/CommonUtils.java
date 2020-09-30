package alioth.mrsheng.space.core.utils;

import alioth.mrsheng.space.Environment;
import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CommonUtils {

    /**
     * 对方解密的时候需要使用相同的模式和补码方式
     * 密钥支持三种密钥长度：128、192、256位
     */
    private static AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, Environment.FISH_SALT.getBytes());

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

    /**
     * AES加密
     */
    public static String aesEncrypt(String data) {
        return Base64.encodeUrlSafe(aes.encrypt(data));
    }

    /**
     * AES解密
     */
    public static String aesDecrypt(String data) {
        return aes.decryptStr(Base64.decode(data));
    }
}
