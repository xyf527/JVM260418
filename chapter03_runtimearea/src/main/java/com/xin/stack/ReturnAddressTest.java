package com.xin.stack;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-04-28 11:28
 * @github https://github.com/xyf527
 * @copyright
 */

/*
    栈总结的五个小问题
    问题一 栈溢出的情况？
    栈溢出 StackOverflowError
    在main方法中调用main方法 就会不断的压栈执行 直到栈溢出
    栈的大小可以是固定大小的 也可以是动态变化(动态扩展)的
    如果是固定的 可以通过-Xss设置栈的大小
    如果是动态变化的 当站大小到达了整个内存空间不足了就是抛出OutOfMemory异常(java.lang.OurOfMemoryError)

    问题二 调整栈大小 就能保证不出现溢出吗？
    不能 因为调整栈大小 只会减少出现溢出的可能 栈大小不是可以无限扩大的 所以不能保证不出现溢出

    问题三 分配的占内存越大越好吗？
    不是 因为增加栈大小 会造成每个线程的栈都变得很大 使得一定的栈空间下 能创建的线程数量会变少

    问题四 垃圾回收是否会涉及到虚拟机栈？
    不会 垃圾回收只会涉及到方法区和堆中 方法区和堆也会存在溢出的可能
    程序计数器 只记录下一行的地址 不存在溢出和垃圾回收
    虚拟机栈和本地方法栈 都是只涉及压栈和出栈 可能存在栈溢出 不存在垃圾回收

    问题五 方法中定义的局部变量是否线程安全
 */
public class ReturnAddressTest {

    public boolean methodBoolean() {
        return false;
    }

    public byte methodByte() {
        return 0;
    }

    public short methodShort() {
        return 0;
    }

    public char methodChar() {
        return 'a';
    }

    public int methodInt() {
        return 0;
    }

    // 0 lconst_0
    // 1 lreturn
    public long methodLong() {
        return 0L;
    }

    // 1 freturn
    public float methodFloat() {
        return 0.0f;
    }

    // 1 dreturn
    public double methodDouble() {
        return 0.0;
    }

    // 1 areturn
    public String methodString() {
        return null;
    }

    // 1 areturn
    public Date methodDate() {
        return null;
    }

    // 0 return
    public void methodVoid() {

    }

    static {
        int i = 10;
    }

    public void method1() throws IOException {
        FileReader fis = new FileReader("xyf");
        char[] cBuffer = new char[1024];
        int len;
        while ((len = fis.read(cBuffer)) != -1) {
            String str = new String(cBuffer, 0, len);
            System.out.println(str);
        }
        fis.close();
    }

    /*
        只要在本方法中的异常表中没有搜索到匹配的异常处理器 就会导致方法退出 简称异常完成出口

        方法执行过程中抛出异常时的异常处理 存储在一个异常处理表 方便在发生异常的时候找到处理异常的代码
        Exception table:
        from  to  target  type
        4     16  19      any
        19    21  19      any

        本质上 方法的退出就是当前栈帧出栈的过程 此时需要恢复上层方法的局部变量表、操作数栈、将返回值压入调用这栈帧的操作数栈、
        设置PC寄存器值等 让调用者方法继续执行下去

        针刺感喊我干成出口和异常完成出口的区别在于 通过异常完成出口退出的不会给他的上层调用这产生任何的返回值
     */
    public void method2() {
        methodVoid();

        try {
            method1();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
