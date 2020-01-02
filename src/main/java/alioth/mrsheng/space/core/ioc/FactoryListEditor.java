package alioth.mrsheng.space.core.ioc;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * 一个自定义的属性编辑器
 * 保证Factory可以自动更新其中的内容
 * 按照JSR规范将自动注入到Spring中
 */
public class FactoryListEditor extends CustomCollectionEditor {

    public FactoryListEditor() {
        super(FactoryArrayList.class);
        this.addPropertyChangeListener(new InitializingBeanListener());
    }

    private class InitializingBeanListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            Object value = ((FactoryListEditor) evt.getSource()).getValue();
            if (value instanceof InitializingBean) {
                try {
                    ((InitializingBean) value).afterPropertiesSet();
                } catch (Exception e) {
                    throw new RuntimeException("init bean afterPropertiesSet error:", e);
                }
            }
        }
    }
}
