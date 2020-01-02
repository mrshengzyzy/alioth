package alioth.mrsheng.space.domain.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 消息基类
 */
@JacksonXmlRootElement(localName = "xml")
public class Message {

    // 开发者微信号
    @JacksonXmlCData
    @JsonProperty("ToUserName")
    private String toUserName;

    // 发送方帐号(一个OpenID)
    @JacksonXmlCData
    @JsonProperty("FromUserName")
    private String fromUserName;

    // 消息创建时间 (整型)
    @JsonProperty("CreateTime")
    private Long createTime;

    /**
     * 消息类型,参照 {@link MessageType}
     */
    @JacksonXmlCData
    @JsonProperty("MsgType")
    private String msgType;

    // 消息id,64位整型
    @JsonProperty("MsgId")
    private Long msgId;

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
