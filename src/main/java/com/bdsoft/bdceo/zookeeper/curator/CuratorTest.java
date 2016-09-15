package com.bdsoft.bdceo.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * Curator-framework 测试
 */
public class CuratorTest {

	final static String ZK_LIST = "192.168.1.10:2181,192.168.1.11:2181,192.168.1.12:2181";
	final static int ZK_TIMEOUT = 50000;

	/**
	 * Curator-测试入口
	 */
	public static void main(String[] args) throws Exception {
		// 重试机制
		RetryPolicy retry = new ExponentialBackoffRetry(1000, 5);
		// 通过工厂创建客户端
		CuratorFramework client = CuratorFrameworkFactory.newClient(ZK_LIST,
				ZK_TIMEOUT, 10000, retry);

		// fluent 风格创建
		// CuratorFramework clientFluent = CuratorFrameworkFactory.builder()
		// .connectString(ZK_LIST).sessionTimeoutMs(5000)
		// .retryPolicy(retry).namespace("base").build();
		// clientFluent.start();

		// 启动客户端连接
		client.start();

		// fluent-api测试
		// 创建节点
		String path = "/curator-test";
		client.create().forPath(path);
		client.create().forPath(path + "/data", "dddd".getBytes());
		// 指定创建模式
		client.create().withMode(CreateMode.EPHEMERAL)
				.forPath(path + "/sub-temp");
		// 自动创建父目录
		client.create().creatingParentsIfNeeded()
				.withMode(CreateMode.EPHEMERAL).forPath(path + "/sub/haha");
		client.delete().deletingChildrenIfNeeded().forPath(path);

		// 删除节点
		path = "/curator-delete/sub";
		client.create().creatingParentsIfNeeded()
				.withMode(CreateMode.EPHEMERAL)
				.forPath(path, "bdceo".getBytes());
		Stat stat = new Stat();
		System.out.println(new String(client.getData().storingStatIn(stat)
				.forPath(path)));
		client.delete().deletingChildrenIfNeeded()
				.withVersion(stat.getVersion()).forPath(path);

		// 更新数据
		path = "/curator-setdata";
		client.create().withMode(CreateMode.EPHEMERAL)
				.forPath(path, "bdceo".getBytes());
		Stat stat2 = new Stat();
		System.out.println(new String(client.getData().storingStatIn(stat2)
				.forPath(path)));
		int ver = client.setData().withVersion(stat2.getVersion())
				.forPath(path, "bdcoo".getBytes()).getVersion();
		System.out.println(String.format(
				"Success set node for : %s, new version: %d", path, ver));
		try {// 错误的版本更新
			client.setData().withVersion(stat2.getVersion()).forPath(path);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		client.delete().deletingChildrenIfNeeded().forPath(path);

		System.out.println("curator test finish.");
	}
}
