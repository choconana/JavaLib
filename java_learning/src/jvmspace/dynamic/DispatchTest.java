package jvmspace.dynamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * @author hezhidong
 * @date 2023/1/15 下午4:38
 * @description
 */
public class DispatchTest {
    public class GrandFather {
        void thinking() {
            System.out.println("I am grandFather");
        }
    }

    class Father extends GrandFather {
        @Override
        void thinking() {
            System.out.println("I am father");
        }
    }

    class Son extends Father {
        @Override
        void thinking() {
            MethodType mt = MethodType.methodType(void.class);
            try {
//                Field lookupImpl = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
//                lookupImpl.setAccessible(true);
//                MethodHandle mh = ((MethodHandles.Lookup) lookupImpl.get(null)).findSpecial(GrandFather.class, "thinking", mt, GrandFather.class);
                MethodHandle mh = lookup().findSpecial(GrandFather.class, "thinking", mt, GrandFather.class);
                mh.invoke(this);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        (new DispatchTest().new Son()).thinking();
    }
}
