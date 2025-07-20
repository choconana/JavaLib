package code.own.youzan;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/10/11 19:26
 */
public class Main {
    public String largestMultipleOfThree (ArrayList<Integer> digits) {
        // write code here
        ArrayList<Integer>[] ans = new ArrayList[3];
        for (int i = 0; i < 3; i++) {
            ans[i] = new ArrayList<>();
        }
        int sum = 0;
        for (int n : digits) {
            ans[n % 3].add(n);
            sum += n;
        }
        for (int i = 0; i < 3; i++) {
            Collections.sort(ans[i], Collections.reverseOrder());
        }
        int sol = sum % 3;
        if (sol != 0) {
            if (ans[sol].size() > 0) {
                ans[sol].remove(ans[sol].size() - 1);
            } else if (ans[3 - sol].size() > 1) {
                ans[3 - sol].remove(ans[3 - sol].size() - 1);
                ans[3 - sol].remove(ans[3 - sol].size() - 1);
            } else {
                ans[1].clear();
                ans[2].clear();
            }
        }
        ans[0].addAll(ans[1]);
        ans[0].addAll(ans[2]);
        Collections.sort(ans[0], Collections.reverseOrder());
        StringBuilder stringBuilder = new StringBuilder();
        for (int n : ans[0]) {
            stringBuilder.append(n);
        }
        if (stringBuilder.length() != 0 && stringBuilder.charAt(0) == '0') return "0";
        return stringBuilder.toString();
    }
}
