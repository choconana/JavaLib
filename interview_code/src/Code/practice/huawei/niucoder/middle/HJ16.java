package Code.practice.huawei.niucoder.middle;

import java.util.*;

/**
 * @author hezhidong
 * @date 2023/3/1 下午5:40
 * @description 购物单
 */
public class HJ16 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();
        List<List<Goods>> goodsLists = new ArrayList<>(m);
        Map<Integer, Integer> codeMap = new HashMap<>(m);
        int count = 0;
        goodsLists.add(null);
        for (int i = 0; i < m; i++) {
            String[] strs = scanner.nextLine().split(" ");
            Goods goods = new Goods();
            goods.setPrice(Integer.parseInt(strs[0]));
            goods.setWeight(Integer.parseInt(strs[1]));
            int code = Integer.parseInt(strs[2]);
            if (0 == code) {
                List<Goods> goodsList = new ArrayList<>(m);
                goodsList.add(goods);
                goodsLists.add(goodsList);
                codeMap.put(i + 1,++count);
            } else {
                goodsLists.get(codeMap.get(code)).add(goods);
            }

        }

        int listSize = goodsLists.size();
        int[][] dp = new int[listSize][N + 1];
        for (int i = 1; i < listSize; i++) {
            List<Goods> goodsList = goodsLists.get(i);
            int size = goodsList.size();
            for (int j = N; j >= 1; j--) {
                /*Goods majorGoods = goodsList.get(0);
                int satisfiction = majorGoods.getPrice() * majorGoods.getWeight();
                if (j >= majorGoods.getPrice()) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - majorGoods.getPrice()] + satisfiction);
                    if (2 == size) {
                        Goods goods = goodsList.get(1);
                        satisfiction += goods.getPrice() * goods.getWeight();
                        if (j >= goods.getPrice()) {
                            dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - goods.getPrice()] + satisfiction);
                        }
                    } else if (3 == size) {
                        Goods goods1 = goodsList.get(1);
                        Goods goods2 = goodsList.get(2);
                        int satisfiction1 = goods1.getPrice() * goods1.getWeight();
                        int satisfiction2 = goods2.getPrice() * goods2.getWeight();
                        int satisfictionAll = satisfiction1 + satisfiction2;
                        if (j >= majorGoods.getPrice() + goods1.getPrice()) {
                            dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - majorGoods.getPrice() - goods1.getPrice()] + satisfiction + satisfiction1);
                        }
                        if (j >= majorGoods.getPrice() + goods2.getPrice()) {
                            dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - majorGoods.getPrice() - goods2.getPrice()] + satisfiction + satisfiction2);
                        }
                        if (j >= majorGoods.getPrice() + goods1.getPrice() + goods2.getPrice()) {
                            dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - majorGoods.getPrice() - goods1.getPrice() - goods2.getPrice()] + satisfiction + satisfictionAll);
                        }
                    }
                } else {
                    dp[i][j] = dp[i - 1][j];
                }*/

                for (int k = 0; k < size; k++) {
                    int satisfaction = goodsList.get(k).getPrice() * goodsList.get(k).getWeight();
                    if (j >= goodsList.get(k).getPrice()) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - goodsList.get(k).getPrice()] + satisfaction);
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }

        System.out.println(dp[listSize - 1][N]);

        for (int i = 0; i < listSize; i++) {
            for (int j = 0; j <= N; j++) {
                System.out.printf("[" + dp[i][j] + "],  ");
            }
            System.out.println();
        }

    }

    static class Goods {
        private int price;
        private int weight;

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
}
