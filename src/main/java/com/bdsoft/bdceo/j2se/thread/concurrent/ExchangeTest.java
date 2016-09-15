/**
 * ExchangeTest.java
 * com.bdsoft.bdceo.j2se.thread.concurrent
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
*/
package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 参考： 
 * http://blog.csdn.net/saberming/article/details/6292820
 * http://www.cnblogs.com/whgw/archive/2011/09/29/2195874.html
 * 
 * @author	丁辰叶
 * @date	2014-8-7
 */
public class ExchangeTest {

	/**
	 * Exchanger，提供线程间的数据交换支持
	 * 必须是偶数个线程间的交换
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();

		final Exchanger<String> change = new Exchanger<String>();

		exchangeData(service, change, "111");
		exchangeData(service, change, "222");
		exchangeData(service, change, "333");
		exchangeData(service, change, "444");
		exchangeData(service, change, "555");
		exchangeData(service, change, "666");

		service.shutdown();
	}

	static void exchangeData(ExecutorService service, final Exchanger<String> change, final String data) {
		service.execute(new Runnable() {
			public void run() {
				String name = Thread.currentThread().getName();
				try {
					System.out.println(String.format("%s 交换出去数据 > %s", name, data));
					Thread.sleep(1000);
					String got = (String) change.exchange(data, 1000, TimeUnit.MILLISECONDS);
					System.out.println(String.format("%s 获取交换数据 < %s", name, got));
				} catch (Exception e) {
				}
			}
		});
	}

}
