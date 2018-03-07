package com.bdsoft;

import com.bdsoft.utils.http.BDHttpParam;
import com.bdsoft.utils.http.BDHttpUtil;
import com.google.common.io.Files;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模拟并发
 *
 * @author 丁辰叶
 * @version 1.0.0
 * @date 2016年4月26日
 */
public class BF {

    static long[] uidArr = {1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009, 10010};
    static int rdRang = uidArr.length;

    static List<String> uidList = Collections.emptyList();

    static Random rd = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws Exception {
        // 初始化用户id
        uidList = Files.readLines(new File("d:/download/alpha-user.txt"), Charset.forName("UTF-8"));
//        uidList = uidList.subList(0, 10);
        rdRang = uidList.size();

        // 地址
        String url = "http://aly-alpha-api.shejijia.com/gateway/api/v1/erp/coupons/receive";

        // 并发数
        int bfNum = 1;

        CountDownLatch cdt = new CountDownLatch(1);
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < bfNum; i++) {
            es.execute(new Bingfa(cdt, url));
        }
        cdt.countDown();
        es.shutdown();
    }

    static class Bingfa implements Runnable {

        private CountDownLatch cdt;

        private String url;

        public Bingfa(CountDownLatch cdt, String url) {
            this.cdt = cdt;
            this.url = url;
        }

        public void run() {
            String name = Thread.currentThread().getName();
            try {
                // 准备
                cdt.await();
                // 并发
                System.out.println(String.format("线程>%s，请求>%s", name, url));
                long tmp = System.currentTimeMillis();

                BDHttpParam hp = BDHttpParam.init().addHeader("X-Member-Id", uidList.get(rd.nextInt(rdRang)));
                BDHttpUtil.sendGet(url, hp);

                System.err.println(
                        String.format("线程>%s 执行完，调用comment-server耗时：%d", name, (System.currentTimeMillis() - tmp)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
