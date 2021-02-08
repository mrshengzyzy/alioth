package alioth.mrsheng.space.domain.event;

import alioth.mrsheng.space.domain.message.MessageType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "xml")
public class Event {

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
     * 所有的事件类型的消息这个值都是固定的 event
     */
    @JacksonXmlCData
    @JsonProperty("MsgType")
    private String msgType;

    /**
     * 事件类型,参照 {@link EventType}
     */
    @JacksonXmlCData
    @JsonProperty("Event")
    private String event;

    /**
     * 事件 key 值,不同事件的 key 值含义不同
     */
    @JacksonXmlCData
    @JsonProperty("EventKey")
    private String eventKey;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
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
