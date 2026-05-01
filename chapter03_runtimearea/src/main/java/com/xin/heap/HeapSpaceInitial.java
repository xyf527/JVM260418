package com.xin.heap;

import java.util.concurrent.TimeUnit;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-04-28 12:58
 * @github https://github.com/xyf527
 * @copyright
 */

/*
    -Xms6m = -XX:InitialHeapSize=6m 表示堆区的初始大小
    -Xmx80m = -XX:MaxHeapSize=1024m 表示堆区的最大内存
    通常会把这两个参数配置相同的值 其目的是为了能够在java垃圾回收机
    清理完堆区后不需要重新分隔计算堆区的大小 从而提高性能

    heap默认最大值计算方式 如果物理内存小于192M 那么heap最大值为物理内存的一半
    如果物理内存大于等于1G 那么heap的最大值为物理内存的1 / 4
    heap默认最小值计算方式 最少不得少于8M 如果物理内存大于等于1G 那么默认值为物理内存的1 / 64
    即1024 / 64 = 16M 最小堆内存在jvm启动的时候就会被初始化
 */

// 默认-XX:NewRatio=2 新生代何老年代内存比例
// 默认-XX:SurvivorRatio=8 Eden空间和另外两个Survivor空间缺省所占的比例是8:1:1
public class HeapSpaceInitial {

    public static void main(String[] args) {

        // 返回Java虚拟机中的堆内存总量
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        // 返回Java虚拟机试图使用的最大堆内存量
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;

        System.out.println("-Xms: " + initialMemory + "M");
        System.out.println("-Xmx: " + maxMemory + "M");

        try {
            TimeUnit.SECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
