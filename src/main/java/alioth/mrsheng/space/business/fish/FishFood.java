package alioth.mrsheng.space.business.fish;

import alioth.mrsheng.space.core.utils.CommonUtils;

import java.util.HashMap;
import java.util.Map;

public class FishFood {

    // 0未投放 1已投放 2 排队中
    public static String UNUSE = "0";
    public static String UNUSE_DESC = "未投放";
    public static String USED = "1";
    public static String USED_DESC = "已投放";
    public static String QUEUE = "2";
    public static String QUEUE_DESC = "排队中";

    public static final Map<String, String> MAPPER = new HashMap<String, String>() {{
        put(UNUSE, UNUSE_DESC);
        put(USED, USED_DESC);
        put(QUEUE, QUEUE_DESC);
    }};

    // 编号
    private String food;

    // 生成时间,是个时间戳,单位毫秒
    private String timestamp;

    // 投放时间
    private String useTimestamp;

    // 状态
    private String status;

    public FishFood() {

    }

    public FishFood(String food) {
        this.food = food;
        this.timestamp = String.valueOf(System.currentTimeMillis());
        this.useTimestamp = "";
        this.status = UNUSE;
    }

    public FishFood(String food, String timestamp, String useTimestamp, String use) {
        this.food = food;
        this.timestamp = timestamp;
        this.useTimestamp = useTimestamp;
        this.status = use;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUseTimestamp() {
        return useTimestamp;
    }

    public void setUseTimestamp(String useTimestamp) {
        this.useTimestamp = useTimestamp;
    }

    @Override
    public String toString() {
        return CommonUtils.toString(this);
    }
}
