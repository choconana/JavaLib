package code.other.tongcheng58;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split(",");
        int[] nums = new int[2];
        nums[0] = Integer.parseInt(strings[0]);
        nums[1] = Integer.parseInt(strings[1]);
        compSqrt(nums[0], nums[1]);
    }

    public static int compSqrt(int a, int b) {
        boolean flag1 = false, flag2 = false;
        for (int i = 0; i <= 500; i++) {
            for(int x=1;x<=(i + a)/2;x++) {
                if((x*x==(i + a))) {
                    for(int y=1;y<=(i + b)/2;y++) {
                        if((y*y==(i + b))) {
                            flag2 = true;
                        }
                    }
                }
            }

            if (flag2) {
                flag2 = false;
                System.out.println(i);
            }
        }
        return 0;
    }

    private static boolean isCompSqrt(int num) {

        for(int x=1;x<=num/2;x++) {
            if(x*x==num) {
                return true;
            }
        }
        return false;

    }

}
