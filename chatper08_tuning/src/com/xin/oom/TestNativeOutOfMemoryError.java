package com.xin.oom;

import java.util.concurrent.CountDownLatch;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-05-06 11:25
 * @github https://github.com/xyf527
 * @copyright
 */

/*
 通过-Xss设置每个线程栈大小的容量
 JDK5.0以后每个线程堆栈大小为1M 以前每个线程堆栈大小为256k
 正常情况下 在相同物理内存下 减少这个值能生成更多的线程 但是操作系统对一个进程内的线程数还是有限制的
 不能无限生成 经验值在3000~5000左右
 能创建的线程数的具体计算公式如下:
 (MaxProcessMemory - JVMMemory - ReservedOsMemory) / (ThreadStackSize) = Number of threads
 MaxProcessMemory 指的是进程可寻址的最大空间
 JVMMemory JVM内存
 ReservedOsMemory 保留的操作系统
 ThreadStackSize 线程栈的大小
 由公式得出结论 你给JVM内存越多 那么你能创建的线程越少 越容易发生
 Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
 问题解决
 1.如果程序中有bug 导致创建大量不需要的线程或者线程没有及时回收 那么必须解决这个bug 修改参数是不能解决问题的
 2.如果程序确实需要大量的线程 现有的设置不能达到要求 那么可以通过修改MaxProcessMemory JVMMemory
 ThreadStackSize 这三个因素 来增加能创建的线程数
 3.MaxProcessMemory 使用64位操作系统
 4.JVMMemory 减少JVMMemory的分配
 5.ThreadStackSize 减少单个线程的栈大小
 经实测 在32位windows系统下较为严格 64位系统下只能保证正/负相关性 甚至说相关性也不能保证
 即: 在测试的过程中64位操作系统下调整Xss的大小并没有对产生线程的总数产生影响 程序执行到极限的时候 操作系统会死机 无法看出效果

 线程总数也收到系统空闲内存和操作系统的限制 检查是否该系统下有此限制:
 Linux Ubuntu24.04:
 cat /proc/sys/kernel/pid_max 系统最大pid值 在大型系统里可适当调大 4194304
 cat /proc/sys/kernel/threads-max 系统允许的最大线程数 252773
 ulimit -u 系统限制某用户下最多可以运行多少进程或者线程
 cat /proc/sys/vm/max_map_count 1048576
 max_map_count文件包含限制一个进程可以拥有的VMA(虚拟内存区域)的数量 虚拟内存区域是一个连续的虚拟地址空间区域
 在进程的生命周期中 没当成许昌市记载内存中映射文件 链接到共享内存段 或者分配堆内存的时候 这些区域将被创建
 调优这个值将限制进程可拥有VMA的数量 限制一个进程拥有VMA的总数可能导致应用程序出错 因为当进程打到了VMA上限
 但又只能释放少量的内存给其他的内核进程使用时 操作系统会抛出内存不足的错误 如果你的操作系统在NORMAL区域仅占用
 少量的内存 那么调低这个值可以帮助释放内存给内核用
 */
public class TestNativeOutOfMemoryError {

    public static void main(String[] args) {
        for (int i = 0; ; i++) {
            System.out.println("i = " + i);
            new Thread(new HoldThread()).start();
        }
    }

}

class HoldThread extends Thread {

    CountDownLatch cdl = new CountDownLatch(1);

    @Override
    public void run() {
        try {
            cdl.await();
        } catch (InterruptedException e) {

        }
    }
}