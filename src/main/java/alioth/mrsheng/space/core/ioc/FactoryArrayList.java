package alioth.mrsheng.space.core.ioc;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.OrderComparator;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * 可以作为注入列表的代替
 * 当一个接口有多个时间类时,通过注入接口的方式可以将请求分配到所有的实现类中处理
 * 但是有时候我们并不需要每个实现类都进行处理,解决方法之一是在实现类中进行判断是否需要本类处理
 * 还有一种就是当前实现的方法,在注入的时候先选择合适的实现类在注入
 * 原理就是接口实现 MatchingBean 之后放置到 List中,取出的时候现行判断 MatchingBean 中的match方法是否满足需求
 */
public class FactoryArrayList<E extends MatchingBean<K>, K> extends ArrayList<E> implements FactoryList<E, K>, InitializingBean {

    public FactoryArrayList() {
        super();
    }

    public FactoryArrayList(int size) {
        super(size);
    }

    @Override
    public E getBean(K factor) {
        for (E bean : this) {
            // 返回第一个匹配的Bean
            if (bean.match(factor)) {
                return bean;
            }
        }
        return null;
    }

    /**
     * 每次检测到FactoryList变化都更新自身
     */
    @SuppressWarnings("unchecked")
    public void afterPropertiesSet() {
        if (!isEmpty()) {
            Object[] a = toArray();
            OrderComparator.sort(a);
            ListIterator i = listIterator();
            for (Object anA : a) {
                i.next();
                i.set(anA);
            }
        }
    }
}
