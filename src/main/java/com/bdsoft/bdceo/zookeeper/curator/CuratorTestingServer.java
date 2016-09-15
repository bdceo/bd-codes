package com.bdsoft.bdceo.zookeeper.curator;

import java.io.File;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;

/**
 * 本地模拟启动一个标准的 zookeeper 单机服务
 */
public class CuratorTestingServer {

	/**
	 * 测试入口
	 */
	public static void main(String[] args) throws Exception {
		String path = "/zookeeper";

		TestingServer server = new TestingServer(2181, new File(
				"/download/zk/data"));

		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString(server.getConnectString())
				.sessionTimeoutMs(50000).connectionTimeoutMs(100000)
				.retryPolicy(new ExponentialBackoffRetry(1000, 5)).build();
		client.start();

		System.out.println(client.getChildren().forPath(path));

		server.close();
	}

}
