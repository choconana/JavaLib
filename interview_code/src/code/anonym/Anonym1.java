package code.anonym;

import java.util.Scanner;

public class Anonym1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();
        System.out.println(minLength(str1, str2));
    }

    public static int minLength(String str1, String str2){
        if(str1 == null || str2 ==null || str1.length() < str2.length()){
            return 0;
        }
        char[] chas1 = str1.toCharArray();
        char[] chas2 = str2.toCharArray();
        int[] map = new int[256];
        for (int i=0; i< chas2.length; i++){
            map[chas2[i]]++;
        }
        int left= 0;
        int right = 0;
        int minLen =Integer.MAX_VALUE;
        int match =chas2.length;
        while (right != chas1.length){
            map[chas1[right]]--;
            if(map[chas1[right]] >= 0){
                match--;
            }
            if(match == 0){
                while (map[chas1[left]] < 0){
                    map[chas1[left++]]++;
                }
                minLen = Math.min(minLen, right - left + 1);
                match++;
                map[chas1[left++]]++;
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? 0 :minLen;
    }
}
