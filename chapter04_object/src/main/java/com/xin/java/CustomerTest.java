package com.xin.java;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-05-01 11:04
 * @github https://github.com/xyf527
 * @copyright
 */

/*
 创建对象的步骤
  1.判断对象对应的类是否加载、链接、初始化 对应new #2 <com/xin/java/Customer>
  2.为对象分配内存
  3.处理并发安全问题
  4.初始化分配到的空间
  5.设置对象的对象头
  6.执行init方法进行初始化
 */
public class CustomerTest {

    public static void main(String[] args) {
        /*
         0 new #2 <com/xin/java/Customer>
         3 dup
         4 invokespecial #3 <com/xin/java/Customer.<init> : ()V>
         7 astore_1
         8 return
         */
        Customer customer1 = new Customer();

        /*
          0 new #2 <com/xin/java/Customer>
          3 dup
          4 ldc #3 <Tom>
          6 invokespecial #4 <com/xin/java/Customer.<init> : (Ljava/lang/String;)V>
          9 astore_1
         10 return
         */
        Customer customer2 = new Customer("Tom");
        System.out.println(customer2.name);

        System.out.println(customer2.getClass());

        System.out.println(customer2);

    }

}
