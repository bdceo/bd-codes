package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.jsoup.nodes.Element;

public class DiguiTest {

	// 串行处理
	void processSequentially(List<Element> eles) {
		for (Element e : eles) {
			// process(e);
		}
	}

	// 并行处理：要求循环内的任务独立
	void processInParallel(List<Element> eles, Executor exec) {
		for (final Element e : eles) {
			exec.execute(new Runnable() {
				public void run() {
					// process(e);
				}
			});
		}
	}

	class Node<T> {
		public Node<T> coumpte() {
			return new Node<T>();
		}

		public List<Node<T>> getChildren() {
			return null;
		}
	}

	// 串行执行，递归
	public <T> void sequentialRecursive(List<Node<T>> nodes,
			Collection<T> results) {
		for (final Node<T> n : nodes) {
			results.add((T) n.coumpte());
			sequentialRecursive(n.getChildren(), results);
		}
	}

	// 并行执行，递归
	public <T> void parallelRecusive(final Executor exec, List<Node<T>> nodes,
			final Collection<T> results) {
		for (final Node<T> n : nodes) {
			exec.execute(new Runnable() {
				public void run() {
					results.add((T) n.coumpte());
				}
			});
			parallelRecusive(exec, n.getChildren(), results);
		}
	}

	// 测试嗲用
	public <T> Collection<T> getParallelResults(List<Node<T>> nodes)
			throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		// 并发队列
		Queue<T> resultQueue = new ConcurrentLinkedQueue<T>();
		parallelRecusive(exec, nodes, resultQueue);
		// 阻塞获取返回结果
		exec.shutdown();
		exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
		return resultQueue;
	}

}
