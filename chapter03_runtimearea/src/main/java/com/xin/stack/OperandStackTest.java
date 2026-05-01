package com.xin.stack;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-04-28 10:59
 * @github https://github.com/xyf527
 * @copyright
 */

public class OperandStackTest {

    /*
    public void testAddOperation();
        Code:
         0 bipush 15
         2 istore_1
         3 bipush 8
         5 istore_2
         6 iload_1
         7 iload_2
         8 iadd
         9 istore_3
        10 ldc2_w #2 <12>
        13 lstore 4
        15 sipush 800
        18 istore 6
        20 lload 4
        22 iload 6
        24 i2l
        25 lmul
        26 lstore 4
        28 return
        操作数栈 在方法执行过程中 根据字节码指令 并非采用访问索引的方式来进行数据访问的 而是只能通过标准的入栈(push)和出栈(pop)操作
        往栈中写入数据或提取数据来完成一次数据访问

        如果被调用的方法带有返回值的话 其返回值将会被压入当前栈帧的操作数栈中 并更新PC寄存器中下一条需要执行的字节码指令

        栈顶缓存技术: 将栈顶元素全部缓存在物理CPU的寄存器中
     */
    public void testAddOperation() {
        byte i = 15;
        int j = 8;
        int k = i + j;

        long m = 12L;
        int n = 800;
        // 存在宽化类型处理
        // i2l
        m = m * n;
    }

    public int getSum() {
        int m = 10;
        int n = 20;
        int k = m + n;
        System.out.println(k);
        return k;
    }

    public void testGetSum() {
        int i = getSum();
        int j = 10;
    }

}
