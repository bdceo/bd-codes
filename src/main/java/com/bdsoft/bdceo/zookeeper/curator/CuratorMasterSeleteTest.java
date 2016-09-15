package com.bdsoft.bdceo.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 典型使用场景-Master选举测试
 */
public class CuratorMasterSeleteTest {

	final static String ZK_LIST = "192.168.1.10:2181,192.168.1.11:2181,192.168.1.12:2181";
	final static int ZK_TIMEOUT = 50000;

	static String path = "/curator-master";
	static CuratorFramework client = CuratorFrameworkFactory.builder()
			.connectString(ZK_LIST).sessionTimeoutMs(ZK_TIMEOUT)
			.connectionTimeoutMs(100000)
			.retryPolicy(new ExponentialBackoffRetry(1000, 5)).build();

	/**
	 * 测试入口
	 */
	public static void main(String[] args) throws Exception {
		client.start();

		LeaderSelector selector = new LeaderSelector(client, path,
				new LeaderSelectorListenerAdapter() {
					public void takeLeadership(CuratorFramework client)
							throws Exception {
						System.out.println("成为Master角色");
						Thread.sleep(3000);
						System.out.println("完成Master操作，释放Master权利");
					}
				});

		selector.autoRequeue();
		selector.start();

		Thread.sleep(Integer.MAX_VALUE);
	}

}
