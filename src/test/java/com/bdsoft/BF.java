package com.bdsoft;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.bdsoft.utils.http.BDHttpParam;
import com.bdsoft.utils.http.BDHttpUtil;

/**
 * 模拟并发
 *
 * @author 丁辰叶
 * @date 2016年4月26日
 * @version 1.0.0
 */
public class BF {

//	static long[] uidArr = { 20738773, 20738772, 20738924, 20738801, 20738771, 20738939, 20736016, 20735592 };
	static long[] uidArr = { 1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009, 10010 };
	static int rdRang = uidArr.length;
	static Random rd = new Random(System.currentTimeMillis());

	public static void main(String[] args) throws Exception {
		for(int i=0;i<rdRang ;i++){
			uidArr[i] = System.nanoTime() + rd.nextInt(1000);
		}

		// 地址
//		String url = "http://alpha-www.gdfcx.net/member-app/v1/api/designers/#uid/score?offset=0&limit=12";
		String url = "http://localhost:8090/api/v1/coupons/issueCoupons?activityId=111544887702192128&platform=10100";

		// 并发数
		int bfNum = 20;

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
//			this.url = url.replaceAll("#uid", String.valueOf(uidArr[rd.nextInt(rdRang)]));
		}

		public void run() {
			String name = Thread.currentThread().getName();
			try {
				// 准备
				cdt.await();
				// 并发
				System.out.println(String.format("线程>%s，请求>%s", name, url));
				long tmp = System.currentTimeMillis();

				BDHttpParam hp = BDHttpParam.init().addHeader("X-Member-Id",String.valueOf(uidArr[rd.nextInt(rdRang)]));
				BDHttpUtil.sendPost(url, hp);

				System.err.println(
						String.format("线程>%s 执行完，调用comment-server耗时：%d", name, (System.currentTimeMillis() - tmp)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
