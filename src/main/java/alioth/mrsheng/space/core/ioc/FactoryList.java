package alioth.mrsheng.space.core.ioc;

import java.util.List;

/**
 * 实现选择Bean的功能
 * 当向Spring注入一个List<InterfaceClass>时,请求将被送往所有的实现类
 * 然后在各个实现类的开始判断是否真正需要本类处理
 * 而是用了FactoryList后,可以先判断由哪个bean处理
 */
public interface FactoryList<E extends MatchingBean<K>, K> extends List<E> {

    /**
     * 返回特定的Bean
     * 确切的来说,返回的是执行 {@link MatchingBean#match(Object)} 方法返回true的Bean
     */
    E getBean(K selector);
}
