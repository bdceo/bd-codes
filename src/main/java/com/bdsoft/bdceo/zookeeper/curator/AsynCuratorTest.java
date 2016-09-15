package com.bdsoft.bdceo.zookeeper.curator;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * Curator-framework 异步接口 测试
 */
public class AsynCuratorTest {

	final static String ZK_LIST = "192.168.1.10:2181,192.168.1.11:2181,192.168.1.12:2181";
	final static int ZK_TIMEOUT = 50000;

	static String path = "/curator-asyn";
	static CuratorFramework client = CuratorFrameworkFactory.builder()
			.connectString(ZK_LIST).sessionTimeoutMs(ZK_TIMEOUT)
			.connectionTimeoutMs(100000)
			.retryPolicy(new ExponentialBackoffRetry(1000, 5)).build();

	static CountDownLatch semaphore = new CountDownLatch(2);
	static ExecutorService tp = Executors.newFixedThreadPool(2);

	/**
	 * Curator-测试入口
	 */
	public static void main(String[] args) throws Exception {
		client.start();

		System.out.println("Main thread: " + Thread.currentThread().getName());

		// 传入自定义线程池
		client.create().creatingParentsIfNeeded()
				.withMode(CreateMode.EPHEMERAL)
				.inBackground(new BackgroundCallback() {
					public void processResult(CuratorFramework client,
							CuratorEvent event) throws Exception {
						String msg = String.format("event[code:%d, type: %s]",
								event.getResultCode(), event.getType());
						System.out.println(msg);
						msg = String.format("Thread of processResult: %s",
								Thread.currentThread().getName());
						System.out.println(msg);
						semaphore.countDown();
					}
				}, tp).forPath(path, "init".getBytes());

		// 没传自定义线程池
		client.create().creatingParentsIfNeeded()
				.withMode(CreateMode.EPHEMERAL)
				.inBackground(new BackgroundCallback() {
					public void processResult(CuratorFramework client,
							CuratorEvent event) throws Exception {
						String msg = String.format("event[code:%d, type: %s]",
								event.getResultCode(), event.getType());
						System.out.println(msg);
						msg = String.format("Thread of processResult: %s",
								Thread.currentThread().getName());
						System.out.println(msg);
						semaphore.countDown();
					}
				}).forPath(path, "init".getBytes());

		// 阻塞
		semaphore.await();

		// 关闭线程池
		tp.shutdown();

		System.out.println("curator-asyn test finish.");
	}

}
