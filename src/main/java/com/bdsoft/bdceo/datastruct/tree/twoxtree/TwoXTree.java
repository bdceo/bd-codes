package com.bdsoft.bdceo.datastruct.tree.twoxtree;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 二叉树
 * 
 * @author 丁辰叶
 * @date 2016-8-15
 * @version 1.0.0
 */ 
public class TwoXTree {

	/**
	 * 测试入口
	 */
	public static void main(String[] args) {
		Random rd = new Random(System.currentTimeMillis());

		// 测试：二叉树-v1版本
		int nodeSize = 19;
		TwoXTreeV1 treeV1 = new TwoXTreeV1();
		treeV1.addNode(28);
		for (int i = 0; i < nodeSize; i++) {
			treeV1.addNode(rd.nextInt(100));
		}
		System.out.println(treeV1);
	}

}

/**
 * 二叉树-v1版本
 */
class TwoXTreeV1 {

	private Set<Integer> dataSet = new HashSet<Integer>();

	private Node root; // 根节点

	// 添加树节点
	public void addNode(int data) {
		if (!dataSet.add(data)) {
			System.out.print(String.format("-dup#%d，", data));
			return;
		}
		if (root == null) {
			root = new Node(data);
		} else {
			root.addNode(data);
		}
		System.out.print(String.format("+new#%d，", data));
	}

	// 输出树
	public String toString() {
		System.out.println("\n树结构：");
		return root.toString();
	}

	/**
	 * 子树节点
	 */
	class Node {

		private int data; // 节点数据
		private Node left; // 左子树
		private Node right; // 右子树

		public Node(int data) {
			this.data = data;
		}

		// 输出节点数据
		public String toString() {
			StringBuilder sb = new StringBuilder();
			if (left != null) {
				sb.append(left.toString());
			}
			sb.append(data).append(" ");
			if (right != null) {
				sb.append(right.toString());
			}
			return sb.toString();
		}

		// 添加树节点
		public void addNode(int newData) {
			if (data > newData) {
				addLeft(newData);
			} else {
				addRight(newData);
			}
		}

		// 放到左子树
		private void addLeft(int data) {
			if (left == null) {
				left = new Node(data);
			} else {
				left.addNode(data);
			}
		}

		// 放到右子树
		private void addRight(int data) {
			if (right == null) {
				right = new Node(data);
			} else {
				right.addNode(data);
			}
		}
	}

}
