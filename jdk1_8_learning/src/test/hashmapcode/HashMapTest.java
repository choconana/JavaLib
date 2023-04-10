package test.hashmapcode;

import java.util.HashMap;

/**
 * @author hezhidong
 * @date 2021/7/2 下午4:27
 * @description
 */
public class HashMapTest {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < 1 << 10 - 1; i++) {
            hashMap.put(i, i);
        }
        hashMap.put(-2, -2);
        System.out.println("运行成功");
    }
}
