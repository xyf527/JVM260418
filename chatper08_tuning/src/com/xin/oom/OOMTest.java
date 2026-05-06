package com.xin.oom;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-05-06 10:44
 * @github https://github.com/xyf527
 * @copyright
 */

public class OOMTest {

    public static void main(String[] args) {
        // test1();
        test2();
    }

    /*
    GC overhead limit exceeded 这个是JDK6新加的错误类型 一般都是堆太小导致的 Sun官方对此的定义 超过98%
    的时间用来做GC并且回收了不到2%的内存时会抛出此异常 本质是一个预判性的异常 抛出该异常时系统没有真正的内存溢出
    解决方法
    1.检查项目中是否有大量的死循环或者有使用大内存的代码 优化代码
    2.添加参数 -XX:-UseGCOverHeadLimit 禁用这个检查 其实这个参数解决不了内存问题 只是把错误信息延后
    最终还是会出现java.lang.OutOfMemoryError: Java heap space
    3.dump内存 检查是否存在内存泄漏 如果没有 加大内存
     -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=heap/dumpExceeded.hprof
     -XX:+PrintGCDateStamps -Xms8M -Xmx8M -Xloggc:log/gc-oomExceeded.log
     */
    public static void test1() {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while (true) {
                list.add(UUID.randomUUID().toString().intern());
                i++;
            }
        } catch (Throwable e) {
            System.out.println("*******i: " + i);
            e.printStackTrace();
            throw e;
        }
    }
    /*
     -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=heap/dumpHeap1.hprof
     -XX:+PrintGCDateStamps -Xms8M -Xmx8M -Xloggc:log/gc-oomHeap1.log
     */
    public static void test2() {
        String str = "";
        Integer i = 1;
        try {
            while (true) {
                i++;
                str += UUID.randomUUID();
            }
        } catch (Throwable e) {
            System.out.println("*******i: " + i);
            e.printStackTrace();
            throw e;
        }
    }

}
