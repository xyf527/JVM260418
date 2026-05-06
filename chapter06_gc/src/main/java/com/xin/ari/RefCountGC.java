package main.java.com.xin.ari;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-05-02 14:44
 * @github https://github.com/xyf527
 * @copyright
 */

/*
 -XX:+PrintGCDetails
 证明: Java使用的不是引用计数算法
 */
public class RefCountGC {

    private byte[] bigSize = new byte[1024 * 1024 * 5];

    Object reference = null;

    public static void main(String[] args) {

        RefCountGC obj1 = new RefCountGC();
        RefCountGC obj2 = new RefCountGC();

        obj1.reference = obj2;
        obj2.reference = obj1;

        obj1 = null;
        obj2 = null;

        // 显式的执行垃圾回收算法
        // 这里发生GC obj1和obj2能否被回收
        System.gc();

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


}
