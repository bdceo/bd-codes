package com.bdsoft.bdceo.zookeeper.curator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 典型使用场景-分布式锁测试
 */
public class CuratorLockTest {

	final static String ZK_LIST = "192.168.1.10:2181,192.168.1.11:2181,192.168.1.12:2181";
	final static int ZK_TIMEOUT = 50000;

	static String path = "/curator-lock";
	static CuratorFramework client = CuratorFrameworkFactory.builder()
			.connectString(ZK_LIST).sessionTimeoutMs(ZK_TIMEOUT)
			.connectionTimeoutMs(100000)
			.retryPolicy(new ExponentialBackoffRetry(1000, 5)).build();

	/**
	 * 测试入口
	 */
	public static void main(String[] args) {
		// 普通单机并发，出现订单号重复
		final CountDownLatch down = new CountDownLatch(1);
		for (int i = 0; i < 20; i++) {
			new Thread(new Runnable() {
				public void run() {
					try {
						down.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
					String oid = sdf.format(new Date());
					System.out.println("单机-订单号：" + oid);
				}
			}).start();
		}
		down.countDown();

		// 分布式锁
		client.start();
		final InterProcessMutex lock = new InterProcessMutex(client, path);
		final CountDownLatch down2 = new CountDownLatch(1);
		for (int i = 0; i < 30; i++) {
			new Thread(new Runnable() {
				public void run() {
					try {
						down2.await();// 全部等待
						lock.acquire(); // 获取锁
					} catch (Exception e) {
						e.printStackTrace();
					}
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
					String oid = sdf.format(new Date());
					System.out.println("分布式-订单号：" + oid);
					try {
						lock.release();// 释放锁
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		down2.countDown();// 并发开始
	}

}
