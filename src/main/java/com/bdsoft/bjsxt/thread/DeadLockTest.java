package com.bdsoft.bjsxt.thread;

public class DeadLockTest {

    /**
     * 死锁测试
     */
    public static void main(String[] args) {
        // 资源定义
        String s1 = "bdceo";
        String s2 = "bdcto";

        // 线程开始互斥，死锁
        DeadLock dl1 = new DeadLock(s1, s2);
        Thread t1 = new Thread(dl1, "Th1");
        t1.start();

        DeadLock dl2 = new DeadLock(s2, s1);
        Thread t2 = new Thread(dl2, "Th2");
        t2.start();
    }
}

class DeadLock implements Runnable {

    private Object o1;
    private Object o2;

    public DeadLock(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    /**
     * 嵌套锁结构，外层不释放又去获取新的
     */
    public void run() {
        synchronized (o1) {
            System.out
                    .println(Thread.currentThread().getName() + " hold " + o1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out
                    .println(Thread.currentThread().getName() + " wait " + o2);
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + " hold "
                        + o2);
            }
        }
    }
}