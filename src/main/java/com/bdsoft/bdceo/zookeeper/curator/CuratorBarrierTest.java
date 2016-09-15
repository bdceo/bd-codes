package com.bdsoft.bdceo.zookeeper.curator;

import java.util.Date;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 典型使用场景-分布式同步控制器测试
 */
public class CuratorBarrierTest {

	final static String ZK_LIST = "192.168.1.10:2181,192.168.1.11:2181,192.168.1.12:2181";
	final static int ZK_TIMEOUT = 50000;

	static String path = "/curator-barrier";

	// 单机障碍锁
	public static CyclicBarrier barrier = new CyclicBarrier(3);

	// 分布式障碍锁
	public static DistributedBarrier disBarrier;

	/**
	 * 测试入口
	 */
	public static void main(String[] args) throws Exception {
		// jdk原生提供的并发障碍锁
		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.submit(new Thread(new Runner("bdceo")));
		executor.submit(new Thread(new Runner("bdcfo")));
		executor.submit(new Thread(new Runner("bdcto")));
		executor.shutdown();

		// 分布式障碍器实现-需要主线程释放障碍锁
		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				public void run() {
					CuratorFramework client = CuratorFrameworkFactory.builder()
							.connectString(ZK_LIST)
							.sessionTimeoutMs(ZK_TIMEOUT)
							.connectionTimeoutMs(50000)
							.retryPolicy(new ExponentialBackoffRetry(1000, 3))
							.build();
					client.start();
					String name = Thread.currentThread().getName();
					disBarrier = new DistributedBarrier(client, path);
					try {
						System.out.println(name + " setBarrier");
						disBarrier.setBarrier();// 设置
						System.out.println(name + " waitOnBarrier");
						disBarrier.waitOnBarrier();// 等待释放
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println(name + " 启动...");
				}
			}).start();
		}

		Thread.sleep(15000);
		disBarrier.removeBarrier();// 主线程触发释放
		System.out.println("Thread-main removeBarrier");

		// 分布式障碍器实现-线程自发触发释放
		final int bc = 5;
		for (int i = 0; i < bc; i++) {
			new Thread(new Runnable() {
				public void run() {
					CuratorFramework client = CuratorFrameworkFactory.builder()
							.connectString(ZK_LIST)
							.sessionTimeoutMs(ZK_TIMEOUT)
							.connectionTimeoutMs(50000)
							.retryPolicy(new ExponentialBackoffRetry(1000, 3))
							.build();
					client.start();
					String name = Thread.currentThread().getName();
					DistributedDoubleBarrier ddBarrier = new DistributedDoubleBarrier(
							client, path, bc);
					try {
						Thread.sleep(Math.round(Math.random() * 3000));
						System.out.println(name + " wait enter "
								+ new Date().toLocaleString());
						ddBarrier.enter(); // 进入，等待
						System.out.println(name + " enter "
								+ new Date().toLocaleString());
						Thread.sleep(Math.round(Math.random() * 3000));
						System.out.println(name + " ready leave "
								+ new Date().toLocaleString());
						ddBarrier.leave();// 离开
						System.out.println(name + " leave "
								+ new Date().toLocaleString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}

}

class Runner implements Runnable {

	private String name;

	public Runner(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(name + " 准备好了");
		try {
			CuratorBarrierTest.barrier.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(name + " 起跑！");
	}

}
