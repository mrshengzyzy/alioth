package alioth.mrsheng.space.core.wxaes;

import alioth.mrsheng.space.Environment;
import org.springframework.beans.factory.FactoryBean;

public class WXBizMsgCryptFactory implements FactoryBean<WXBizMsgCrypt> {

    private static WXBizMsgCrypt wxBizMsgCrypt;

    private static synchronized void init() throws Exception {
        if (null == wxBizMsgCrypt) {
            wxBizMsgCrypt = new WXBizMsgCrypt(Environment.WECHAT_TOKEN, Environment.WECHAT_KEY, Environment.WECHAT_APP_ID);
        }
    }

    @Override
    public WXBizMsgCrypt getObject() throws Exception {
        if (null == wxBizMsgCrypt) {
            init();
        }
        return wxBizMsgCrypt;
    }

    @Override
    public Class<?> getObjectType() {
        return WXBizMsgCrypt.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
