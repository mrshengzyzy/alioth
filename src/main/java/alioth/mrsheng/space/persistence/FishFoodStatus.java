package alioth.mrsheng.space.persistence;

public enum FishFoodStatus {

    UNKNOWN(-1, "未知状态"),
    UNUSED(0, "未投放"),
    USED(1, "已投放"),
    QUEUE(2, "排队中");

    private int code;

    private String desc;

    FishFoodStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    // 通过 code 返回对应的描述信息
    public static String forDesc(int code) {
        for (FishFoodStatus status : FishFoodStatus.values()) {
            if (status.getCode() == code) {
                return status.getDesc();
            }
        }
        return UNKNOWN.getDesc();
    }
}

