package alioth.mrsheng.space;

import alioth.mrsheng.space.domain.Const;
import alioth.mrsheng.space.service.wechat.IWeChatService;
import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信公众平台消息入口
 * TODO 非微信请求一律返回 404
 */
@RestController
public class ZWeChatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZWeChatController.class);

    @Resource
    private IWeChatService weChatBusiness;

    /**
     * 验证微信服务器签名
     */
    @RequestMapping(value = "/wechat", method = RequestMethod.GET)
    public void weChatGet(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 解析认证参数
        Map<String, String> checkParam = parseCheckParams(request);
        LOGGER.info("receive get message for check: {}", StrUtil.toString(checkParam));

        // 调用业务端校验请求是否来自微信
        boolean pass = weChatBusiness.check(checkParam.get(Const.SIGNATURE), checkParam.get(Const.TIMESTAMP), checkParam.get(Const.NONCE));
        if (pass) {
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write(checkParam.get(Const.ECHOSTR));
        } else {
            LOGGER.error("signature check not pass, redirect to 404");
            response.sendRedirect("/404/");
        }
    }

    /**
     * 处理发送至公众号的消息
     */
    @RequestMapping(value = "/wechat", method = RequestMethod.POST)
    public void weChatPost(@RequestBody String message, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("receive post message:\n{}", message);

        // 业务逻辑处理
        // 返回处理结果给微信服务器,服务器会转发给用户
        Map<String, String> checkParam = parseCheckParams(request);
        LOGGER.info("receive post message for check: {}", StrUtil.toString(checkParam));
        String result = weChatBusiness.dispatch(message, checkParam.get(Const.MSG_SIGNATURE), checkParam.get(Const.TIMESTAMP), checkParam.get(Const.NONCE));
        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().write(result);
    }

    private Map<String, String> parseCheckParams(HttpServletRequest request) {

        Map<String, String> m = new HashMap<>();

        // post 消息签名
        String msgSignature = request.getParameter(Const.MSG_SIGNATURE);

        // get 消息签名
        String signature = request.getParameter(Const.SIGNATURE);

        // 时间戳
        String timestamp = request.getParameter(Const.TIMESTAMP);

        // 随机数
        String nonce = request.getParameter(Const.NONCE);

        // 校验通过返回的随机串
        String echoStr = request.getParameter(Const.ECHOSTR);

        m.put(Const.MSG_SIGNATURE, msgSignature);
        m.put(Const.SIGNATURE, signature);
        m.put(Const.TIMESTAMP, timestamp);
        m.put(Const.NONCE, nonce);
        m.put(Const.ECHOSTR, echoStr);
        return m;
    }
}
