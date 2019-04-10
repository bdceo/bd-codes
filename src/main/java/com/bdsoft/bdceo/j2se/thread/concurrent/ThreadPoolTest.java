package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.util.concurrent.*;

public class ThreadPoolTest {

    /**
     * 线程池测试：
     * Executors, ExecutorService, ScheduledExecutorService
     * <p>
     * ThreadPoolExecutor, BlockingQueue
     */
    public static void main(String[] args) {
//        fixedThreadPool1(); // 固定尺寸
//        fixedThreadPool2(); // 单线程线程池
//        cachedThreadPool(); // 可变线程池，适合短小任务
//        scheduleThreadPool(); // 可调度线程池，支持延迟启动
        manualThreadPool(); // 自定义线程池
    }

    public static void fixedThreadPool1() {
        // 固定尺寸线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        // 从线程池中启动3个任务执行
        pool.execute(new SimpleTask("A"));
        pool.execute(new SimpleTask("B"));
        pool.execute(new SimpleTask("C"));
        // 关闭线程池
        pool.shutdown();
    }

    public static void fixedThreadPool2() {
        // 固定尺寸线程池:单任务
        ExecutorService pool = Executors.newSingleThreadExecutor();
        // 从线程池中启动3个任务执行
        pool.execute(new SimpleTask("A"));
        pool.execute(new SimpleTask("B"));
        pool.execute(new SimpleTask("C"));
        // 关闭线程池
        pool.shutdown();
    }

    public static void cachedThreadPool() {
        // 可变尺寸线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        // 从线程池中启动3个任务执行
        pool.execute(new SimpleTask("A"));
        pool.execute(new SimpleTask("B"));
        pool.execute(new SimpleTask("C"));
        pool.execute(new SimpleTask("D"));
        pool.execute(new SimpleTask("E"));
        pool.execute(new SimpleTask("F"));
        // 关闭线程池
        pool.shutdown();
    }

    public static void scheduleThreadPool() {
        // 延迟线程池，指定同一时间，最多2个线程同时运行
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        // 从线程池中启动4个任务执行
        pool.schedule(new SimpleTask("A"), 2, TimeUnit.SECONDS);
        pool.schedule(new SimpleTask("B"), 2, TimeUnit.SECONDS);
        pool.schedule(new SimpleTask("C"), 2, TimeUnit.SECONDS);
        pool.schedule(new SimpleTask("D"), 2, TimeUnit.SECONDS);
        // 关闭线程池
        pool.shutdown();
    }

    public static void manualThreadPool() {
        // 任务等待队列
        BlockingQueue queue = new ArrayBlockingQueue(2);
        // 自定义线程池:标准2，最大4,
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4, 1,
                TimeUnit.SECONDS, queue);
        // 设定饱和策略，调用者运行
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        // 从线程池中启动任务执行
        pool.execute(new SimpleTask("A"));
        pool.execute(new SimpleTask("B"));
        pool.execute(new SimpleTask("C"));
        pool.execute(new SimpleTask("D"));
        pool.execute(new SimpleTask("E"));
        pool.execute(new SimpleTask("F"));

        // 关闭线程池
        pool.shutdown();
    }
}

/**
 * 任务模拟器
 */
class SimpleTask implements Runnable {

    private String name;

    public SimpleTask(String n) {
        this.name = n;
    }

    @Override
    public void run() {
        System.out.println("\n" + name + " Start!");
        for (int i = 1; i <= 50; i++) {
            System.out.print("[" + name + "-" + i + "]");
            if (i % 5 == 0) {
                System.out.println();
            }
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n" + name + " Over!");
    }
}
