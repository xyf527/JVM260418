package com.xin.java;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-05-02 10:22
 * @github https://github.com/xyf527
 * @copyright
 */

/*
 测试解释器模式和JIT编译模式
  -Xint: 花费的时间为: 2479
  -Xcomp: 花费的时间为: 162
  -Xmixed: 花费的时间为: 155
 */

public class IntCompTest {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        testPrimeNumber(1000000);

        long end = System.currentTimeMillis();

        System.out.println("花费的时间为: " + (end - start));
    }

    public static void testPrimeNumber(int count) {
        for (int i = 0; i < count; i++) {
            // 计算100以内的质数
            label:
            for (int j = 2; j <= 100; j++) {
                for (int k = 2; k <= Math.sqrt(j); k++) {
                    if (j % k == 0) {
                        continue label;
                    }
                }
                // System.out.println(j);
            }
        }
    }

}
