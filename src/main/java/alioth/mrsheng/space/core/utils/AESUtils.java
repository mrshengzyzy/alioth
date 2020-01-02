package alioth.mrsheng.space.core.utils;

import alioth.mrsheng.space.Environment;
import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;

/**
 * AES工具类
 * 用于简单的加解密
 */
public class AESUtils {

    /**
     * 对方解密的时候需要使用相同的模式和补码方式
     * token和salt设置为相同的值,简化编码
     */
    private static AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, Environment.FISH_SALT.getBytes(), Environment.FISH_SALT.getBytes());

    /**
     * 加密
     * 注意salt长度必须为16
     */
    public static String encrypt(String data) {
        return Base64.encodeUrlSafe(aes.encrypt(data));
    }

    /**
     * 解密
     * 注意salt长度必须为16
     */
    public static String decrypt(String data) {
        return aes.decryptStr(Base64.decode(data));
    }
}
