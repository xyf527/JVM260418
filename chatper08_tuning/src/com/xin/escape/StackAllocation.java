package com.xin.escape;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-05-06 16:49
 * @github https://github.com/xyf527
 * @copyright
 */
// 栈上分配测试 -Xmx1G -Xms1G -XX:-DoEscapeAnalysis(关闭逃逸分析 默认开启) -XX:+PrintGCDetails
// 通常会将-Xmx和-Xms两个参数配置相同的值 其目的是为了能够在java垃圾回收机制清理完堆区后不需要重新分隔计算堆区的大小 从而提高性能
// 只要开启了逃逸分析 就会判断方法中的变量是否发生了逃逸 如果没有发生逃逸 则会使用栈上分配
public class StackAllocation {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000000; i++) {
            alloc();
        }

        // 查看执行时间
        long end = System.currentTimeMillis();
        System.out.println("花费时间: " + (end - start) + "ms");

        // 为了调试
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void alloc() {
        // 没有发生逃逸
        User user = new User();
    }

    static class User {

    }

}
