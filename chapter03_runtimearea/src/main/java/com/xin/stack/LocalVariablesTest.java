package com.xin.stack;

import java.util.Date;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-04-25 14:26
 * @github https://github.com/xyf527
 * @copyright
 */

/*
    局部变量表(Local Variables)
    1.局部变量表也被称之为局部变量数组或本地变量表
    2.定义为一个数字数组 主要用于存储方法参数和定义在方法体内的局部变量 这些数据类型包括各类基本数据类型(8种) 对象引用(reference)
    以及returnAddress类型
    3.局部变量表所需的容量大小是在编译期确定下来的 并保存在方法Code属性的maximum local variable数据项中 在方法运行期间
    是不会改变局部变量表的大小的
    方法嵌套调用的次数由栈的大小决定 一般来说 栈越大 方法嵌套调用的次数越多 对一个函数而言 它的参数和局部变量越多
    使得局部变量表膨胀 它的栈帧就越大 以满足方法调用所传递的信息增大的需求 进而函数调用就会占用更多的栈空间 导致其嵌套调用次数就会减少
    4.局部变量表中的变量只在当前方法调用中有效 在方法执行时 虚拟机通过使用局部变量表完成参数值到参数变量列表的传递过程
    当方法调用结束后 随着方法栈帧的销毁 局部变量表也会随之销毁

    局部变量表中的变量也是重要的垃圾回收根节点 只要被局部变量表中直接或间接引用的对象都不会被回收
 */

public class LocalVariablesTest {

    private int count = 0;

    public static void main(String[] args) {
        LocalVariablesTest test = new LocalVariablesTest();
        int num = 10;
        test.test1();
    }

    // 非静态方法 局部变量表中会多出一个this0	0	43	0	cp_info #36	cp_info #32
    private void test1() {
        Date date = new Date();
        String name1 = "527626.xyz";
        test2(date, name1);
        System.out.println(date + name1);
    }

    public static void testStatic() {
        LocalVariablesTest test = new LocalVariablesTest();
        Date date = new Date();
        int count = 10;
        System.out.println(count);
    }

    // init方法
    public LocalVariablesTest() {
        this.count = 1;
    }

    // 局部变脸最大槽数6 1.this 2.3.形参 4.weight (double和long占据两个槽位slot) 5.gender
    public String test2(Date dateP, String name2) {
        dateP = null;
        name2 = "xyf";
        // double long 占据两个slot
        double weight = 120.0;
        long asd = 1L;
        int i = 10;
        char gender = '男';
        return dateP + name2;
    }

    public void test3() {
        count++;
    }

    /*
        栈帧中的局部变量表的槽位是可以重复用的 如果一个局部变量过了其作用域 那么在其作用域之后申明的新的局部变量就很有可能会复用过期
        局部变量的槽位 从而达到节省资源的目的
     */
    public void test4() {
        int a = 0;
        {
            int b = 0;
            b = a + 1;
        }
        // 变量c的位置 复用了变量b的slot槽位
        int c = a + 1;
    }

    public void test5Temp() {
        int num;
        // System.out.println(num); 错误信息 局部变量num未进行初始化
    }

}

