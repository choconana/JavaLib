package Code.practice.huawei;

import java.util.*;

/**
 * @author hezhidong
 * @date 2023/3/7 下午4:24
 * @description 用户调度问题
 */
public class _8_UserDispatch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] arr = new int[3][n];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        List<Integer> res = new ArrayList<>(n * n);

        Stack<Integer> oparetionStack = new Stack<>();
        oparetionStack.push(0);

        dfs(arr, 0, -1, oparetionStack, res);

        res.sort(Comparator.comparingInt(i -> i));
        System.out.println(res.get(0));
    }

    public static void dfs(int[][] arr, int floorIndex, int lastIndex, Stack<Integer> oparetionStack, List<Integer> res) {
        if (arr[0].length == floorIndex) {
            res.add(oparetionStack.peek());
            return;
        }

        int len = arr[floorIndex].length; // 3
        for (int i = 0; i < len; i++) {
            if (i == lastIndex) {
                continue;
            }
            oparetionStack.push(oparetionStack.peek() + arr[floorIndex][i]);
            dfs(arr, floorIndex + 1, i, oparetionStack, res);
            oparetionStack.pop();
        }
    }
}
