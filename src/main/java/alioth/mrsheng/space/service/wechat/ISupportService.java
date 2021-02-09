package alioth.mrsheng.space.service.wechat;

import alioth.mrsheng.space.domain.wechat.media.MediaType;

import java.io.File;

public interface ISupportService {

    /**
     * 上传临时素材并返回素材ID
     *
     * @param type 素材类型,参照{@link MediaType}配置
     * @param file 需要上传的文件
     */
    String uploadTempMedia(String type, File file);

    /**
     * 获取一个access_token
     * 1.单实例部署可用,如果多实例部署需要将token放置在公共缓存服务器中
     * 2.是一个同步方法
     */
    String getAccessToken() throws Exception;
}
