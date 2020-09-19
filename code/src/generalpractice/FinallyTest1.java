package generalpractice;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/5/26 19:14
 */
public class FinallyTest1 {
    public static void main(String[] args) {
        String s = "祝你考出好成绩！";
        System.out.println(s.length());
        System.out.println("return value of getValue(): " + getValue());
    }
    public static int getValue() {
        int i = 1;
        try {
            i = 4;
        } finally{

            return i++;
        }
    }
}
