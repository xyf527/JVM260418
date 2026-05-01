package com.xin.methodarea;

import com.sun.xml.internal.ws.org.objectweb.asm.ClassWriter;
import com.sun.xml.internal.ws.org.objectweb.asm.Opcodes;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-05-01 10:06
 * @github https://github.com/xyf527
 * @copyright
 */

/*

 Jdk6/7中
 -XX:PermSize=10m 设置永久初始分配空间 默认值是20.75M
  -XX:MaxPermSize=10m 设置永久代最大可分配空间 32位机器默认64M 64位机器默认82N
 Jdk8中:
 -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 关闭指针压缩：在启动参数中加入 -XX:-UseCompressedClassPointers。
 这样 JVM 就不再划分独立的 Compressed class space，所有的类元数据都会直接扔进 Metaspace。
 */
public class OOMTest extends ClassLoader{

    public static void main(String[] args) {
        int j = 0;
        try {
            OOMTest oomTest = new OOMTest();
            for (int i = 0; i < 10000; i++) {
                // 创建ClassWriter对象 用于生成类的二进制字节码
                ClassWriter classWriter = new ClassWriter(0);
                // 指明版本号 修饰符 类名 包名 父类 接口
                classWriter.visit(Opcodes.V1_6, Opcodes.ACC_PRIVATE, "Class" + i, null, "java/lang/Object", null);
                // 返回byte[]
                byte[] code = classWriter.toByteArray();
                // 类德甲在
                oomTest.defineClass("Class" + i, code , 0, code.length); // Class对象
                j++;
            }
        } finally {
            System.out.println(j);
        }
    }

}
