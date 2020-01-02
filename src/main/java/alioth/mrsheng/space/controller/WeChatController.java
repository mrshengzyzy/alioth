package alioth.mrsheng.space.controller;

import alioth.mrsheng.space.business.IWeChatBusiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信公众平台消息入口
 */
@RestController
public class WeChatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatController.class);

    @Resource
    private IWeChatBusiness weChatBusiness;

    /**
     * GET用于验证服务器地址
     * 当消息来自微信服务器时校验通过,当消息来自网站是校验失败,返回公众号页面
     */
    @RequestMapping(value = "/wechat", method = RequestMethod.GET)
    public void weChatGet(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 签名
        String signature = request.getParameter("signature");

        // 时间戳
        String timestamp = request.getParameter("timestamp");

        // 随机数
        String nonce = request.getParameter("nonce");

        // 校验通过返回的随机串
        String echoStr = request.getParameter("echostr");

        LOGGER.info("[WeChat] Receive get message signature[{}],timestamp[{}],nonce[{}], echoStr[{}]", signature, timestamp, nonce, echoStr);

        // 调用业务端校验请求是否来自微信
        boolean pass = weChatBusiness.check(signature, timestamp, nonce);

        // 验证成功就返回传入的字符串
        if (pass) {
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write(echoStr);
        } else {
            LOGGER.info("[WeChat] check fail redirect to index");

            // 验证失败重定向到首页
            response.sendRedirect("/");
        }
    }

    /**
     * POST用于处理业务逻辑
     * 微信服务器将所有消息都发送到该地址
     */
    @RequestMapping(value = "/wechat", method = RequestMethod.POST)
    public void weChatPost(@RequestBody String message, HttpServletRequest request, HttpServletResponse response) throws Exception {

        LOGGER.info("[WeChat] Receive post message {}", message);

        // 签名
        String signature = request.getParameter("msg_signature");

        // 时间戳
        String timestamp = request.getParameter("timestamp");

        // 随机数
        String nonce = request.getParameter("nonce");

        // 业务逻辑处理
        String result = weChatBusiness.dispatch(message, signature, timestamp, nonce);

        // 返回处理结果给微信服务器,再转发给用户
        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().write(result);
    }
}
