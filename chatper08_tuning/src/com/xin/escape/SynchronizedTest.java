package com.xin.escape;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-05-06 17:13
 * @github https://github.com/xyf527
 * @copyright
 */

public class SynchronizedTest {

    public void f() {

        /*
        代码中对hollis这个对象进行加锁 但是hollis对象的生命周期只在f()方法中
        并不会被其他线程锁访问到 所以在JIT编译阶段就会被优化掉
        问题: 字节码文件中会去掉hollis吗?
         */
        Object hollis = new Object();
        synchronized (hollis) {
            System.out.println(hollis);
        }

        /*
         优化后
         Object hollis = new Object();
         System.out.println(hollis);
         */

    }

}
