package com.xin.oom;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-05-06 11:13
 * @github https://github.com/xyf527
 * @copyright
 */

public class ArgsTest {

    public static void main(String[] args) {
        System.out.println("程序开始执行");
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("程序执行结束");
    }

}
