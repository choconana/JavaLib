package threadcoreknowledge.synchronizedPractise.obj_class;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hezhidong
 * @date 2021/7/6 下午1:32
 * @description
 */
public class ObjOrClaz implements Runnable {

    static int i = 0;

    static int n = 0;

    static AtomicInteger ai = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ObjOrClaz o1 = new ObjOrClaz();
        ObjOrClaz o2 = new ObjOrClaz();
        ObjOrClaz o3 = new ObjOrClaz();
        Thread t1 = new Thread(o1);
        Thread t2 = new Thread(o2);
        Thread t3 = new Thread(o3);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println(ai);
        System.out.println(i);
        System.out.println(n);
    }
    public void objIncr() {
        synchronized (this) {
            i++;
        }
    }

    public void clazIncr() {
        synchronized (ObjOrClaz.class) {
            n++;
        }
    }

    @Override
    public void run() {
        for (int j = 0; j < 100000; j++) {
            ai.incrementAndGet();
            objIncr();
            clazIncr();
        }
    }
}
