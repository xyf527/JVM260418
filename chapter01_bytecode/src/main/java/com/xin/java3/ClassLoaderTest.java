package com.xin.java3;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-04-23 08:37
 * @github https://github.com/xyf527
 * @copyright
 */

public class ClassLoaderTest {

    public static void main(String[] args) {

        // 获取系统该类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
        // 获取扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);
        // 试图获取引导类加载器 失败
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println(bootstrapClassLoader);

        try {
            ClassLoader classLoader = Class.forName("java.lang.String").getClassLoader();
            System.out.println(classLoader);

            // 自定义的类默认使用系统类加载器
            ClassLoader classLoader1 = Class.forName("com.xin.java3.ClassLoaderTest").getClassLoader();
            System.out.println(classLoader1);

            // 关于数组类型的加载 使用的类加载器与数组元素的类加载器相同
            String[] arrStr = new String[10];
            System.out.println(arrStr.getClass().getClassLoader());

            ClassLoaderTest[] arr1 = new ClassLoaderTest[10];
            System.out.println(arr1.getClass().getClassLoader());

            int[] arr2 = new int[10];
            System.out.println(arr2.getClass().getClassLoader());

            System.out.println(Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
