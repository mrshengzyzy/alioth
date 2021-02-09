package alioth.mrsheng.space.domain.wechat.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

/**
 * 连接信息
 */
public class LinkMessage extends Message {

    // 消息标题
    @JacksonXmlCData
    @JsonProperty("Title")
    private String title;

    // 消息描述
    @JacksonXmlCData
    @JsonProperty("Description")
    private String description;

    // 消息链接
    @JacksonXmlCData
    @JsonProperty("Url")
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
