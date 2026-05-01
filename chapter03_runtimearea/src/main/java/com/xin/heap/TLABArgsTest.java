package com.xin.heap;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-04-29 12:32
 * @github https://github.com/xyf527
 * @copyright
 */

/*
 尽管不是所有的对象实例都能够在TLAB中成功分配内存 但JVM确实是将TLAB作为内存分配的首选
 在程序中 开发人员可以通过选项-XX:+/-UseTLAB设置是否开启TLAB空间
 默认情况下 TLAB空间的内存非常小 仅占有整个Eden空间的1%
 当然我们可以通过选项-XX:TLABWasteTargetPercent设置TLAB空间所占用的Eden空间的百分比大小
 一旦对现在TLAB空间分配内存失败时 JVM就会尝试着通过使用加锁机制确保数据操作的原子性 从而直接在Eden空间中分配内存
 */
public class TLABArgsTest {

    public static void main(String[] args) {
        System.out.println("我只是个打酱油的。。");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
