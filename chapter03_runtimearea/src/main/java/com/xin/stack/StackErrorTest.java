package com.xin.stack;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-04-25 13:50
 * @github https://github.com/xyf527
 * @copyright
 */

/*
    jps
    95816 StackErrorTest

    jinfo -flag ThreadStackSize 95816
    -XX:ThreadStackSize=2048

    -XX:ThreadStackSize=256k
    -Xss640k

    堆和栈的区别 谁的性能更高？
    角度一 堆会被GC栈不会 堆是OutOfMemory 栈是StackOverFlow
    角度二 栈的执行效率比堆更高 比栈执行效率更高的可能就只有程序计数器了
    角度三 堆内存更大 栈内存较小数据结构 栈先进后出 FILO(First In Last Out) 类似弹夹 堆的数据结构更加复杂
    角度四 栈管运行 堆管存储 每个线程都有自己的栈 栈中的数据都是以栈帧(Stack Frame )的格式存在

    方法和栈帧之间存在怎样的关系？
    在这个线程上正在执行的每个方法都各自对应一个栈帧
    栈就是一个内存区块 是一个数据集 维系着方法执行过程中的各种数据信息

    在一条活动线程中 一个时间点上 只会有一个活动的栈帧 即只有当前正在执行的方法的栈帧(栈顶栈帧)是有效的 这个栈帧
    被称为当前栈帧(Current Frame) 与当前栈帧相对应的方法就是当前方法(Current Method) 定义这个方法的类就是当前类(Current Class)
    如果在该方法中调用了其他方法 对应的新的栈帧会被创建出来放在栈的顶端 成为新的当前栈
    执行引擎运行的所有字节码指令只针对当前栈帧进行操作
 */
public class StackErrorTest {

    private static int count = 1;

    public static void main(String[] args) {
       /* try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        try {
            count++;
            main(args);
        } catch (Throwable throwable) {
            System.out.println("递归的次数为: " + count);
        }
    }

}
