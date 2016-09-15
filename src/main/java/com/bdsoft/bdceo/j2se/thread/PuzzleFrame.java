package com.bdsoft.bdceo.j2se.thread;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

import com.bdsoft.bdceo.j2se.thread.concurrent.Node;

public class PuzzleFrame {

}

// 谜题框架，搬箱子之类的谜题
class P {// 位置
}

class M {// 移动
}

// 谜题抽象类
interface Puzzle<P, M> {
	P initialPosition();// 初始化一个位置

	boolean isGoal(P pos);// 是否是目标位置

	Set<M> legalMoves(P pos);// 当前位置所有可行的移动方案

	P move(P position, M move);// 移动到指定位置
}

// 串行的谜题解答器
class SequentialPuzleSolver<P, M> {

	private final Puzzle<P, M> puzzle;
	private final Set<P> seen = new HashSet<P>();// 已经走过的位置

	public SequentialPuzleSolver(Puzzle<P, M> pu) {
		this.puzzle = pu;
	}

	public List<M> solve() {
		P pos = puzzle.initialPosition();
		return search(new Node<P, M>(pos, null, null));
	}

	private List<M> search(Node<P, M> node) {
		P pos = node.pos;
		if (!seen.contains(pos)) {
			seen.add(pos);
			if (puzzle.isGoal(pos)) {
				return node.asMoveList();
			}
			for (M move : puzzle.legalMoves(pos)) {
				P newPos = puzzle.move(pos, move);
				Node<P, M> child = new Node<P, M>(newPos, move, node);
				List<M> result = search(child);
				if (result != null) {
					return result;
				}
			}
		}
		return null;
	}
}

// 闭锁
class ValueLatch<T> {
	private T value = null;
	private final CountDownLatch done = new CountDownLatch(1);

	public boolean isSet() {
		return (done.getCount() == 0);
	}

	public synchronized void setValue(T newValue) {
		if (!isSet()) {
			value = newValue;
			done.countDown();
		}
	}

	public T getValue() throws InterruptedException {
		done.await();// 线程阻塞，等待调用countdown激活
		synchronized (this) {
			return value;
		}
	}

}

// 并发的谜题解答器
class ConcurrentPuzzleSolver<P, M> {

	private final Puzzle<P, M> puzzle;
	private final ExecutorService exec;
	private final ConcurrentMap<P, Boolean> seen;
	final ValueLatch<Node<P, M>> solution = new ValueLatch<Node<P, M>>();

	public ConcurrentPuzzleSolver() {
		this.puzzle = null;
		this.seen = null;
		this.exec = null;
	}

	public List<M> solve() throws InterruptedException {
		try {
			P p = puzzle.initialPosition();
			exec.execute(newTask(p, null, null));
			// getValue，阻塞直到找到解答
			Node<P, M> solnNode = solution.getValue();
			return (solnNode == null) ? null : solnNode.asMoveList();
		} finally {
			exec.shutdown();
		}
	}

	protected Runnable newTask(P p, M m, Node<P, M> n) {
		return new SolverTask(p, m, n);
	}

	// 即时节点也是线程
	class SolverTask extends Node<P, M> implements Runnable {

		public SolverTask(P p, M m, Node<P, M> n) {
			super(p, m, n);
		}

		public void run() {
			if (solution.isSet() || seen.putIfAbsent(pos, true) != null) {
				return;// 已经找过这个位置或找到了解答
			}
			if (puzzle.isGoal(pos)) {
				solution.setValue(this);
			} else {
				for (M m : puzzle.legalMoves(pos)) {
					P newPos = puzzle.move(pos, m);
					exec.execute(newTask(newPos, m, this));
				}
			}
		}
	}
}

// 处理在解决器中找不到答案
class PuzzleSolver<P, M> extends ConcurrentPuzzleSolver<P, M> {

	private final AtomicInteger taskCount = new AtomicInteger(0);

	protected Runnable newTask(P p, M m, Node<P, M> n) {
		return new CountingSolverTask(p, m, n);
	}

	class CountingSolverTask extends SolverTask {
		CountingSolverTask(P p, M m, Node<P, M> n) {
			super(p, m, n);
			taskCount.incrementAndGet();
		}

		public void run() {
			try {
				super.run();
			} finally {
				if (taskCount.decrementAndGet() == 0) {
					solution.setValue(null);
				}
			}
		}
	}
}
