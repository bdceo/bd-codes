package com.bdsoft.bdceo.zookeeper.api;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * ZK-api 测试
 */
public class ZKTest implements Watcher {

	// 连接成功信号量
	static CountDownLatch CONNECTED_SEMAPHORE = new CountDownLatch(1);
	// zk连接
	static ZooKeeper zk = null;
	static Stat stat = new Stat();

	final static String ZK_LIST = "192.168.1.10:2181,192.168.1.11:2181,192.168.1.12:2181";
	final static int ZK_TIMEOUT = 5000;

	/**
	 * ZooKeeper原生API测试
	 */
	public static void main(String[] args) throws Exception {
		ZKTest wathcer = new ZKTest();
		// 创建zk连接
		zk = new ZooKeeper(ZK_LIST, ZK_TIMEOUT, wathcer);
		System.out.println(zk.getState());

		CONNECTED_SEMAPHORE.await(); // 阻塞，等待zk事件监听器检测连接是否成功
		System.out.println("zk session established.");

		// 获取连接后的会话信息
		// long sessionId = zk.getSessionId();
		// byte[] sessionPwd = zk.getSessionPasswd();

		// 不同的连接方式
		// zk = new ZooKeeper(ZK_LIST, ZK_TIMEOUT, new ZKWatcher(), 1988L,
		// "bdceo".getBytes());
		// zk = new ZooKeeper(ZK_LIST, ZK_TIMEOUT, new ZKWatcher(), sessionId,
		// sessionPwd);

		// 同步方式创建节点
		// String path1 = zk.create("/zk-api", "tttttt".getBytes(),
		// Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		// System.out.println(path1);
		// 创建持久+顺序节点，自动在同名节点后加数字后缀区分
		// String path2 = zk.create("/zk-api", "tttttt".getBytes(),
		// Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
		// System.out.println(path2);

		// 异步方式创建节点，节点一存在，之会返回错误码
		// zk.create("/zk-api-ac", "".getBytes(), Ids.OPEN_ACL_UNSAFE,
		// CreateMode.PERSISTENT, new AStringCallBack(), "a ctx");
		// zk.create("/zk-api-ac", "".getBytes(), Ids.OPEN_ACL_UNSAFE,
		// CreateMode.PERSISTENT, new AStringCallBack(), "a ctx");
		// zk.create("/zk-api-ac", "".getBytes(), Ids.OPEN_ACL_UNSAFE,
		// CreateMode.PERSISTENT_SEQUENTIAL, new AStringCallBack(),
		// "a ctx");

		// 读取节点列表
		// String path = "/zk-api-get";
		// zk.create(path, "".getBytes(), Ids.OPEN_ACL_UNSAFE,
		// CreateMode.PERSISTENT);
		// zk.create(path + "/sub1", "".getBytes(), Ids.OPEN_ACL_UNSAFE,
		// CreateMode.EPHEMERAL);
		// // 读取子节点-同步
		// List<String> childrenList = zk.getChildren(path, true);
		// System.out.println(childrenList);
		// // 读取子节点-异步
		// zk.getChildren(path, true, new AChildren2Callback(), null);
		// // 通过事件变更，获取最新子节点
		// zk.create(path + "/sub2", "".getBytes(), Ids.OPEN_ACL_UNSAFE,
		// CreateMode.EPHEMERAL);

		// 读取数据-同步
		// String path = "/zk-api-getdata";
		// zk.create(path, "bdceo".getBytes(), Ids.OPEN_ACL_UNSAFE,
		// CreateMode.PERSISTENT);
		// // 读取数据-同步
		// System.out.println(new String(zk.getData(path, true, stat)));
		// System.out.println(String.format("%d, %d, %d", stat.getCzxid(),
		// stat.getMzxid(), stat.getVersion()));
		// // 读取数据-异步
		// zk.getData(path, true, new ADataCallBack(), null);
		// // 修改数据，通过时间变更获取
		// zk.setData(path, "19880517".getBytes(), -1);

		// 更新数据
		// String path = "/zk-api-setdata";
		// zk.create(path, "bdceo".getBytes(), Ids.OPEN_ACL_UNSAFE,
		// CreateMode.PERSISTENT);
		// zk.getData(path, true, null);
		// // 更新数据-同步
		// Stat stat1 = zk.setData(path, "bdcfo".getBytes(), -1);
		// System.out.println(String.format("%d, %d, %d", stat1.getCzxid(),
		// stat1.getMzxid(), stat1.getVersion()));
		// // 更新数据-同步
		// Stat stat2 = zk.setData(path, "bdcto".getBytes(),
		// stat1.getVersion());
		// System.out.println(String.format("%d, %d, %d", stat2.getCzxid(),
		// stat2.getMzxid(), stat2.getVersion()));
		// try {
		// zk.setData(path, "bdcoo".getBytes(), stat1.getVersion());
		// } catch (KeeperException e) {
		// System.out.println(String.format("Error:%s, %s", e.code()
		// .toString(), e.getMessage()));
		// }
		// // 更新数据-异步
		// zk.setData(path, "bdcoo".getBytes(), -1, new AStatCallBack(), null);

		// 检测节点是否存在
		// String path = "/zk-exists";
		// zk.exists(path, true);
		// zk.create(path, "xxx".getBytes(), Ids.OPEN_ACL_UNSAFE,
		// CreateMode.PERSISTENT);
		// zk.setData(path, "bdceo".getBytes(), -1);
		// zk.create(path + "/sub", "".getBytes(), Ids.OPEN_ACL_UNSAFE,
		// CreateMode.PERSISTENT);
		// zk.delete(path + "/sub", -1);
		// zk.delete(path, -1);

		// 权限控制
		String path = "/zk-auth";
		String subPath = "/zk-auth/sub";
		ZooKeeper zk1 = new ZooKeeper(ZK_LIST, ZK_TIMEOUT, wathcer);
		zk1.addAuthInfo("digest", "key:bdceo".getBytes());
		zk1.create(path, "".getBytes(), Ids.CREATOR_ALL_ACL,
				CreateMode.PERSISTENT);
		zk1.create(subPath, "".getBytes(), Ids.CREATOR_ALL_ACL,
				CreateMode.EPHEMERAL);
		// 无权限查看
		// try {
		// ZooKeeper zk2 = new ZooKeeper(ZK_LIST, ZK_TIMEOUT, wathcer);
		// zk2.getData(path, false, null);
		// } catch (Exception e) {
		// System.out.println(e.getMessage());
		// }
		// // 正确权限查看
		// ZooKeeper zk3 = new ZooKeeper(ZK_LIST, ZK_TIMEOUT, wathcer);
		// zk3.addAuthInfo("digest", "key:bdceo".getBytes());
		// zk3.getData(path, false, null);
		// // 错误权限查看
		// try {
		// ZooKeeper zk4 = new ZooKeeper(ZK_LIST, ZK_TIMEOUT, wathcer);
		// zk4.addAuthInfo("digest", "key:bdcfo".getBytes());
		// zk4.getData(path, false, null);
		// } catch (Exception e) {
		// System.out.println(e.getMessage());
		// }
		// 测试删除子节点：必须携带权限信息
		try {
			ZooKeeper zk5 = new ZooKeeper(ZK_LIST, ZK_TIMEOUT, wathcer);
			zk5.delete(subPath, -1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		ZooKeeper zk6 = new ZooKeeper(ZK_LIST, ZK_TIMEOUT, wathcer);
		zk6.addAuthInfo("digest", "key:bdceo".getBytes());
		zk6.delete(subPath, -1);
		// 删除父节点-不需要权限信息
		ZooKeeper zk7 = new ZooKeeper(ZK_LIST, ZK_TIMEOUT, wathcer);
		zk7.delete(path, -1);

		System.out.println("zk-auth test finish!");
		Thread.sleep(Integer.MAX_VALUE);
	}

	/**
	 * 状态变更通知
	 */
	@Override
	public void process(WatchedEvent event) {
		System.out.println("Receive watched event: " + event);
		try {
			// 同步连接成功
			if (KeeperState.SyncConnected == event.getState()) {
				// 未知事件类型，未知节点，说明连接创建成功
				if (EventType.None == event.getType()
						&& null == event.getPath()) {
					ZKTest.CONNECTED_SEMAPHORE.countDown();
				}
				// 节点创建
				else if (event.getType() == EventType.NodeCreated) {
					System.out.println("节点创建通知-start");
					System.out.println(String.format("Node (%s) Created",
							event.getPath()));
					System.out.println("节点创建通知-end");
					zk.exists(event.getPath(), true);
				}
				// 节点删除
				else if (event.getType() == EventType.NodeDeleted) {
					System.out.println("节点删除通知-start");
					System.out.println(String.format("Node (%s) Deleted",
							event.getPath()));
					System.out.println("节点删除通知-end");
					zk.exists(event.getPath(), true);
				}
				// 节点子目录变更
				else if (event.getType() == EventType.NodeChildrenChanged) {
					System.out.println("节点子目录变更通知-start");
					System.out.println(String.format("ReGet Child : %s",
							zk.getChildren(event.getPath(), true)));
					System.out.println("节点子目录变更通知-end");
				}
				// 节点数据变更
				else if (event.getType() == EventType.NodeDataChanged) {
					System.out.println("节点数据变更通知-start");
					System.out.println(new String(zk.getData(event.getPath(),
							true, stat)));
					System.out
							.println(String.format("%d, %d, %d",
									stat.getCzxid(), stat.getMzxid(),
									stat.getVersion()));
					System.out.println("节点数据变更通知-end");
					zk.exists(event.getPath(), true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

/**
 * 异步更新数据
 */
class AStatCallBack implements AsyncCallback.StatCallback {

	@Override
	public void processResult(int rc, String path, Object ctx, Stat stat) {
		System.out.println("异步更新数据内容-start");
		if (rc == 0) {
			System.out.println("set data SUCCESS");
		}
		System.out.println("异步更新数据内容-end");
	}

}

/**
 * 异步获取数据内容
 */
class ADataCallBack implements AsyncCallback.DataCallback {

	@Override
	public void processResult(int rc, String path, Object ctx, byte[] data,
			Stat stat) {
		System.out.println("异步获取数据内容-start");
		System.out.println(String.format("%s, %s, %s", rc, path, new String(
				data)));
		System.out.println(String.format("%d, %d, %d", stat.getCzxid(),
				stat.getMzxid(), stat.getVersion()));
		System.out.println("异步获取数据内容-end");
	}

}

/**
 * 异步读取子节点
 */
class AChildren2Callback implements AsyncCallback.Children2Callback {

	@Override
	public void processResult(int rc, String path, Object ctx,
			List<String> children, Stat stat) {
		System.out.println("异步获取子节点列表-start");
		System.out
				.println(String
						.format("Get Children znode result: [response code: %d, param path: %s, ctx: %s, children list: %s, stat: %s",
								rc, path, ctx, children, stat));
		System.out.println("异步获取子节点列表-end");
	}

}

/**
 * 异步创建目录回调
 */
class AStringCallBack implements AsyncCallback.StringCallback {

	@Override
	public void processResult(int rc, String path, Object ctx, String name) {
		System.out.println("异步创建节点-start");
		System.out.println(String.format(
				"Create path result:[%d, %s, %s, real path name: %s]", rc,
				path, ctx, name));
		System.out.println("异步创建节点-start");
	}

}
