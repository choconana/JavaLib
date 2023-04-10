package cn.gaoh.jol;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: gaoh
 * @Date: 2021/3/7 22:06
 * @Version: 1.0
 */
@Slf4j
public class JolTest {
    public static void main(String[] args) {
        Object o = new Object();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
