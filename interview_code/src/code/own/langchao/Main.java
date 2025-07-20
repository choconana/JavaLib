package code.own.langchao;

import java.util.Scanner;

/**
 * 石头
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        int num=1;
        int max=1;
        while(scanner.hasNext()){
            int radius = scanner.nextInt();
            int[] stone1=new int[radius];
            int[] stone2=new int[radius];
            for(int i = 0;i < radius;i++){
                stone1[i] = scanner.nextInt();
            }
            for(int i = 0;i < radius; i++){
                stone2[i] = stone1[i];
                for(int j = i + 1;j < radius;j++){
                    stone2[j] = stone1[j];
                    if(stone2[i] + 1 == stone2[j]){
                        num += 1;
                        stone2[i] = stone2[j];
                    }
                }
                if(num > max){
                    max = num;
                }
                num = 1;
            }
            System.out.println(radius - max);
        }
    }
}
