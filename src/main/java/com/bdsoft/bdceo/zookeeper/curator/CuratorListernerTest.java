package com.bdsoft.bdceo.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * 典型使用场景-事件监听测试
 */
public class CuratorListernerTest {

	final static String ZK_LIST = "192.168.1.10:2181,192.168.1.11:2181,192.168.1.12:2181";
	final static int ZK_TIMEOUT = 50000;

	static String path = "/curator-cache";
	static CuratorFramework client = CuratorFrameworkFactory.builder()
			.connectString(ZK_LIST).sessionTimeoutMs(ZK_TIMEOUT)
			.connectionTimeoutMs(100000)
			.retryPolicy(new ExponentialBackoffRetry(1000, 5)).build();

	/**
	 * 测试入口
	 */
	public static void main(String[] args) throws Exception {

		client.start();

		client.create().creatingParentsIfNeeded()
				.withMode(CreateMode.EPHEMERAL)
				.forPath(path, "init".getBytes());

		// 单节点监听
		final NodeCache cache = new NodeCache(client, path, false);
		cache.start(true);
		cache.getListenable().addListener(new NodeCacheListener() {
			public void nodeChanged() throws Exception {
				if (cache.getCurrentData() == null) {
					System.out.println("Node maybe deleted.");
					return;
				}
				String data = new String(cache.getCurrentData().getData());
				System.out.println("Node data update, new data: " + data);
			}
		});

		client.setData().forPath(path, "hello".getBytes());
		Thread.sleep(1000);
		client.delete().deletingChildrenIfNeeded().forPath(path);

		// 子节点监听
		PathChildrenCache cache2 = new PathChildrenCache(client, path, true);
		cache2.start(StartMode.POST_INITIALIZED_EVENT);
		cache2.getListenable().addListener(new PathChildrenCacheListener() {
			public void childEvent(CuratorFramework client,
					PathChildrenCacheEvent event) throws Exception {
				if (event.getData() == null) {
					System.out.println("ChildrenNode maybe deleted.");
					return;
				}
				String path = event.getData().getPath();
				switch (event.getType()) {
				case CHILD_ADDED:
					System.out.println("CHILD_ADDED :" + path);
					break;
				case CHILD_UPDATED:
					System.out.println("CHILD_UPDATED :" + path);
					break;
				case CHILD_REMOVED:
					System.out.println("CHILD_REMOVED :" + path);
					break;
				default:
					break;
				}
			}
		});

		client.create().withMode(CreateMode.PERSISTENT).forPath(path);
		Thread.sleep(1000);

		client.create().withMode(CreateMode.PERSISTENT).forPath(path + "/c1");
		Thread.sleep(1000);

		client.delete().forPath(path + "/c1");
		Thread.sleep(1000);

		client.delete().forPath(path);

		Thread.sleep(Integer.MAX_VALUE);
	}

}
