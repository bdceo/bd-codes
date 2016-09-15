package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.util.LinkedList;
import java.util.List;

//谜题解决框架的链表节点
public class Node<P, M> {

	public final P pos;
	public final M move;
	public final Node<P, M> prev;// 上一个位置节点

	public Node(P pos, M move, Node<P, M> prev) {
		this.pos = pos;
		this.move = move;
		this.prev = prev;
	}

	public List<M> asMoveList() {
		List<M> solutions = new LinkedList<M>();
		for (Node<P, M> n = this; n.move != null; n = n.prev) {
			solutions.add(0, n.move);
		}
		return solutions;
	}
}
