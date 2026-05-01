package com.xin.methodarea;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-05-01 10:23
 * @github https://github.com/xyf527
 * @copyright
 */

public class StringLocationTest {

    /*
     jdk6中:
      -XX:PermSize=6m -XX:MaxPermSize=6m -Xms6m -Xmx6m
     jdk8中:
      -XX:MetaspaceSize=6m -XX:MaxMetaspaceSize=6m -Xms6m -Xmx6m
      # 给元空间留活路，限制堆内存
      -XX:MetaspaceSize=128m -Xms20m -Xmx20m -XX:+PrintGCDetails
     */
    public static void main(String[] args) {
        // 使用Set保持着常量池引用 避免full gc回收常量池行为
        Set<String> set = new HashSet<String>();
        // 在short可以取值的范围内足以让6mb的PermSize或heap产生OOM了
        int i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }

}
