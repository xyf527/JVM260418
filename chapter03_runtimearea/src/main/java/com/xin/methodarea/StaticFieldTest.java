package com.xin.methodarea;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-05-01 10:28
 * @github https://github.com/xyf527
 * @copyright
 */

/*

 结论 静态引用相应的对象实体始终都存在堆空间(jdk7及之后)

 JDK 7 参数：
  -Xms300m -Xmx300m -XX:PermSize=300m -XX:MaxPermSize=300m -XX:+PrintGCDetails
 JDK 8 参数：
  -Xms300m -Xmx300m -XX:MetaspaceSize=300m -XX:MaxMetaspaceSize=300m -XX:+PrintGCDetails
 */
public class StaticFieldTest {

    // 100MB -> 200+MB
    private static byte[] arr = new byte[1024 * 1024 * 100];

    public static void main(String[] args) {
        System.out.println(StaticFieldTest.arr);
    }

}
