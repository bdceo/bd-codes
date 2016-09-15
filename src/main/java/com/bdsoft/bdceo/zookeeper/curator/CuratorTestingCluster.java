package com.bdsoft.bdceo.zookeeper.curator;

import org.apache.curator.test.TestingCluster;
import org.apache.curator.test.TestingZooKeeperServer;

/**
 * 本地模拟启动一个标准的 zookeeper 集群服务
 */
public class CuratorTestingCluster {

	/**
	 * 测试入口
	 */
	public static void main(String[] args) throws Exception {
		// 本机模拟启动 3个节点的zk服务
		TestingCluster cluster = new TestingCluster(3);
		cluster.start();
		Thread.sleep(2000);

		TestingZooKeeperServer leader = null;
		for (TestingZooKeeperServer zs : cluster.getServers()) {
			System.out.println(zs.getInstanceSpec().getServerId() + "-");
			System.out.println(zs.getQuorumPeer().getServerState() + "-");
			System.out.println(zs.getInstanceSpec().getDataDirectory()
					.getAbsolutePath());
			if (zs.getQuorumPeer().getServerState().equals("leading")) {
				leader = zs;
			}
		}

		// 杀死 主zk服务
		leader.kill();
		System.out.println("--After leader kill:");
		
		for (TestingZooKeeperServer zs : cluster.getServers()) {
			System.out.println(zs.getInstanceSpec().getServerId() + "-");
			System.out.println(zs.getQuorumPeer().getServerState() + "-");
			System.out.println(zs.getInstanceSpec().getDataDirectory()
					.getAbsolutePath());
		}

		cluster.stop();
	}

}
