package com.xin.java2;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-04-23 08:13
 * @github https://github.com/xyf527
 * @copyright
 */

public class T {

    public static int k = 0;
    public static T t1 = new T("t1");
    public static T t2 = new T("t2");
    public static int i = print("i");
    public static int n = 99;

    static {
        print("静态块");
    }

    public int j = print("j");

    {
        print("构造块");
    }

    public T(String str) {
        System.out.println((++k) + ":" + str + " i=" + i + " n=" + n);
        ++n;
        ++i;
    }

    public static int print(String str) {
        System.out.println((++k) + ":" + str + " i=" + i + " n=" + n);
        ++n;
        return ++i;
    }

    public static void main(String[] args) {

    }

}
