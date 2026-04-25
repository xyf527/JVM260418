package com.xin.java1;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.junit.Test;

import java.io.Serializable;
import java.util.Random;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-04-23 09:58
 * @github https://github.com/xyf527
 * @copyright
 */

public class ActiveUse1 {

    public static void main(String[] args) {
        Order order = new Order();
    }


    @Test
    public void test3(){
        Order.method();
    }

    @Test
    public void test4() {
        System.out.println(Order.I);
    }

}

class Order implements Serializable {
    // 在链接环节的准备阶段赋值
    static final int I = 10;
    // static final int J = new Random().nextInt(10);
    static {
        System.out.println("Order类的初始化过程");
    }

    public static void method() {
        System.out.println("Order method...");
    }
}

interface MyInterface {
    public static final Thread t = new Thread(){
        {
            System.out.println("MyInterface的初始化");
        }
    };

    public default void method1() {
        System.out.println("hello");
    }
}
