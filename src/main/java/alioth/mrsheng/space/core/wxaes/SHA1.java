package alioth.mrsheng.space.core.wxaes;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.util.Arrays;

class SHA1 {

    /**
     * 用 SHA1 算法生成安全签名
     *
     * @param token     票据
     * @param timestamp 时间戳
     * @param nonce     随机字符串
     * @param encrypt   密文
     * @return 安全签名
     */
    static String getSHA1(String token, String timestamp, String nonce, String encrypt) throws AesException {
        try {
            String[] array = new String[]{token, timestamp, nonce, encrypt};
            StringBuilder sb = new StringBuilder();

            // 字符串排序
            Arrays.sort(array);
            for (int i = 0; i < 4; i++) {
                sb.append(array[i]);
            }
            String str = sb.toString();

            // SHA1签名生成
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] digest = md.digest();

            StringBuilder hesStr = new StringBuilder();
            String shaHex;
            for (byte b : digest) {
                shaHex = Integer.toHexString(b & 0xFF);
                if (shaHex.length() < 2) {
                    hesStr.append(0);
                }
                hesStr.append(shaHex);
            }
            return hesStr.toString();
        } catch (Exception e) {
            throw new AesException(AesException.ComputeSignatureError);
        }
    }

    /**
     * 用 SHA1 算法生成安全签名
     *
     * @param token     票据
     * @param timestamp 时间戳
     * @param nonce     随机字符串
     * @return 安全签名
     */
    static String getSHA1(String token, String timestamp, String nonce) throws AesException {
        try {

            // 字符串排序
            String[] arr = new String[]{token, timestamp, nonce};
            Arrays.sort(arr);

            // 字符串拼接
            StringBuilder content = new StringBuilder();
            for (String anArr : arr) {
                content.append(anArr);
            }

            // 计算 SHA1
            return DigestUtils.sha1Hex(content.toString());
        } catch (Exception e) {
            throw new AesException(AesException.ComputeSignatureError);
        }
    }
}
