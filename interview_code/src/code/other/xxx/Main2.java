package code.other.xxx;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/2 10:19
 */
public class Main2 {
    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 3));
    }

    public static int uniquePaths(int m, int n) {
        int N = n + m - 2;
        int K = n - 1;
        double res = 1.0;
        for (int i = 1; i <= n - 1; ++i) {
            res = res * (N - K + i) / i;
        }
        return (int) res;
    }
}