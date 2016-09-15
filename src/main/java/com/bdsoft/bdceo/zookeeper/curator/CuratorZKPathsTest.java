package com.bdsoft.bdceo.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.apache.curator.utils.ZKPaths.PathAndNode;
import org.apache.zookeeper.ZooKeeper;

/**
 * 工具-ZKPaths 测试
 */
public class CuratorZKPathsTest {

	final static String ZK_LIST = "192.168.1.10:2181,192.168.1.11:2181,192.168.1.12:2181";
	final static int ZK_TIMEOUT = 50000;

	static String path = "/curator-zkpaths";
	static CuratorFramework client = CuratorFrameworkFactory.builder()
			.connectString(ZK_LIST).sessionTimeoutMs(ZK_TIMEOUT)
			.connectionTimeoutMs(100000)
			.retryPolicy(new ExponentialBackoffRetry(1000, 5)).build();

	/**
	 * 测试入口
	 */
	public static void main(String[] args) throws Exception {
		client.start();

		ZooKeeper zookeeper = client.getZookeeperClient().getZooKeeper();

		System.out.println(ZKPaths.fixForNamespace(path, "sub"));
		System.out.println(ZKPaths.makePath(path, "sub"));

		System.out.println(ZKPaths.getNodeFromPath(path + "/sub1"));

		PathAndNode pn = ZKPaths.getPathAndNode(path + "/sub1");
		System.out.println(pn.getPath());
		System.out.println(pn.getNode());

		String path1 = path + "/child1";
		String path2 = path + "/child2";
		ZKPaths.mkdirs(zookeeper, path1);
		ZKPaths.mkdirs(zookeeper, path2);
		System.out.println(ZKPaths.getSortedChildren(zookeeper, path));

		ZKPaths.deleteChildren(zookeeper, path, true);
	}

}
