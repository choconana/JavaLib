package Code.own.xunfei;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int n = Integer.parseInt(s);
        s = scanner.nextLine();
        String[] strings = s.split(",");
        int[] nums = new int[n];
        int count = 0;
        for (String str : strings) {
            nums[count++] = Integer.parseInt(str);
        }
        quickSort(nums, 0, nums.length - 1);
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                System.out.print(nums[i]);
            } else {
                System.out.printf("," + nums[i]);
            }
        }
    }

    public static void quickSort(int[] numbers, int left, int right) {
        if (left >= right) return;
        int pos = partition(numbers, left, right);
        quickSort(numbers, left, pos - 1);
        quickSort(numbers, pos + 1, right);
    }

    public static int partition(int[] numbers, int i, int j) {
        int mid = (j + i) / 2;
        int base = numbers[mid];
        int n = numbers[i];
        numbers[i] = numbers[mid];
        numbers[mid] = n;
        while (i < j) {
            while (i < j && numbers[j] >= base) {
                j--;
            }
            numbers[i] = numbers[j];
            while (i < j && numbers[i] <= base) {
                i++;
            }
            numbers[j] = numbers[i];
        }
        numbers[i] = base;
        return i;
    }
}
