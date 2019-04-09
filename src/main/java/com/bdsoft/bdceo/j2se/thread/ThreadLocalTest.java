package com.bdsoft.bdceo.j2se.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 丁辰叶
 * @date 2015-10-8
 */
public class ThreadLocalTest {

    static ThreadLocal<HashMap> threadLocal = new ThreadLocal<HashMap>() {
        @Override
        protected HashMap initialValue() {
            System.out.println(Thread.currentThread().getName() + ">initialValue");
            return new HashMap();
        }
    };

    public static class T1 implements Runnable {

        int id;

        public T1(int id) {
            this.id = id;
        }

        public void run() {
            Map map = threadLocal.get();
            for (int i = 0; i < 20; i++) {
                map.put(i, i + id * 100);
                System.out.println(Thread.currentThread().getName() + " # " + map);
                try {
                    Thread.sleep(100);
                } catch (Exception ex) {
                }
            }
            System.out.println(Thread.currentThread().getName() + "# map.size()=" + map.size() + " # " + map);
        }
    }

    public static void main(String[] args) {
        Thread[] runs = new Thread[20];
        T1 t = new T1(1);
        for (int i = 0; i < runs.length; i++) {
            // 两种更新数据的方式
            runs[i] = new Thread(t);
//            runs[i] = new Thread(new T1(i));
        }
        for (int i = 0; i < runs.length; i++) {
            runs[i].start();
        }
    }

}
