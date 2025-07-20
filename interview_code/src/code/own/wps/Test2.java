package code.own.wps;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/9/29 19:43
 */
public class Test2 {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[0-9a-z]{2}(?=ab)");
        Matcher m = pattern.matcher("23443ab549abe");
        StringBuilder sb = new StringBuilder();
        while (m.find()) {
            sb.append(m.group());
        }
        System.out.println(sb.toString());
    }
}
