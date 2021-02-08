package cn.hutool.http;

import org.junit.Test;

public class HttpTest {

    @Test
    public void refreshToken() {
        String body = HttpUtil.get("https://www.ithome.com/");
        System.out.println(body);
    }
}
