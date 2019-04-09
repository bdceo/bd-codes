package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    // 线程池相关测试
    public static void main(String[] args) {
        // 相关类：Executors,ExecutorService,ScheduledExecutorService
        fixedThreadPool1();
        // fixedThreadPool2();
        // cachedThreadPool();
//		 scheduleThreadPool();

        // 相关类：ThreadPoolExecutor,BlockingQueue
//		manualThreadPool();
    }

    public static void fixedThreadPool1() {
        // 固定尺寸线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        // 线程任务对象
        SimpleTask task1 = new SimpleTask("task1");
        SimpleTask task2 = new SimpleTask("task2");
        SimpleTask task3 = new SimpleTask("task3");
        // 从线程池中启动3个任务执行
        System.out.println("1");
        pool.execute(task1);
        System.out.println("2");
        pool.execute(task2);
        System.out.println("3");
        pool.execute(task3);
        // 关闭线程池
        pool.shutdown();
    }

    public static void fixedThreadPool2() {
        // 固定尺寸线程池:单任务
        ExecutorService pool = Executors.newSingleThreadExecutor();
        // 线程任务对象
        SimpleTask task1 = new SimpleTask("task1");
        SimpleTask task2 = new SimpleTask("task2");
        SimpleTask task3 = new SimpleTask("task3");
        // 从线程池中启动3个任务执行
        pool.execute(task1);
        pool.execute(task2);
        pool.execute(task3);
        // 关闭线程池
        pool.shutdown();
    }

    public static void cachedThreadPool() {
        // 可变尺寸线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        // 线程任务对象
        SimpleTask task1 = new SimpleTask("task1");
        SimpleTask task2 = new SimpleTask("task2");
        SimpleTask task3 = new SimpleTask("task3");
        SimpleTask task4 = new SimpleTask("task4");
        SimpleTask task5 = new SimpleTask("task5");
        SimpleTask task6 = new SimpleTask("task6");
        // 从线程池中启动3个任务执行
        pool.execute(task1);
        pool.execute(task2);
        pool.execute(task3);
        pool.execute(task4);
        pool.execute(task5);
        pool.execute(task6);
        // 关闭线程池
        pool.shutdown();
    }

    public static void scheduleThreadPool() {
        // 延迟线程池
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        // 单任务的延迟线程池
        ScheduledExecutorService singlePool = Executors
                .newSingleThreadScheduledExecutor();
        // 线程任务对象
        SimpleTask task1 = new SimpleTask("task1");
        SimpleTask task2 = new SimpleTask("task2");
        SimpleTask task3 = new SimpleTask("task3");
        // 从线程池中启动3个任务执行
        pool.schedule(task1, 2, TimeUnit.SECONDS);
        pool.schedule(task2, 2, TimeUnit.SECONDS);
        pool.schedule(task3, 2, TimeUnit.SECONDS);
        // 关闭线程池
        pool.shutdown();
    }

    public static void manualThreadPool() {
        // 自定义线程池
        // 任务等待队列
        BlockingQueue queue = new ArrayBlockingQueue(2);
        // 自定义线程池:标准2，最大4,
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4, 2,
                TimeUnit.SECONDS, queue);
        // 设定饱和策略，调用者运行
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        // 线程任务对象
        SimpleTask task1 = new SimpleTask("task1");
        SimpleTask task2 = new SimpleTask("task2");
        SimpleTask task3 = new SimpleTask("task3");
        SimpleTask task4 = new SimpleTask("task4");
        // 从线程池中启动3个任务执行
        pool.execute(task1);
        pool.execute(task2);
        pool.execute(task3);
        pool.execute(task4);

        System.out.println("real pool size = " + pool.getPoolSize());
        // 关闭线程池
        pool.shutdown();
    }
}

class SimpleTask implements Runnable {

    private String name;

    public SimpleTask(String n) {
        this.name = n;
    }

    public void run() {
        System.out.println("\n" + name + " start execute");
        for (int i = 1; i <= 51; i++) {
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
        System.out.println("\n" + name + " finish execute");
    }
}
