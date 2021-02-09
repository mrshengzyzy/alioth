package alioth.mrsheng.space.domain.wechat.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

import java.util.List;

/**
 * 系统拍照或者微信相册选图事件
 */
public class SystemPhotoAlbumEvent extends Event {

    // 发送的图片信息
    @JsonProperty("SendPicsInfo")
    private SendPicsInfo sendPicsInfo;

    public SendPicsInfo getSendPicsInfo() {
        return sendPicsInfo;
    }

    public void setSendPicsInfo(SendPicsInfo sendPicsInfo) {
        this.sendPicsInfo = sendPicsInfo;
    }

    public static class SendPicsInfo {

        // 图片列表
        @JsonProperty("PicList")
        private PicList picList;

        // 发送的图片数量
        @JsonProperty("Count")
        private Integer count;

        public PicList getPicList() {
            return picList;
        }

        public void setPicList(PicList picList) {
            this.picList = picList;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }

    public static class PicList {

        @JsonProperty("item")
        private List<PicListItem> item;

        public List<PicListItem> getItem() {
            return item;
        }

        public void setItem(List<PicListItem> item) {
            this.item = item;
        }
    }

    public static class PicListItem {

        // 图片的MD5值，开发者若需要，可用于验证接收到图片
        @JacksonXmlCData
        @JsonProperty("PicMd5Sum")
        private String picMd5Sum;

        public String getPicMd5Sum() {
            return picMd5Sum;
        }

        public void setPicMd5Sum(String picMd5Sum) {
            this.picMd5Sum = picMd5Sum;
        }
    }
}
