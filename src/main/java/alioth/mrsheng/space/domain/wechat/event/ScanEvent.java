package alioth.mrsheng.space.domain.wechat.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

/**
 * 扫码事件消息
 */
public class ScanEvent extends Event {

    // 扫描信息包装类
    @JsonProperty("ScanCodeInfo")
    private ScanInfo ScanCodeInfo;

    public static class ScanInfo {

        // 扫描类型,一般是qrcode
        @JacksonXmlCData
        @JsonProperty("ScanType")
        private String ScanType;

        // 扫描结果,即二维码对应的字符串信息
        @JacksonXmlCData
        @JsonProperty("ScanResult")
        private String ScanResult;

        public String getScanType() {
            return ScanType;
        }

        public void setScanType(String scanType) {
            ScanType = scanType;
        }

        public String getScanResult() {
            return ScanResult;
        }

        public void setScanResult(String scanResult) {
            ScanResult = scanResult;
        }
    }

    public ScanInfo getScanCodeInfo() {
        return ScanCodeInfo;
    }

    public void setScanCodeInfo(ScanInfo scanCodeInfo) {
        ScanCodeInfo = scanCodeInfo;
    }
}
