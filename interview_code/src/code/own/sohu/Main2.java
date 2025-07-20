package code.own.sohu;

public class Main2 {
    public int getHouses (int t, int[] xa) {
        // write code here
        int len = xa.length;
        int n = len / 2;
        int[] center = new int[n];
        int[] width = new int[n];
        int[] start = new int[n];
        int[] end = new int[n];
        int j  = 0;
        int k = 0;
        int sum = 2;
        for (int i = 0; i < len; i += 2) {
            center[j++] = xa[i];
        }
        for (int i = 1; i < len; i += 2) {
            width[k++] = xa[i];
        }
        for (int i = 0; i < n; i++) {
            start[i] = center[i] - width[i] / 2;
            end[i] = center[i] + width[i] / 2;
        }
        for (int i = 0; i < n - 1; i++) {
            if (start[i + 1] - end[i] > t) {
                sum += 2;
            } else if(start[i + 1] - end[0] == t) {
                sum++;
            }
        }
        return sum;
    }
}
