package alioth.mrsheng.space.funny;

import cn.hutool.http.HttpUtil;

/**
 * 一言 API
 */
public class HitokotoFunny {

    public static String get() {
        return HttpUtil.get("https://international.v1.hitokoto.cn/?encode=text");
    }
}
