package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class QueueTest {

	// 遍历队列
	public static void forQueue(Queue queue) {
		System.out.println("\nqueue elements: ");
		Object o = queue.poll();
		while (o != null) {
			System.out.print("  get-" + o);
			o = queue.poll();
		}
		System.out.println();
	};

	/**
	 * 普通队列和阻塞队列测试
	 */
	public static void main(String[] args) {
		// 普通队列--LinkedList：基本队列，先进先出
		System.out.println("LinkedList：基本队列，先进先出");
		Queue<Integer> q1 = new LinkedList<Integer>();
		for (int i = 0; i < 10; i++) {
			System.out.print("  put-" + i);
			q1.offer(new Integer(i));
		}
		forQueue(q1);

		// PriorityQueue：按照自然元素顺序，可以指定比较器
		System.out.println("PriorityQueue：按照自然元素顺序");
		SortedSet set = new TreeSet();
		for (int i = 0; i < 10; i++) {
			set.add(new Qele((int) Math.floor(Math.random() * 100), "ele=" + i));
		}
		Queue q2 = new PriorityQueue(set);
		forQueue(q2);

		System.out.println("PriorityQueue：指定比较器");
		Queue q3 = new PriorityQueue(10, new QComp());
		for (int i = 0; i < 10; i++) {
			q3.offer(new Qele((int) Math.floor(Math.random() * 100), "ele=" + i));
		}
		forQueue(q3);

		/** ------------------以下，阻塞队列测试-------------------- */

		// 阻塞队列--BlockingQueue：ArrayBlockingQueue,LinkedBlockingQueue,PriorityBlockingQueue
		System.out.println("BlockingQueue");
		BlockingQueue q4 = new ArrayBlockingQueue(10);
		try {
			for (int i = 0; i < 10; i++) {
				System.out.print("put" + i + "  ");
				q4.put("bele=" + i);
			}
			for (int i = 10; i < 20; i++) {
				q4.take();
				System.out.print("put" + i + "  ");
				q4.put("bele=" + i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// BlockingDeque:阻塞栈，LinkedBlockingDeque
		System.out.println("\nBlockingDeque:阻塞栈，LinkedBlockingDeque");
		BlockingDeque q5 = new LinkedBlockingDeque(5);
		try {
			for (int i = 0; i < 5; i++) {
				System.out.print("put" + i + "  ");
				q5.putFirst(new Integer(i));
			}
			for (int i = 0; i < 5; i++) {
				q5.takeFirst();
				System.out.print("put" + i + "  ");
				q5.put(new Integer(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

// 队列
class Bele {
	private String name;

	private Bele(String n) {
		this.name = n;
	}
}

class Qele implements Comparable {

	private int priority;
	private String content;

	public Qele(int p, String c) {
		this.priority = p;
		this.content = c;
	}

	// 默认比较规则
	public int compareTo(Object o) {
		return this.priority - ((Qele) o).priority;
	}

	public String toString() {
		return "[priority=" + this.priority + ",content=" + content + "]";
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}

// 自定义比较器
class QComp implements Comparator {
	public int compare(Object a, Object b) {
		return ((Qele) a).getPriority() - ((Qele) b).getPriority();
	}
}
