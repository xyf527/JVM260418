package com.xin.java;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-04-18 11:24
 * @github https://github.com/xyf527
 * @copyright
 */

class Father {

    int x = 10;
    public Father() {
        this.print();
        x = 20;
    }

    public void print() {
        System.out.println("Father.x = " + x);
    }

}

class Son extends Father {

    int x = 30;

    public Son() {
        this.print();
        x = 40;
    }

    public void print() {
        System.out.println("Son.x = " + x);
    }

}

public class ByteCodeInterview1 {

    public static void main(String[] args) {
        Father father = new Son();
        System.out.println(father.x);
    }

}
