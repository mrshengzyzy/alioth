package alioth.mrsheng.space.domain.wechat.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

/**
 * 文本信息
 */
public class TextMessage extends Message {

    // 消息文本内容
    @JacksonXmlCData
    @JsonProperty("Content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
