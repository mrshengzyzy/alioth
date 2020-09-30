package cn.hutool.http;

import org.junit.Test;

public class HttpTest {

    @Test
    public void httpUtil() {
        String body = HttpUtil.get("http://c.ishadowx.com/");
        System.out.println(body);
    }
}
