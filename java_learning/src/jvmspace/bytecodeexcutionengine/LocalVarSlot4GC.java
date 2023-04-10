package jvmspace.bytecodeexcutionengine;

/**
 * @author hezhidong
 * @date 2023/1/12 下午10:32
 * @description 局部变量槽复用对垃圾收集的影响。
 * vm options: -verbose:gc
 */
public class LocalVarSlot4GC {

    // 没有回收掉
    /*public static void main(String[] args) {
        byte[] placeholder = new byte[64 * 1024 * 1024];
        System.gc();
    }*/

    // 没有回收掉
    /*public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        System.gc();
    }*/

    // 有回收掉
    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
            placeholder = null;
        }
//        int a = 0;
        System.gc();
    }
}
