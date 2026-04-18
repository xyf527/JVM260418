package com.xin.java;

import org.junit.Test;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-04-18 10:53
 * @github https://github.com/xyf527
 * @copyright
 */

public class ByteCodeInterview {

    //面试题： i++和++i有什么区别？
    @Test
    public void test1(){
        int i = 10;
        i++;
//        ++i;

        System.out.println(i);
    }

    @Test
    public void test2(){
        int i = 10;
        i = i++;
        System.out.println(i);
    }

    @Test
    public void test3(){
        int i = 2;
        // i = (i * i) ++
        i *= i++;
        System.out.println(i);
    }

    @Test
    public void test4(){
        int k = 10;
        k = k + (k++) + (++k);
        System.out.println(k);
    }

    // 包装类对象的缓存问题
    /*
    包装类        缓存对象
    Byte        -128~127
    Short       -128~127
    Integer     -128~127
    Long        -128~127
    Float       没有
    Double      没有
    Character   0-127
    Boolean     true和false
     */
    @Test
    public void test5(){
        Integer i1 = 10;
        Integer i2 = 10;
        System.out.println(i1 == i2);

        /*
         0 bipush 10
         2 invokestatic #19 <java/lang/Integer.valueOf : (I)Ljava/lang/Integer;>
         5 astore_1

         public static Integer valueOf(int i) {
            if (i >= IntegerCache.low && i <= IntegerCache.high)
                return IntegerCache.cache[i + (-IntegerCache.low)];
            return new Integer(i);
         }
         */
        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println(i3 == i4);

        Boolean b1 = true;
        Boolean b2 = true;
        System.out.println(b1 == b2);
    }

    // String生命的字面量数据都放在字符串常量池中
    // jdk6中字符串常量池存放在方法区中(即永久代中)
    // jdk7及以后字符串常量池存放在堆空间
    @Test
    public void test6() {
        String str = new String("hello") + new String("world");
        // str.intern(); 在str1声明之前
        String str1 = "helloworld";
        System.out.println(str == str1);
    }

}
