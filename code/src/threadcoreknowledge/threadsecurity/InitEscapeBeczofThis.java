package threadcoreknowledge.threadsecurity;

/**
 * @Description: 初始化未完毕，就this赋值
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/14 23:43
 */
public class InitEscapeBeczofThis {

    static Point point;

    public static void main(String[] args) throws InterruptedException {
        new PointMaker().start();
        Thread.sleep(101);
        if (point != null) {
            System.out.println(point);
        }
    }
}

class Point {
    private final int x, y;

    public Point(int x, int y) throws InterruptedException {
        this.x = x;
        InitEscapeBeczofThis.point = this;
        Thread.sleep(100);
        this.y = y;
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }
}

class PointMaker extends Thread {

    @Override
    public void run() {
        try {
            new Point(1, 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
