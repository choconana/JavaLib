package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @Description: 两个线程交替打印0-100的奇偶数(位运算)，用synchronized关键字实现
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/12 22:22
 */
public class WaitNotifyPrintOddEvenSyn {

    private static int count;
    private static final Object lock = new Object();

    // 新建2个线程
    // 第一个只处理偶数，第二个只处理奇数（位运算）
    // 用synchronized来通信
    public static void main(String[] args) {
        new Thread( () -> {
            while (count < 100) {
                synchronized (lock) {
                    if ((count & 1) == 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + count++);
                    }
                }
            }
        }, "偶数").start();

        new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    if ((count & 1) != 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + count++);
                    }
                }
            }
        }, "奇数").start();
    }


/*
    private volatile static int num = 0;

    public static void main(String[] args) {

        PrintNum printNum = new PrintNum();
        PrintEven printEven = new PrintEven(printNum, num);
        PrintOdd printOdd = new PrintOdd(printNum, num);

        new Thread(printEven).start();
        new Thread(printOdd).start();
    }
    */
}
/*

class PrintEven implements Runnable {

    private PrintNum printNum;

    private int num;

    public PrintEven(PrintNum printNum, int num) {
        this.printNum = printNum;
        this.num = num;
    }

    @Override
    public void run() {
        while (num < 100) {
            printNum.printEvenNum(num);
            num++;
        }
    }
}

class PrintOdd implements Runnable {

    private PrintNum printNum;

    private int num;

    public PrintOdd(PrintNum printNum, int num) {
        this.printNum = printNum;
        this.num = num;
    }

    @Override
    public void run() {
        while (num < 100) {
            printNum.printOddNum(num);
            num++;
        }
    }
}

class PrintNum {

    public synchronized void printEvenNum(int num) {
        if (num % 2 != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + ": " + num);
        notify();
    }

    public synchronized void printOddNum(int num) {
        if (num % 2 == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + ": " + num);
        notify();
    }
}
*/
