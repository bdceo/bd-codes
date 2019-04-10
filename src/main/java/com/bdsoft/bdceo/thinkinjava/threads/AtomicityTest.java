package com.bdsoft.bdceo.thinkinjava.threads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程，原子性测试
 */
public class AtomicityTest implements Runnable {

    private int i = 0;

    // 非线程安全，且容易读到中间状态的值-奇数
    public int getValue() {
        // public synchronized int getValue() {
        return i;
    }

    // 偶数递增，线程安全，原子性
    public synchronized void evenIncrement() {
        i++;
        i++;
    }

    @Override
    public void run() {
        while (true) {
            evenIncrement();
        }
    }

    /**
     * 入口
     */
    public static void main(String[] args) throws Exception {
//		 test1(); // 发现奇数，退出
        test2(); // 不会退出
    }

    static void test1() {
        AtomicityTest at = new AtomicityTest();
        new Thread(at).start();

        // 发现奇数，程序退出
        while (true) {
            int val = at.getValue();
            if (val % 2 != 0) {
                System.out.println(val); // 1，13，7
                System.exit(0);
            }
        }
    }

    static void test2() {
        AtomicityTest2 at = new AtomicityTest2();
        new Thread(at).start();

        // 程序不会退出
        while (true) {
            int val = at.getValue();
            if (val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }

}

/**
 * 使用AtomicInteger 替代基础类型int，使用其特有api进行原子更新操作
 */
class AtomicityTest2 implements Runnable {

    private AtomicInteger i = new AtomicInteger(0);

    // 方法无需同步，api保证其线程安全
    public int getValue() {
        return i.get();
    }

    // 方法无需同步，api保证其线程安全
    public void evenIncrement() {
        i.addAndGet(2);
    }

    @Override
    public void run() {
        while (true) {
            evenIncrement();
        }
    }
}
