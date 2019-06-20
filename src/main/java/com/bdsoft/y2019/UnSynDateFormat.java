package com.bdsoft.y2019;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 日期格式未同步
 */
public class UnSynDateFormat {

    static String pattern = "yyyy-M-dd HH:mm:ss";
    static Random random = new Random(2019);
    static Long now = System.currentTimeMillis();

    /**
     * 日期格式未同步。 建议为每个线程创建单独的格式实例。 如果多个线程同时访问格式，则必须在外部进行同步。
     */
    public static void main(String[] args) {
        bind();
//        share();
    }

    static void bind() {
        int bfSize = 500;
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService pool = Executors.newFixedThreadPool(bfSize);
        // 每个线程绑定一个
        for (int i = 0; i < bfSize; i++) {
            Date date = new Date(now + random.nextInt(60 * 60 * 1000));
            Mock mock = new Mock(date, new SimpleDateFormat(pattern), latch);
            pool.execute(mock);
        }
        latch.countDown();
        pool.shutdown();
    }

    static void share() {
        int bfSize = 20;
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService pool = Executors.newFixedThreadPool(bfSize);
        // 多个线程绑定同一个
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        for (int i = 0; i < bfSize; i++) {
            Date date = new Date(now + random.nextInt(60 * 60 * 1000));
            Mock mock = new Mock(date, sdf, latch);
            pool.execute(mock);
        }
        latch.countDown();
        pool.shutdown();
    }

}

class Mock implements Runnable {

    private Date date;
    private SimpleDateFormat sdf;

    private CountDownLatch latch;

    public Mock(Date date, SimpleDateFormat sdf, CountDownLatch latch) {
        this.date = date;
        this.sdf = sdf;
        this.latch = latch;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        try {
            latch.await();
            String res = sdf.format(date);
            System.out.println(String.format("%s == %s, %s", name, date.toLocaleString(), res));
            if (!res.equals(date.toLocaleString())) {
                throw new RuntimeException(String.format("%s neq", name));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
