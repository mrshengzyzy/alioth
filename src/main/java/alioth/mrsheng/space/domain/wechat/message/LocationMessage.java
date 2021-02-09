package alioth.mrsheng.space.domain.wechat.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

/**
 * 地理位置消息
 */
public class LocationMessage extends Message {

    // 地理位置维度
    @JsonProperty("Location_X")
    private String locationX;

    // 地理位置经度
    @JsonProperty("Location_Y")
    private String locationY;

    // 地图缩放大小
    // 可理解为精度或者比例尺、越精细的话 scale越高
    @JsonProperty("Scale")
    private String scale;

    // 地理位置信息
    @JacksonXmlCData
    @JsonProperty("Label")
    private String label;

    public String getLocationX() {
        return locationX;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
