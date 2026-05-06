package main.java.com.xin.other;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-05-03 09:56
 * @github https://github.com/xyf527
 * @copyright
 */

public class SystemGCTest {

    public static void main(String[] args) {

        new SystemGCTest();

        // 提醒jvm垃圾回收期执行gc 但是不确定是否马上执行gc
        System.gc();
        // Runtime.getRuntime().gc();

        // 强制调用使用引用的对象的finalize()方法
        System.runFinalization();
    }

    // 此方法什么时候会被执行？当一个对象首次考虑要被回收时 会调用其finalize()
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("SystemGCTest 重写了finalize()");
    }
}
