package com.xin.stack;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-04-28 12:05
 * @github https://github.com/xyf527
 * @copyright
 */

/*
   问题五 方法中定义的局部变量是否线程安全
   具体问题具体分析
 */
public class LocalVariableThreadSafe {

    public static void method1() {
        // StringBuilder线程不安全
        // s1的声明方式是线程安全的 因为线程私有 在线程内创建的s1 不会被其他线程调用
        StringBuilder s1 = new StringBuilder();
        s1.append("a");
        s1.append("b");
    }

    public static void method2(StringBuilder stringBuilder) {
        // stringBuilder的操作过程是线程不安全的
        // 因为stringBuilder是外面传进来的 可能被多个线程调用
        stringBuilder.append("a");
        stringBuilder.append("b");
    }

    public static StringBuilder method3() {
        // stringBuilder的操作过程是线程不安全的 因为返回了一个stringBuilder 可能被其他线程共享
        StringBuilder s1 = new StringBuilder();
        s1.append("a");
        s1.append("b");
        return s1;
    }

    public static String method4() {
        // s1的操作过程是线程安全的 s1.toString() 相当于new了一个String 所以s1没有被其他线程共享的可能
        StringBuilder s1 = new StringBuilder();
        s1.append("a");
        s1.append("b");
        return s1.toString();
    }

    // 结论 如果局部变量在内部产生并且在内部销毁 那就是线程安全的
}
