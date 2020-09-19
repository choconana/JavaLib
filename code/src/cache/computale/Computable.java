package cache.computale;

/**
 * @Description: 有一个计算函数computer，用来代表耗时计算，每个计算器都要实现这个接口，这样就可以无侵入实现缓存功能
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/31 8:43
 */
public interface Computable<A, V> {
    V compute(A arg) throws Exception;
}
