package com.bdsoft.bdceo.zookeeper.zkclient;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * ZkClient 测试
 */
public class ClientTest {

	final static String ZK_LIST = "192.168.1.10:2181,192.168.1.11:2181,192.168.1.12:2181";
	final static int ZK_TIMEOUT = 50000;

	/**
	 * ZkClient-测试入口
	 */
	public static void main(String[] args) throws Exception {
		ZkClient zkClient = new ZkClient(ZK_LIST, ZK_TIMEOUT);
		System.out.println("ZooKeeper session established.");

		String path = "/zk-client";
		zkClient.deleteRecursive(path);
		// 自动递归创建父节点
		zkClient.createPersistent(path + "/sub", true);
		zkClient.createPersistent(path + "/sub/ssb1");
		zkClient.createPersistent(path + "/sub/ssb2");

		// 自动遍历删除所有子节点
		zkClient.createPersistent(path + "/sub2", true);
		zkClient.deleteRecursive("/zk-client/sub");

		// 获取子节点列表
		path = "/zk-getchild";
		// 注册子节点变更通知
		zkClient.subscribeChildChanges(path, new IZkChildListener() {
			public void handleChildChange(String parentPath,
					List<String> currentChilds) throws Exception {
				System.out.println(String.format(
						"%s 's child changed, currentChilds : %s", parentPath,
						currentChilds));
			}
		});
		zkClient.createPersistent(path);
		Thread.sleep(1000);
		System.out.println(zkClient.getChildren(path));
		Thread.sleep(1000);
		zkClient.createPersistent(path + "/sub1");
		Thread.sleep(1000);
		zkClient.delete(path + "/sub1");
		Thread.sleep(1000);
		zkClient.delete(path);

		// 获取数据内容
		path = "/zk-getdata";
		zkClient.createEphemeral(path, "hello zookeeper");
		// 注册数据变更通知
		zkClient.subscribeDataChanges(path, new IZkDataListener() {
			public void handleDataDeleted(String dataPath) throws Exception {
				System.out.println("Node " + dataPath + " deleted");
			}

			public void handleDataChange(String dataPath, Object data)
					throws Exception {
				System.out.println("Node " + dataPath + " changed, new data: "
						+ data);
			}
		});
		System.out.println(""+zkClient.readData(path));
		// 修改数据内容
		zkClient.writeData(path, "yes");
		Thread.sleep(1000);
		zkClient.delete(path);
		// 检测节点是否存在
		System.out.println(path + " is exists : " + zkClient.exists(path));

		System.out.println("test finish.");
		Thread.sleep(Integer.MAX_VALUE);
	}
}
