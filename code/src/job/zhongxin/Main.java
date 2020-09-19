package job.zhongxin;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/24 10:58
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(helper("4B", 12, 13));
    }

    public static int helper(String str, int l, int r) {
        int n = 0;
        int sum = 0;
        int[] num = new int[r - l + 1];
        for (int i = l; i <= r; i++) {
            num[n++] = Integer.valueOf(str, i);
            System.out.println(Integer.valueOf(str, i));
        }
        for (int i = 0; i < n; i++) {
            sum += num[i];
        }
        if (sum % 2 == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
