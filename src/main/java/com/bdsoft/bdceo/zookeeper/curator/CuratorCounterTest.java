package com.bdsoft.bdceo.zookeeper.curator;

import java.util.Random;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

/**
 * 典型使用场景-分布式计数器测试
 */
public class CuratorCounterTest {

	final static String ZK_LIST = "192.168.1.10:2181,192.168.1.11:2181,192.168.1.12:2181";
	final static int ZK_TIMEOUT = 50000;

	static String path = "/curator-counter";
	static CuratorFramework client = CuratorFrameworkFactory.builder()
			.connectString(ZK_LIST).sessionTimeoutMs(ZK_TIMEOUT)
			.connectionTimeoutMs(100000)
			.retryPolicy(new ExponentialBackoffRetry(1000, 5)).build();

	/**
	 * 测试入口
	 */
	public static void main(String[] args) throws Exception {
		client.start();

		DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(
				client, path, new RetryNTimes(3, 1000));
		
		int jia = new Random().nextInt(100);
		AtomicValue<Integer> rc = atomicInteger.add(jia);
		System.out.println("pre: " + rc.preValue());
		System.out.println("add: " + jia);
		System.out.println("post: " + rc.postValue());
		System.out.println("Result: " + rc.succeeded());
	}

}
