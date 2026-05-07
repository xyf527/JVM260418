package com.xin.cpu;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-05-07 11:00
 * @github https://github.com/xyf527
 * @copyright
 */

public class JstackDeadLockDemo {

    public final Object obj1 = new Object();
    public final Object obj2 = new Object();

    public static void main(String[] args) {
        new JstackDeadLockDemo().testDeadLock();
    }

    private void testDeadLock() {
        Thread t1 = new Thread(() -> calLock_Obj1_First());
        Thread t2 = new Thread(() -> calLock_Obj2_First());
        t1.start();
        t2.start();
    }

    private void calLock_Obj1_First() {
        synchronized (obj1) {
            sleep();
            System.out.println("已经拿到obj1的对象锁 接下来等待obj2的对象锁");
            synchronized (obj2) {
                sleep();
            }
        }
    }

    private void calLock_Obj2_First() {
        synchronized (obj2) {
            sleep();
            System.out.println("已经拿到obj2的对象锁 接下来等待obj1的对象锁");
            synchronized (obj1) {
                sleep();
            }
        }
    }

    // 为了便于让两个线程分别锁住其中一个对象 一个线程锁住obj1 然后一直等待obj2 另一个线程锁住obj2 然后一直等待obj1
    // 然后就是一直等待 死锁产生
    private void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
