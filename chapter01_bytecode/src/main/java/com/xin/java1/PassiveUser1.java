package com.xin.java1;

import org.junit.Test;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-04-23 13:57
 * @github https://github.com/xyf527
 * @copyright
 */

public class PassiveUser1 {

    @Test
    public void test1() {
        // 当通过子类引用父类静态变量时 不会导致子类初始化
        System.out.println(Child.num);
    }

    @Test
    public void test2() {
        // 通过数组定义类引用 不会触发此类的初始化
        Parent[] parents = new Parent[10];

        System.out.println(parents.getClass());
        System.out.println(parents.getClass().getSuperclass());

        // parents[0] = new Parent();
        // parents[1] = new Parent();
    }

}

class Parent {
    static {
        System.out.println("Parent initialization...");
    }

    public static int num = 1;
}

class Child extends Parent {
    static {
        System.out.println("Child initialization...");
    }
}