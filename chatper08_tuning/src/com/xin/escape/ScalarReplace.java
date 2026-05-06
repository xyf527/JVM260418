package com.xin.escape;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-05-06 17:18
 * @github https://github.com/xyf527
 * @copyright
 */

// 标量替换测试 -Xmx100m -Xms100m -XX:+DoEscapeAnalysis -XX:+PrintGCDetails -XX:+EliminateAllocations
// 结论 Java中的逃逸分析 其实优化的点就在于对栈上分配的对象进行标量替换

/*
 逃逸分析小节 逃逸分析并不成熟
 关于逃逸分析的论文在1999年就发表了 但直到JDK1.6才实现 而且这项技术 到如今也并不是十分成熟的
 其根本原因就是无法保证非逃逸分析的性能消耗一定能高于他的消耗 虽然经过逃逸分析可以做标量替换、栈上分配和锁消除 但是
 逃逸分析自身也是需要进行一系列复杂的分析的 这其实也是一个相对耗时的过程
 一个极端的例子 就是经过逃逸分析之后发现没有一个对象是不逃逸的 那这个逃逸分析的过程就白白浪费掉了
 */
public class ScalarReplace {

    public static class User {

        public int id;
        public String name;

    }

    public static void alloc() {

        User u = new User();
        u.id = 5;
        u.name = "xx";

    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("花费时间: " +  (end - start) + "ms");

    }

}
