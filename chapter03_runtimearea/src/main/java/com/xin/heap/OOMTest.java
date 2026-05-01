package com.xin.heap;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-04-29 11:13
 * @github https://github.com/xyf527
 * @copyright
 */

/**
 * 针对幸存者s0 s1的总结 复制之后有交换 谁空谁是to
 * 关于垃圾回收
 *  频繁在新生区(伊甸区)收集
 *  很少在养老区(老年代)收集 养老区执行MajorGC后仍然无法保存对象就会java.lang.OutOfMemoryError: Java heap space
 *  几乎不在永久区/元空间收集
 * JVM在进行GC时 并非每次都对上面三个内存(新生代、老年代；方法区)区域一起回收的 大部分时候回收的都是指新生代
 * 针对HotSpot VM的视线 它里面的GC按照回收区域又分为两大种类型
 *  一种是部分收集(Partial GC)
 *  一种是整堆收集(Full GC)
 *  部分收集: 不是完整收集整个Java堆的垃圾收集 其中又分为
 *   新生代收集(Minor GC / Young GC): 只是新生代(Eden \ S0, S1)的垃圾收集
 *   老年代收集(Major GC / Old GC): 只是老年代的垃圾收集
 *    目前 只有CMS GC会有单独收集老年代的行为
 *    注意 很多时候Major GC会和Full GC混淆使用 需要具体分辨是老年代回收还是整堆回收
 *   混合收集(Mixed GC): 收集整个新生代以及部分老年代的垃圾收集
 *    目前 只有G1 GC会有这种行为
 *  整堆收集(Full GC): 收集整个Java堆和方法区的垃圾收集
 */

public class OOMTest {

    public static void main(String[] args) {
        ArrayList<Picture> list = new ArrayList<Picture>();
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list.add(new Picture(new Random().nextInt(1024 * 1024)));
        }
    }

}

class Picture {

    private byte[] pixels;

    public Picture(int len) {
        this.pixels = new byte[len];
    }

}
