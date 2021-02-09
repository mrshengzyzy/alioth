package alioth.mrsheng.space.domain.wechat.event;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 普通菜单或者跳转菜单点击事件
 */
public class ClickViewEvent extends Event {

    // 指菜单ID,如果是个性化菜单,则可以通过这个字段,知道是哪个菜单被点击
    @JsonProperty("MenuID")
    private String menuID;

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }
}
