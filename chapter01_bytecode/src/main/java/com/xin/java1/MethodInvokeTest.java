package com.xin.java1;

import java.util.Comparator;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-04-20 08:46
 * @github https://github.com/xyf527
 * @copyright
 */

public class MethodInvokeTest {

    public static void main(String[] args) {

        Father f = new Father();
        Son s = new Son();

        System.out.println(f.getInfo());
        System.out.println(s.getInfo());

        Son.show();;

        Comparator<Integer> comparator = Integer::compare;

        comparator.compare(12, 32);
    }

}

class Father {

    private String info = "xyf";

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}

class Son extends Father {

    private String info = "xin";

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public static void show() {
        System.out.println("hello");
    }

}