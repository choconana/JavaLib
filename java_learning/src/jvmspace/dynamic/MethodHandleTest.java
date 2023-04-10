package jvmspace.dynamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * @author hezhidong
 * @date 2023/1/15 上午11:51
 * @description
 */
public class MethodHandleTest {
    static class ClassA {
        public void println(String s) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws Throwable {
        Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
        System.out.println(obj.getClass().getSimpleName());
        getPrintlnMH(obj).invokeExact("coco");
    }

    private static MethodHandle getPrintlnMH(Object receiver) throws NoSuchMethodException, IllegalAccessException {

        MethodType mt = MethodType.methodType(void.class, String.class);
        return lookup().findVirtual(receiver.getClass(), "println", mt).bindTo(receiver);
    }
}
