package cache.computale;

/**
 * @Description: 耗时计算的实现类，实现了Computable接口，但是本身不具备缓存能力，也不需要考虑缓存的事情
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/31 8:48
 */
public class ExpensiveFunction implements Computable<String, Integer> {

    @Override
    public Integer compute(String arg) throws Exception {
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName()+"开始计算");
        return Integer.valueOf(arg);
    }
}
