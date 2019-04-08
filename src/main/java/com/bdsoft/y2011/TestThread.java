package com.bdsoft.y2011;

public class TestThread {

    /**
     * 测试线程优先级
     */
    public static void main(String[] args) {
        Thread t1 = new Thread(new Imp1());
        Thread t2 = new Thread(new Imp2());
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
    }
}

/**
 * 低优先级
 */
class Imp1 implements Runnable {
    public void run() {
        for (int i = 1; i <= 150; i++) {
            if (i % 10 == 0) {
                System.out.println();
            }
            System.out.print("-" + i + "-");
        }
    }
}

/**
 * 高优先级
 */
class Imp2 implements Runnable {
    public void run() {
        for (int i = 1; i <= 150; i++) {
            if (i % 10 == 0) {
                System.out.println();
            }
            System.out.print("+" + i + "+");
        }
    }
}
