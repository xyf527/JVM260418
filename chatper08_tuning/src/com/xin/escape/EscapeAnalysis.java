package com.xin.escape;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-05-06 16:40
 * @github https://github.com/xyf527
 * @copyright
 */

/*
 在JDK 6u23版本之后 HotSpot中默认就已经开启了逃逸分析
 如果使用的是较早版本 可以通过选项-XX:-DoEscapeAnalysis 关闭逃逸分析
 -XX:+PrintEscapeAnalysis查看逃逸分析的筛选结果

 如何快速的判断是否发生了逃逸分析 就看new的对象实体是否有可能在方法外被调用

 使用逃逸分析 编译器可以对代码做如下优化
 栈上分配 将堆分配转换为栈分配 如果经过逃逸分析后发现 一个对象并没有逃逸出方法的话 那么就可能被优化成栈上分配
 这样就无需在堆上分配内存 也无需进行垃圾回收了 可以减少垃圾回收的时间和次数

 JIT在编译期间根据逃逸分析的结果 发现如果一个对象并没有逃逸出方法的话 就可能被油画城栈上分配 分配完成后
 继续在调用栈内执行 最后线程结束 栈空间被回收 局部变量对象也被回收 这样就无需进行垃圾回收了

 结论: 开发中能使用局部变量的 就不要使用在方法外定义
 */
public class EscapeAnalysis {

    public EscapeAnalysis obj;

    // 方法返回EscapeAnalysis对象 发生逃逸
    public EscapeAnalysis getInstance() {
        return obj == null ? new EscapeAnalysis() : obj;
    }

    // 为成员属性赋值 发生逃逸
    public void setObj() {
        this.obj = new EscapeAnalysis();
    }
    // 如果当前的obj引用声明为static的 会发生逃逸吗? 会 且更加恶劣

    // 对象的作用域尽在当前方法中有效 没有发生逃逸
    public void useEscapeAnalysis() {
        EscapeAnalysis e = new EscapeAnalysis();
        // return e; 发生了逃逸
    }

    // 引用成员变量的值 发生逃逸
    public void useEscapeAnalysis1() {
        EscapeAnalysis e = getInstance();
        // getInstance().xxx() 同样也会发生逃逸
    }

    // 发生逃逸
    public void operate(EscapeAnalysis escapeAnalysis) {

    }

}
