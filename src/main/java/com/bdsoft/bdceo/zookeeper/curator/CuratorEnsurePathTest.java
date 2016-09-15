package com.bdsoft.bdceo.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.EnsurePath;

/**
 * 工具-EnsurePath 测试
 */
public class CuratorEnsurePathTest {

	final static String ZK_LIST = "192.168.1.10:2181,192.168.1.11:2181,192.168.1.12:2181";
	final static int ZK_TIMEOUT = 50000;

	static String path = "/curator-ensurepath/sub";
	static CuratorFramework client = CuratorFrameworkFactory.builder()
			.connectString(ZK_LIST).sessionTimeoutMs(ZK_TIMEOUT)
			.connectionTimeoutMs(100000)
			.retryPolicy(new ExponentialBackoffRetry(1000, 5)).build();

	/**
	 * 测试入口
	 */
	public static void main(String[] args) throws Exception {
		client.start();
		client.usingNamespace("ensurepath");
		
		EnsurePath ensurePath = new EnsurePath(path);
		ensurePath.ensure(client.getZookeeperClient());

		// 切换工作空间
		EnsurePath ensurePath2 = client.newNamespaceAwareEnsurePath("/sub");
		ensurePath2.ensure(client.getZookeeperClient());
	}

}
