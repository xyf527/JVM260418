package main.java.com.xin.gclog;

import java.util.ArrayList;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-05-04 17:13
 * @github https://github.com/xyf527
 * @copyright
 */

/*
 -Xms60m -Xmx60m -XX:SurvivorRatio=8 -XX:+PrintGC(Details)/-verbose:gc
 -Xloggc:./logs/gc.log 把GC日志写入到一个文件中去 而不是打印到标准输出中
 -XX:+PrintGCTimeStamps 输出GC发生时的时间戳
 -XX:+PrintGCDateStamps 输出GC发生时的时间戳 以日期的形式例如2026-05-04T17:29:27.732
 -XX:+PrintHeapAtGC 每一次GC前和GC后 都打印堆信息
 */
public class GCLogTest {

    public static void main(String[] args) {
        ArrayList<byte[]> list = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            // 100kb
            byte[] arr = new byte[1024 * 100];
            list.add(arr);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
