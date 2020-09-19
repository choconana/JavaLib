package generalpractice;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/19 19:26
 */
public class guanglianda8 extends Thread {
    private int x = 2;

    public static void main(String[] args) throws InterruptedException {
        new guanglianda8().makeItSo();
    }

    public guanglianda8() {
        x = 5;
        start();
    }

    public void makeItSo() throws InterruptedException {
        this.join();
        x = x - 1;
        System.out.println(x);
    }

    @Override
    public void run() {
        x *= 2;
    }
}
