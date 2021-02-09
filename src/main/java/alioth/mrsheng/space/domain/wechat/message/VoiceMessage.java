package alioth.mrsheng.space.domain.wechat.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

/**
 * 语音消息
 */
public class VoiceMessage extends Message {

    // 语音格式,如amr,speex等
    @JacksonXmlCData
    @JsonProperty("Format")
    private String format;

    // 语音消息媒体id,可以调用多媒体文件下载接口拉取数据
    @JacksonXmlCData
    @JsonProperty("MediaId")
    private String mediaId;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
