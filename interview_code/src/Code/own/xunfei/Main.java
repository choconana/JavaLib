package Code.own.xunfei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        test(n, list);
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                System.out.print(list.get(i));
            } else {
                System.out.printf("*" + list.get(i));
            }
        }
    }

    public static int test(int n, List<Integer> primes) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                primes.add(i);
                n /= i;
                return test(n, primes);
            }
        }
        primes.add(n);
        return n;
    }
}
