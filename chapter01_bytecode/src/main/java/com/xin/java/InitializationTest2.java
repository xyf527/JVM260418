package com.xin.java;

import java.util.Random;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-04-23 09:21
 * @github https://github.com/xyf527
 * @copyright
 */

/*
    总结
    使用static + final修饰的成员变量 称为全局常量
    什么时候在链接阶段的准备环节 给此全局常量赋的值是字面量或常量 不涉及到方法或构造器的调用
    除此之外 都是在初始化环节复制的

    Java程序对类的使用分为两种 主动使用和被动使用
    主动使用的说明 Class只有在必须要首次使用的时候才会被装载 Java虚拟机不会无条件的装载Class类型 Java虚拟机规定
    一个类或接口在初次使用前 必须要进行初始化 这里指的使用是指主动使用
    主动使用只有下列几种情况(即 如果出现如下的情况 则会对类进行初始化操作 而初始化操作之前的加载、验证、准备已经完成)
    1.当创建一个类的实例时 比如使用new关键字 或者通过反射、克隆、反序列化
    2.当调用类的静态方法时 即当使用了字节码invokestatic指令
    3.当使用类、接口的静态字段时(final修饰特殊考虑) 比如使用getstatic或者putstatic指令
    4.当使用java.lang.reflect包中的方法反射类的方法时 比如Class.forName("com.xin.java.Test")
    5.当初始化子类时 如果发现其父类还没有进行过初始化 则需要先出发其父类的初始化
    6.如果一个接口定义了default方法 那么直接实现或者间接实现该接口的类的初始化 该接口要在其之前被初始化
    7.当虚拟机启东时 用户需要指定一个要执行的主类(包含main()方法的那个类) 虚拟机会先初始化这个主类
    8.当初次调用MethodHandle实例时 初始化该MethodHandle指向的方法所在的类(涉及解析REF_getstatic REF_putstatic REF_invokeStatic方法句柄对应的类)

    除了以上的情况属于主动使用 其他的情况均属于被动使用 被动使用不会引起类的初始化
    也就是说 并不是在代码中出现的类就一定会被加载或者初始化 如果不符合主动使用的条件 类就不会初始化
    1.当访问一个静态字段时 只有真正声明这个字段的类才会被初始化
     当通过子类引用父类的静态变量 不会导致子类初始化
    2.通过数组定义类引用 不会触发此类的初始化
    3.引用常量不会触发此类或接口的初始化 因为常量在链接阶段就已经被显式赋值了
    4.调用ClassLoader类的loadClass()方法加载一个类 并不是对类的主动使用 不会导致类的初始化
    被动的使用 意味着不需要执行初始化环节 意味着没有<clinit>()的调用
 */
public class InitializationTest2 {

    // 在初始化阶段赋值
    public static int a = 1;
    // 在链接阶段的准备环节赋值
    public static final int INT_CONSTANT = 10;

    // 在初始化阶段赋值
    public static Integer INTEGER_CONSTANT1 = Integer.valueOf(100);
    // 在初始化阶段赋值
    public static final Integer INTEGER_CONSTANT2 = Integer.valueOf(1000);

    // 在链接阶段的准备环节赋值
    public static final String s0 = "helloworld0";
    // 在初始化阶段赋值
    public static final String s1 = new String("helloworld1");

    //
    public static String s2 = "helloworld2";

    public static final int NUM1 = new Random().nextInt(10);
    static int a1 = 9;
    static final int b = a;


}
