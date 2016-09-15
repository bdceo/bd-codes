/**
 * LinkedStack.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.generics;

/**
 * 泛型：模拟实现链式存储机制
 * 
 * @author bdceo
 * @date 2016-8-17 上午8:42:16
 * @version V1.0
 */
public class LinkedStack<T> {

	private Node<T> top = new Node<T>();

	public void push(T data) {
		Node<T> newTop = new Node<T>(data, top);
		top = newTop;
	}

	public T pop() {
		T data = top.data;
		if (!top.end()) {
			top = top.next;
		}
		return data;
	}

	/**
	 * 入口
	 */
	public static void main(String[] args) {
		LinkedStack<String> ls = new LinkedStack<String>();
		for (String str : "bdceo,jdi,laoding".split(",")) {
			ls.push(str);
		}
		String str = null;
		while ((str = ls.pop()) != null) {
			System.out.println(str);
		}
	}

	static class Node<T> {
		T data;
		Node<T> next;

		public Node() {
			this(null, null);
		}

		/**
		 * 构建链式，每添加一个元素都追加到头部
		 */
		public Node(T data, Node<T> next) {
			this.data = data;
			this.next = next;
		}

		public boolean end() {
			return (data == null && next == null);
		}
	}

}
