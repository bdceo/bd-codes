package com.bdsoft.bdceo.thinkinjava.threads.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 测试：延迟队列
 */
public class DelayQueueDemo {

	/**
	 * 入口
	 */
	public static void main(String[] args) {
		Random rd = new Random(System.currentTimeMillis());
		ExecutorService es = Executors.newCachedThreadPool();

		// 初始化延迟队列
		DelayQueue<DelayedTask> queue = new DelayQueue<DelayedTask>();
		for (int i = 0; i < 20; i++) {
			queue.add(new DelayedTask(rd.nextInt(5000)));
		}
		queue.put(new EndSentinel(5000, es));

		es.execute(new DelayedTaskConsumer(queue));
	}

}

// 触发延迟队列的运行
class DelayedTaskConsumer implements Runnable {

	private DelayQueue<DelayedTask> q;

	public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
		this.q = q;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				q.take().run();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("delayedConsumer execute finish.");
	}
}

// 延迟任务
class DelayedTask implements Runnable, Delayed {

	private static int counter = 0;
	private final int id = counter++;
	private final int delta;
	private final long trigger;

	protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();

	public DelayedTask(int delayInMillsseconds) {
		delta = delayInMillsseconds;
		trigger = System.nanoTime()
				+ TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
		sequence.add(this);
	}

	// 生成队列任务顺序
	@Override
	public int compareTo(Delayed o) {
		DelayedTask that = (DelayedTask) o;
		if (trigger < that.trigger) {
			return -1;
		}
		if (trigger > that.trigger) {
			return 1;
		}
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	@Override
	public void run() {
		System.out.println("exec：" + this);
	}

	public String toString() {
		return String.format("[%1$-4d] Task#%d ", delta, id);
	}

	public String summary() {
		return String.format("(Task#%d:delay=%d)", id, delta);
	}
}

class EndSentinel extends DelayedTask {
	private ExecutorService es;

	public EndSentinel(int delay, ExecutorService es) {
		super(delay);
		this.es = es;
	}

	public void run() {
		for (DelayedTask dt : sequence) {
			System.out.println("info:" + dt.summary());
		}
		System.out.println();
		System.out.println(this + "-call shutdownNow");
		es.shutdownNow();
	}
}