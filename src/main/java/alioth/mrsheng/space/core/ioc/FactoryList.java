package alioth.mrsheng.space.core.ioc;

import java.util.List;

/**
 * 实现选择 Bean 的功能
 * 向 Spring 注入一个 List<InterfaceClass> 时,请求将被送往所有的实现类,由各个实现类判断是否真正需要处理本次请求
 */
public interface FactoryList<E extends MatchingBean<K>, K> extends List<E> {

    /**
     * 返回特定的Bean
     * 确切的来说,返回的是执行 {@link MatchingBean#match(Object)} 方法返回true的Bean
     */
    E getBean(K selector);
}
