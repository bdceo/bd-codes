package com.bdsoft.y2015.m12;

public class JavaThread {

    public static void main(String[] args) {

        SaleThread st = new SaleThread();

        for (int i = 0; i < 20; i++) {
            Thread td = new Thread(st);
            td.start();
        }
    }

}

/**
 * 线程安全：无论开启多少个线程，线程内部对统一实例进行同步锁
 */
class SaleThread implements Runnable {

    int num = 10;

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        while (true) {
            synchronized (this) {// 锁的是 SaleThread 实例
                if (num > 0) {
                    System.out.println(String.format("售票口：%s，买了%d张票，还剩%d张票",
                            name, 1, --num));
                }
                if (num == 0) {
                    break;
                }
            }
        }
        System.out.println(String.format("线程：%s退出", Thread.currentThread().getName()));
    }

}
