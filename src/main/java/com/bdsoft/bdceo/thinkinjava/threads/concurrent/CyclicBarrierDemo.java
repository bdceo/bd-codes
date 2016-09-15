package com.bdsoft.bdceo.thinkinjava.threads.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 测试：CyclicBarrier，可循环记数的障碍器
 * 
 * 模拟跑马比赛
 */
public class CyclicBarrierDemo {

	/**
	 * 入口
	 */
	public static void main(String[] args) {
		new HorseRace(10, 200);
	}

}

// 马赛
class HorseRace {
	// 赛道长度
	static final int FINISH_LISH = 100;
	// 参赛马匹
	private List<Horse> horses = new ArrayList<Horse>();
	private ExecutorService es = Executors.newCachedThreadPool();
	// 障碍器，每秒可监控其进度
	private CyclicBarrier cb;

	public HorseRace(int nHorses, final int pause) {
		cb = new CyclicBarrier(nHorses, new Runnable() {
			public void run() {
				// 打印跑道长度
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < FINISH_LISH; i++) {
					sb.append("=");
				}
				System.out.println(sb.toString());

				// 打印每匹马的进度
				for (Horse h : horses) {
					System.out.println(h.tracks());
				}

				// 计算那匹马先到终点
				for (Horse h : horses) {
					if (h.getStrides() >= FINISH_LISH) {
						System.out.println(h + " won!");
						es.shutdownNow();
						return;
					}
				}
				try { // 休眠检测
					TimeUnit.MILLISECONDS.sleep(pause);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// 初始化马匹
		for (int i = 0; i < nHorses; i++) {
			Horse h = new Horse(cb);
			horses.add(h);
			es.execute(h);
		}
	}

}

// 比赛专用马
class Horse implements Runnable {

	private CyclicBarrier cb;
	// 马匹编号
	private static int counter = 0;
	private final int id = ++counter;
	// 每次随机进度
	private static Random rd = new Random(System.currentTimeMillis());
	private int strides = 0;

	public Horse(CyclicBarrier c) {
		cb = c;
	}

	public synchronized int getStrides() {
		return strides;
	}

	public String toString() {
		return String.format("Horse %d ", id);
	}

	// 进度
	public String tracks() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < getStrides(); i++) {
			sb.append("*");
		}
		sb.append(id);
		return sb.toString();
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				// 随机跑
				synchronized (this) {
					strides += rd.nextInt(3);
				}
				cb.await();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
