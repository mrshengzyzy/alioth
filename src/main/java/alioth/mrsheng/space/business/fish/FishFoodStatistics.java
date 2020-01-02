package alioth.mrsheng.space.business.fish;

import alioth.mrsheng.space.core.utils.CommonUtils;

import java.util.List;

public class FishFoodStatistics {

    // 总数
    private int totalCount;

    // 排队中
    private int queueCount;

    // 未投放
    private int unusedCount;

    // 已投放
    private int usedCount;

    // 详情信息
    private List<Bean> beanList;

    static class Bean {

        // 序号,跟ID不同,仅用来展示条数
        private int index;

        private String food;

        private String createDateTime;

        private String useDateTime;

        private String status;

        private String statusDesc;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getFood() {
            return food;
        }

        public void setFood(String food) {
            this.food = food;
        }

        public String getCreateDateTime() {
            return createDateTime;
        }

        public void setCreateDateTime(String createDateTime) {
            this.createDateTime = createDateTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUseDateTime() {
            return useDateTime;
        }

        public void setUseDateTime(String useDateTime) {
            this.useDateTime = useDateTime;
        }

        public String getStatusDesc() {
            return statusDesc;
        }

        public void setStatusDesc(String statusDesc) {
            this.statusDesc = statusDesc;
        }
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getQueueCount() {
        return queueCount;
    }

    public void setQueueCount(int queueCount) {
        this.queueCount = queueCount;
    }

    public int getUnusedCount() {
        return unusedCount;
    }

    public void setUnusedCount(int unusedCount) {
        this.unusedCount = unusedCount;
    }

    public int getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(int usedCount) {
        this.usedCount = usedCount;
    }

    public List<Bean> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<Bean> beanList) {
        this.beanList = beanList;
    }

    @Override
    public String toString() {
        return CommonUtils.toString(this);
    }
}
