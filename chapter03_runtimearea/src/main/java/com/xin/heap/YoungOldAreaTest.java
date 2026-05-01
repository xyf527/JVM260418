package com.xin.heap;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-04-29 11:48
 * @github https://github.com/xyf527
 * @copyright
 */

/**
 * 内存分配策略(或对象提升(promotion)规则)
 * 如果对象在Eden出生并经过第一次MinorGC后仍然存活 并且能被Survivor容纳的话 将被移动到Survivor空间中
 * 并将对象年龄设为 对象在Survivor区中每熬过一次MinorGC年龄就增加1岁 当他的年龄增加到一定程度(默认15岁 其实每个
 * JVM、每个GC都有所不同 设置参数-XX:MaxTenuringThreshold=15)时 就会被晋升到老年代中
 * 大对象直接进入老年代
 * -Xms60m -Xmx60m -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * Heap
 *  PSYoungGen      total 19456K, used 2097K [0x00000007beb00000, 0x00000007c0000000, 0x00000007c0000000)
 *   eden space 17408K, 12% used [0x00000007beb00000,0x00000007bed0c780,0x00000007bfc00000)
 *   from space 2048K, 0% used [0x00000007bfe00000,0x00000007bfe00000,0x00000007c0000000)
 *   to   space 2048K, 0% used [0x00000007bfc00000,0x00000007bfc00000,0x00000007bfe00000)
 *  ParOldGen       total 44032K, used 20480K [0x00000007bc000000, 0x00000007beb00000, 0x00000007beb00000)
 *   object space 44032K, 46% used [0x00000007bc000000,0x00000007bd400010,0x00000007beb00000)
 *  Metaspace       used 3336K, capacity 4500K, committed 4864K, reserved 1056768K
 *   class space    used 357K, capacity 388K, committed 512K, reserved 1048576K
 */

/*
 空间分配担保
  在发生Minor GC之前 虚拟机会检查老年代最大的可用连续空间是否大于新生代所有对象的总空间
  如果大于则此次Minor GC是安全的 如果小于 则虚拟机会查看HandlePromotionFailure设置值是否允许担保失败
  如果HandlePromotionFailure=true 那么会继续检查老年代最大可用连续空间是否大于历次晋升到老年代的对象平均大小
  如果大于则尝试进行一次Minor GC 但这次Minor GC仍然是有风险的 如果小于或者HandlePromotionFailure=false 则改为进行一次Full GC
 */
public class YoungOldAreaTest {

    public static void main(String[] args) {
        // 20m
        byte[] buffer = new byte[1024 * 1024 * 20];
    }

}
