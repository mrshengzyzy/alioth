package alioth.mrsheng.space.core.ioc;

/**
 * 是否可以匹配Bean
 */
public interface MatchingBean<T> {

    /**
     * 根据入参返回该类是否能够匹配当前业务逻辑
     *
     * @param selector 匹配标识
     */
    boolean match(T selector);
}
