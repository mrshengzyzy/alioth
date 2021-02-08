package alioth.mrsheng.space.domain.fish;

public class FishFood {

    private String id;

    // 鱼食生成时间戳,单位秒
    private long createTimestamp;

    // 鱼食投放时间戳,单位秒
    private long useTimestamp;

    // 状态
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public long getUseTimestamp() {
        return useTimestamp;
    }

    public void setUseTimestamp(long useTimestamp) {
        this.useTimestamp = useTimestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
