package Code.practice.huawei.niucoder.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author hezhidong
 * @date 2023/2/27 下午11:24
 * @description 汽水瓶
 */
public class HJ22 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> res = new ArrayList<>();
        while (scanner.hasNext()) {
            int emptyNum = scanner.nextInt();
            int drinkableNum = 0;
            int borrowNum = 0;
            int exchangeRatio = 3;
            if (0 == emptyNum) {
                break;
            }
            while (emptyNum >= exchangeRatio) {
                int exchangeNum = emptyNum / exchangeRatio;
                drinkableNum += exchangeNum;
                emptyNum = emptyNum % exchangeRatio + exchangeNum;
                // 还上借的瓶子
                if (borrowNum > 0) {
                    emptyNum -= borrowNum;
                    borrowNum = 0;
                }
                // 可以借的情况：2借1，4借2，...，依此类推，剩余2n个空瓶可以借n个空瓶
                if (emptyNum % 2 == 0 && 0 != exchangeNum) {
                    borrowNum = (int) (emptyNum * 0.5);
                    emptyNum += borrowNum;
                }
            }
            res.add(drinkableNum);
        }
        res.forEach(n -> System.out.println(n));
    }
}
