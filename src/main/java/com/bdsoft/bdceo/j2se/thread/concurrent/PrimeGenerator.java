package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.io.InputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PrimeGenerator implements Runnable {

	private final List<BigInteger> primes = new ArrayList<BigInteger>();
	// 线程中止标志，volatile
	private volatile boolean cancelled;

	public void run() {
		BigInteger p = BigInteger.ONE;
		// 运行期不停判断
		while (!cancelled) {
			p = p.nextProbablePrime();
			synchronized (this) {
				primes.add(p);
			}
		}
	}

	public void cancel() {
		cancelled = true;
	}

	public synchronized List<BigInteger> get() {
		return new ArrayList<BigInteger>(primes);
	}

	// 中断使用示例
	public List<BigInteger> aSecondOfPrimes() throws InterruptedException {
		PrimeGenerator pg = new PrimeGenerator();
		new Thread(pg).start();
		try {
			Thread.sleep(1000);
		} finally {
			pg.cancel();
		}
		return pg.get();
	}

	private static ExecutorService pool = Executors.newFixedThreadPool(2);

	// 通过Future取消任务
	public static void timedRun(Runnable r, long timeout, TimeUnit unit)
			throws InterruptedException, ExecutionException {
		Future<?> task = pool.submit(r);
		try {
			task.get(timeout, unit);
		} catch (TimeoutException e) {
			// 任务执行超时，将被取消
		} catch (ExecutionException e) {
			// 任务执行中抛出异常，向上抛
			throw new ExecutionException(e.getCause());
		} finally {
			// 如果任务结束，正常取消
			// 如果任务在运行，将被中断
			task.cancel(true);
		}
	}
}

class PrimeProducer extends Thread {

	private final BlockingQueue<BigInteger> queue;

	public PrimeProducer(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}

	public void run() {
		BigInteger b = BigInteger.ONE;
		try {
			while (!Thread.currentThread().isInterrupted()) {
				b = b.nextProbablePrime();
				queue.put(b);
			}
		} catch (InterruptedException e) {
			// 执行线程退出处理
		}
	}

	public void cancel() {
		// 通过thread类的方法发出中断请求
		this.interrupt();
	}

}

// 处理不可中断的阻塞
class ReaderThread extends Thread {
	private final Socket socket;
	private final InputStream in;

	public ReaderThread(Socket s, InputStream i) {
		socket = s;
		in = i;
	}

	// 重写父类的interrupt方法，实现资源释放
	public void interrupt() {
		try {
			socket.close();
		} catch (Exception e) {
		} finally {
			super.interrupt();
		}

	}

	public void run() {
		byte[] buf = new byte[1024];
		try {
			while (true) {
				int count = in.read(buf);
				if (count < 0) {
					break;
				} else if (count > 0) {
					// 处理socket数据
				}
			}
		} catch (Exception e) {
			// 处理允许线程退出
		}
	}

}

// 非标准的取消
interface CancellableTask<T> extends Callable<T> {
	void cancel();

	RunnableFuture<T> newTask();
}

class CancellingExecutor extends ThreadPoolExecutor {

	public CancellingExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
		if (callable instanceof CancellableTask) {
			return ((CancellableTask<T>) callable).newTask();
		} else {
			return super.newTaskFor(callable);
		}
	}
}

abstract class SocketUsingTask<T> implements CancellableTask<T> {
	private Socket socket;

	protected synchronized void setSocket(Socket s) {
		socket = s;
	}

	public synchronized void cancel() {
		try {
			if (socket != null) {
				socket.close();
			}
		} catch (Exception e) {
		}
	}

	public RunnableFuture<T> newTask() {
		return new FutureTask<T>(this) {
			public boolean cancel(boolean mayInterruptIfRunning) {
				try {
					SocketUsingTask.this.cancel();
				} finally {
					return super.cancel(mayInterruptIfRunning);
				}
			}
		};
	}
}
