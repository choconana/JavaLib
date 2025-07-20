package code.other.xingyebank;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split(",");
        int[] nums = new int[strings.length];
        int count = 0;
        while (count < strings.length) {
            String str = strings[count];
            if (isNumber(str)) {
                nums[count] = Integer.parseInt(strings[count]);
                count++;
            } else {
                System.out.println("ERROR");
                return;
            }
        }

        int a = nums[0] < nums[1] ? nums[0] : nums[1];
        int b = nums[0] < nums[1] ? nums[1] : nums[0];

        ArrayList<Integer> res = getPrime(a, b);
        int size = res.size();
        if (size == 0) {
            System.out.println("NO DATA");
        } else {
            for (int i = 0; i < size; i++) {
                System.out.print(res.get(i));
                if (i < size - 1) {
                    System.out.print(",");
                }
            }
        }
    }

    public static boolean isNumber(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            char c = chars[i];
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Integer> getPrime(int n, int m){
        ArrayList<Integer> prime = new ArrayList<>();

        for(;n<=m;n++){
            boolean isPrime = true;
            for(int j=2;j<=Math.sqrt(n);j++){
                int k  = n % j;
                if(n%j == 0){
                    isPrime = false;
                    break;
                }else
                    isPrime = true;
            }
            if(isPrime)
                prime.add(n);
        }

        return prime;
    }
}

