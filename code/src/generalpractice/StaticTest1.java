package generalpractice;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/5/27 15:20
 */
public class StaticTest1 {
    static int cnt = 100;
    static{  cnt = 6;
    }
    public static void main(String[] args){
        System.out.println("cnt = " + cnt);
        //最后输出是50，如果按照错误说法就应该是3
        //按顺序执行就是cnt=6--->cnt=100---->cnt = 100/2 = 50.
    }
    static{
        cnt /= 2;
    }
}
