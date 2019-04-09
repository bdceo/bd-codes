/**
 * SingleDirList.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.datastruct.list;

/**
 * 单向链表：
 * 
 * 添加操作：递归添加
 * 
 * 删除操作：只需改变当前节点引用即可，少了数组的移位操作
 * 
 * @author bdceo
 * @date 2016-8-21 上午6:28:14
 * @version V1.0
 */
public class SingleDirList {

	/**
	 * 入口
	 */
	public static void main(String[] args) {

		NodeManager nm = new NodeManager();
		nm.addNode("石油在线");
		nm.addNode("良机在线");
		nm.addNode("太格时代");
		nm.addNode("第一视频");
		nm.addNode("中彩汇");
		nm.addNode("中网银科");
		nm.addNode("无微不至");
		nm.addNode("晓迈科技");
		nm.addNode("微课网");
		nm.addNode("律典信息");
		nm.addNode("居然设计家");
		nm.addNode("花生好车");
		System.out.println(nm);
		
		nm.delNode("石油在线");
		nm.delNode("太格时代");
		nm.delNode("第一视频");
		nm.delNode("中网银科");
		nm.delNode("无微不至");
		nm.delNode("晓迈科技");
		nm.delNode("律典信息");
		System.out.println(nm);
	}

}

/**
 * 节点管理器-模拟单向链表
 * 
 * @author bdceo
 * @date 2016-8-21 上午6:57:16
 */
class NodeManager {

	private Node root; // 根节点

	/**
	 * 添加节点
	 * 
	 * @param data 数据
	 */
	public void addNode(String data) {
		if (root == null) {
			root = new Node(data);
		} else {
			root.add(data);
		}
	}

	/**
	 * 删除节点
	 * 
	 * @param data 数据
	 */
	public void delNode(String data) {
		if (root != null) {
			if (root.getData().equals(data)) {
				root = root.getNext();
			} else {
				root.del(data);
			}
		}
	}

	public String toString() {
		if (root != null) {
			return String.format("%s --> %s", root.getData(), root);
		} else {
			return "空链表";
		}
	}
}

/**
 * 节点对象
 * 
 * @author bdceo
 * @date 2016-8-21 上午6:56:50
 */
class Node {

	private String data; // 节点数据
	private Node next;// 单向链表，只维护下一个节点的引用

	public Node(String data) {
		this.data = data;
	}

	// 添加下级节点【递归】
	public void add(String data) {
		if (next == null) {
			next = new Node(data);
		} else {
			next.add(data);
		}
	}

	// 删除下级节点【递归】
	public void del(String data) {
		if (next != null) {
			if (next.getData().equals(data)) {
				next = next.getNext();
			} else {
				next.del(data);
			}
		}
	}

	public String toString() {
		if (next != null) {
			return String.format("%s --> %s", next.getData(), next);
		} else {
			return "结束";
		}
	}

	public String getData() {
		return data;
	}

	public Node getNext() {
		return next;
	}

}