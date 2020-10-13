package Code.own.langchao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * @Description: 砍掉的树
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/12 22:20
 */
public class Main1 {
    public static void main(String[] ars){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        List<Integer> odd=new ArrayList<>();
        List<Integer> even=new ArrayList<>();
        int maxValue=0;
        int maxTmp=0;
        int result=1;
        for(int i=0;i<50;i++){
            odd.add(2*i+1);
            even.add(2*i+2);
        }
        for(int i=0;i<n;i++){
            int temp=scanner.nextInt();
            if(temp%2==1){
                for(int j=0;j<odd.size();j++){
                    if(odd.get(j)==temp){
                        odd.remove(j);
                        break;
                    }
                }
            } else{
                for(int j=0;j<even.size();j++){
                    if(even.get(j)==temp){
                        even.remove(j);
                        break;
                    }
                }
            }
        }
        int start = 0;
        if (odd.size() == 0 && even.size() == 0) {
            maxTmp = 0;
            maxValue = 0;
        } else if (odd.size() == 0) {
            start=even.get(0);
            for(int i=0;i<even.size()-1;i++){
                if(even.get(i+1)-even.get(i)==2){
                    result++;
                } else{
                    if(result>maxValue){
                        maxValue=result;
                        maxTmp=start;
                    }
                    result=1;
                    start=even.get(i+1);
                }
            }
            if(result>maxValue){
                maxValue=result;
                maxTmp=start;
            }
        } else if (even.size() == 0) {
            start=odd.get(0);
            for(int i=0;i<odd.size()-1;i++){
                if(odd.get(i+1)-odd.get(i)==2){
                    result++;
                } else{
                    if(result>maxValue){
                        maxValue=result;
                        maxTmp=start;
                    }
                    result=1;
                    start=odd.get(i+1);
                }
            }
            if(result>maxValue){
                maxValue=result;
                maxTmp=start;
            }
        } else {
            start=odd.get(0);
            for(int i=0;i<odd.size()-1;i++){
                if(odd.get(i+1)-odd.get(i)==2){
                    result++;
                } else{
                    if(result>maxValue){
                        maxValue=result;
                        maxTmp=start;
                    }
                    result=1;
                    start=odd.get(i+1);
                }
            }
            if(result>maxValue){
                maxValue=result;
                maxTmp=start;
            }
            result=1;
            start=even.get(0);
            for(int i=0;i<even.size()-1;i++){
                if(even.get(i+1)-even.get(i)==2){
                    result++;
                } else{
                    if(result>maxValue){
                        maxValue=result;
                        maxTmp=start;
                    }
                    result=1;
                    start=even.get(i+1);
                }
            }
            if(result>maxValue){
                maxValue=result;
                maxTmp=start;
            }
        }

        System.out.println(maxTmp+" "+maxValue);
    }
}
/*
1
1
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90 91 92 93 94 95 96 97 98 99 100
*/