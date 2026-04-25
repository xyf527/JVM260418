package com.xin.java;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-04-23 09:35
 * @github https://github.com/xyf527
 * @copyright
 */

class StaticA {
    static {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            Class.forName("com.xin.java.StaticB");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("StaticA init OK");
    }
}

class StaticB {
    static {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            Class.forName("com.xin.java.StaticA");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("StaticB init OK");
    }
}

public class StaticDeadLockMain extends Thread {

    private char flag;

    public StaticDeadLockMain(char flag) {
        this.flag = flag;
        this.setName("Thread" + flag);
    }

    @Override
    public void run() {
        try {
            Class.forName("com.xin.java.Static" + flag);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(getName() + " over");
    }

    public static void main(String[] args) {
        StaticDeadLockMain loadA = new StaticDeadLockMain('A');
        loadA.start();
        StaticDeadLockMain loadB = new StaticDeadLockMain('B');
        loadB.start();
    }

}
