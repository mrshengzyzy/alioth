package alioth.mrsheng.space.persistence;

import alioth.mrsheng.space.core.utils.CommonUtils;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FishFood {

    @Id
    private String id;

    // 鱼食生成时间戳,单位秒
    private long createTimestamp;

    // 鱼食投放时间戳,单位秒
    private long useTimestamp;

    // 状态
    private int status;

    public FishFood() {

    }

    public FishFood(String food) {
        this.id = food;
        this.createTimestamp = System.currentTimeMillis() / 1000;
        this.useTimestamp = 0L;
        this.status = FishFoodStatus.UNUSED.getCode();
    }

    public FishFood(String id, long createTimestamp, long useTimestamp, int use) {
        this.id = id;
        this.createTimestamp = createTimestamp;
        this.useTimestamp = useTimestamp;
        this.status = use;
    }

    public String getFood() {
        return id;
    }

    public void setFood(String id) {
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

    @Override
    public String toString() {
        return CommonUtils.toString(this);
    }
}
