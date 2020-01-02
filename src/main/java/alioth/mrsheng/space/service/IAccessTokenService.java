package alioth.mrsheng.space.service;

/**
 * AccessToken服务
 */
public interface IAccessTokenService {

    /**
     * 获取一个access_token
     * 1.单实例部署可用,如果多实例部署需要将token放置在公共缓存服务器中
     * 2.是一个同步方法
     */
    String get();
}
