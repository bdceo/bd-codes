/**
 * SafeList.java
 * com.bdsoft.y2015.m09
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
*/
package com.bdsoft.y2015.m09;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

/**
 * 
 * @author	丁辰叶
 * @date	2015-9-23
 */
public class SafeList {

	public static void main(String[] args) throws Exception{

		final Client cli = new Client();

		int subt = 10; 

		final CyclicBarrier cBarrier = new CyclicBarrier(subt, new Runnable() {
			public void run() {
				System.out.println("cb end thread exe.");
			}
		});

		for (int i = 0; i < subt; i++) {
			new Thread(new Runnable() {
				public void run() {
					List data = new ArrayList();
					for (int j = 0; j < 1000; j++) {
						data.add(Thread.currentThread().getName() + ">>" + j);						
					}
					try {
						cBarrier.await();
						cli.convert(data);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}

}

class Client {

	public void convert(List data) {
		for (Iterator iterator = data.iterator(); iterator.hasNext();) {
			System.out.println(iterator.next().toString());
		}
	}

}
