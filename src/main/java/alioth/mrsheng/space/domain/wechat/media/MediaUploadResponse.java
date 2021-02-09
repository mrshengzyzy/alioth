package alioth.mrsheng.space.domain.wechat.media;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 上传素材后的响应对象
 */
public class MediaUploadResponse {

    // 素材类型
    private String type;

    // 素材ID
    @JsonProperty("media_id")
    private String mediaId;

    // 创建时间戳
    @JsonProperty("created_at")
    private String createAt;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
}
