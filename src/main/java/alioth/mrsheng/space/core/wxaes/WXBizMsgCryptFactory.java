package alioth.mrsheng.space.core.wxaes;

import alioth.mrsheng.space.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

/**
 * WXBizMsgCrypt工厂
 * 用来注入到Spring中
 */
public class WXBizMsgCryptFactory implements FactoryBean<WXBizMsgCrypt> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WXBizMsgCryptFactory.class);

    private static WXBizMsgCrypt wxBizMsgCrypt;

    private static synchronized void init() throws Exception {
        if (null == wxBizMsgCrypt) {
            wxBizMsgCrypt = new WXBizMsgCrypt(Environment.TOKEN, Environment.KEY, Environment.APP_ID);
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
