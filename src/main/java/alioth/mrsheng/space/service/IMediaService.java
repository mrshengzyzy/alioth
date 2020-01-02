package alioth.mrsheng.space.service;

import alioth.mrsheng.space.domain.media.MediaType;

import java.io.File;

/**
 * 素材业务
 */
public interface IMediaService {

    /**
     * 上传临时素材并返回素材ID
     *
     * @param type 素材类型,参照{@link MediaType}配置
     * @param file 需要上传的文件
     */
    String uploadTempMedia(String type, File file);

}
