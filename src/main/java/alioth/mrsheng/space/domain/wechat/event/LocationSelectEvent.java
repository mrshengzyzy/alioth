package alioth.mrsheng.space.domain.wechat.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

/**
 * 地理位置选择器的事件推送消息
 */
public class LocationSelectEvent extends Event {

    @JsonProperty("SendLocationInfo")
    private SendLocationInfo sendLocationInfo;

    public SendLocationInfo getSendLocationInfo() {
        return sendLocationInfo;
    }

    public void setSendLocationInfo(SendLocationInfo sendLocationInfo) {
        this.sendLocationInfo = sendLocationInfo;
    }

    public static class SendLocationInfo {

        // 地理位置维度
        @JacksonXmlCData
        @JsonProperty("Location_X")
        private String locationX;

        // 地理位置经度
        @JacksonXmlCData
        @JsonProperty("Location_Y")
        private String locationY;

        // 精度，可理解为精度或者比例尺、越精细的话 scale越高
        @JacksonXmlCData
        @JsonProperty("Scale")
        private String scale;

        // 地理位置的字符串信息
        @JacksonXmlCData
        @JsonProperty("Label")
        private String label;

        // 朋友圈POI的名字，可能为空
        @JacksonXmlCData
        @JsonProperty("Poiname")
        private String PoiName;

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

        public String getPoiName() {
            return PoiName;
        }

        public void setPoiName(String poiName) {
            PoiName = poiName;
        }
    }
}
