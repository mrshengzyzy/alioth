package alioth.mrsheng.space.domain.wechat.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

/**
 * 视频消息
 */
public class VideoMessage extends Message {

    // 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
    @JacksonXmlCData
    @JsonProperty("ThumbMediaId")
    private String thumbMediaId;

    // 视频消息媒体id，可以调用多媒体文件下载接口拉取数据
    @JacksonXmlCData
    @JsonProperty("MediaId")
    private String mediaId;

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
