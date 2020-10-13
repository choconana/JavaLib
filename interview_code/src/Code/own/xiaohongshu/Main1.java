package Code.own.xiaohongshu;

import java.util.HashMap;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] res;
        StringBuffer sb = new StringBuffer();
        while (in.hasNextLine()){
            String line = in.nextLine() ;
            if (line.length()==0)break;
            sb.append(line +"\n");
        }
        helper(sb.toString());
    }

    private static void helper(String sb) {
        HashMap<Integer, String> message = new HashMap<>();
        StringBuffer sb1 = new StringBuffer();
        int count = 0;
        for (int i = 0; i < sb.length(); i++) {

            char c = sb.charAt(i);
            if (c != '\n') {
                sb1.append(c);
            } else {
                count++;
                message.put(sb1.length(), sb1.toString());
                sb1.setLength(0);
            }
        }

        String[] strings = new String[1024];
        int len = 0;
        for (int n : message.keySet()) {
            String s = message.get(n);
            int sLen = 0;
            int num = 0;
            int record1 = 0;
            int record2 = 0;
            if (s.length() > 1024) {
                while (num < s.length()) {
                    if (s.charAt(num) == '.' || s.charAt(num) == '!') {
                        record2 = record1;
                        record1 = num;
                    }
                    if (sLen > 1024) {
                        sLen = 0;
                        strings[len++] = s.substring(record2, record1 + 1);
                    }
                    num++;
                    sLen++;
                }

            }
            strings[len++] = s;
        }

        for (int i = 0; i < len; i++) {
            System.out.println("msg" + (i + 1) + ":" + strings[i]);
        }
    }


}
