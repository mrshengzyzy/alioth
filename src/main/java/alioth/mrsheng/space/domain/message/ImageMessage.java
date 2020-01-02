package alioth.mrsheng.space.domain.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

/**
 * 图片消息
 */
public class ImageMessage extends Message {

    ///////////////////////////////////////
    // 接收消息格式
    ///////////////////////////////////////

    // 图片URL,由系统生成
    @JacksonXmlCData
    @JsonProperty("PicUrl")
    private String picUrl;

    // 图片消息媒体id,可以调用多媒体文件下载接口拉取数据
    @JacksonXmlCData
    @JsonProperty("MediaId")
    private String mediaId;


    ///////////////////////////////////////
    // 回复消息格式
    ///////////////////////////////////////

    @JsonProperty("Image")
    private Image image;

    public static class Image {

        @JacksonXmlCData
        @JsonProperty("MediaId")
        private String mediaId;

        public String getMediaId() {
            return mediaId;
        }

        public void setMediaId(String mediaId) {
            this.mediaId = mediaId;
        }
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
