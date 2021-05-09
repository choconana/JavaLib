package threadcoreknowledge.threadpool;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @author: hezhidong
 */
public class ThreadPoolTest {

    class A {
        Integer i;
        Integer j;
        public void setI(Integer i) {
            this.i = i;
        }

        public Integer getI() {
            return i;
        }

        public Integer getJ() {
            return j;
        }

        public void setJ(Integer j) {
            this.j = j;
        }
    }

    public boolean comp() {
        A a = new A();
        A a1 = new A();
        a.setI(1);
        a.setJ(2);
        a1.setI(1);
        a1.setJ(2);
        Set<String> set = new HashSet<>();
        set.add(a.getI() + "" + a.getJ());
        return set.contains(a1.getI() + "" + a1.getJ());
    }
    public static void main(String[] args) {
        ThreadPoolTest threadPoolTest = new ThreadPoolTest();
        System.out.println(threadPoolTest.comp());
    }
}
