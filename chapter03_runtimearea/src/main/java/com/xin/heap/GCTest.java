package com.xin.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-04-29 12:26
 * @github https://github.com/xyf527
 * @copyright
 */

/*
 年轻代GC(Minor GC)触发机制:
  当年轻代空间不足时 就会触发Minor GC 这里的年轻代指的是Eden区满Survivor满不会引发GC(每次Minor GC会清理年轻代的内存)
  因为Java对象大多都具备朝生夕灭的特性 所有Minor GC非常频繁 一般回收速度也比较快 这一定义既清晰又易于理解
  Minor GC会引发STW(Stop The World) 暂停其他用户的线程 等垃圾回收结束 用户线程才恢复运行
 */

/*
 老年代GC(Major GC / Full GC)触发机制:
  指发生在老年代的GC 对象从老年代消失时 我们说"Major GC"或"Full GC"发生了
   出现了Major GC 经常会伴随至少一次的Minor GC(但非绝对的 在Parallel Scavenge收集器的手机策略里就有直接进行Major GC的策略选择过程)
   也就是在老年代空间不足时 会先触发Minor GC 如果之后空间还不足 则出发Major GC
 Major GC的速度一般会比Minor GC慢10倍以上 STW的时间更长
 如果Major GC之后 内存还不足 就报OOM了
 */

/*
 Full GC触发机制:
  触发Full GC执行的情况有以下五种:
  1.调用System.gc()时 系统建议Full GC 但是不必然执行
  2.老年代空间不足
  3.方法区空间不足
  4.通过Minor GC进入老年代的平均大小大于老年代的可用内存
  5.由Eden区、survivor space0(From Space)区向survivor space1(To Space)区复制时 对象大小大于To Space可用内存
  则把该对象转存到老年代 且老年代的可用内存小于该对象大小
  说明: Full GC是开发或调优中要尽量避免的 这样STW时间会短一些
 */

/*
 为什么需要把Java堆分代 不分代就不能正常工作了吗？
  其实不分代完全可以 分代的唯一理由就是优化GC性能 如果没有分代 那所有的对象都在一块 就如同把一个学校的人都关在一个教室
  GC的时候要找到哪些对象没用 这样就会对堆的所有区域进行扫描 而很多对象都是朝生夕死的 如果分代的话 把新创建的对象放到某一
  地方 当GC的时候先把这块存储"朝生夕死"对象的区域进行回收 这样就会腾出很大的空间出来
 */
public class GCTest {

    public static void main(String[] args) {
        int i = 0;
        try {
            List<String> list = new ArrayList<>();
            String a = "527626.xyz";
            while (true) {
                list.add(a);
                a = a + a;
                i++;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("遍历次数为: " + i);
        }
    }

}
