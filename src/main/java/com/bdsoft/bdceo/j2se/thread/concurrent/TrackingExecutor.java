package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// shutdown的局限性
public class TrackingExecutor extends AbstractExecutorService {

	private final ExecutorService exec;
	private final Set<Runnable> tasksCancelledAtShutdown = Collections
			.synchronizedSet(new HashSet<Runnable>());

	public TrackingExecutor(ExecutorService e) {
		this.exec = e;
	}

	public List<Runnable> getCancelledTasks() {
		if (!exec.isTerminated()) {
			throw new IllegalStateException();
		}
		return new ArrayList<Runnable>(tasksCancelledAtShutdown);
	}

	public void execute(final Runnable runnable) {
		exec.execute(new Runnable() {
			public void run() {
				try {
					runnable.run();
				} finally {
					if (isShutdown() && Thread.currentThread().isInterrupted()) {
						tasksCancelledAtShutdown.add(runnable);
					}
				}
			}
		});
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit)
			throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isShutdown() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTerminated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Runnable> shutdownNow() {
		// TODO Auto-generated method stub
		return null;
	}

}

abstract class WebCrawler {

	private volatile TrackingExecutor exec;
	private final Set<URL> urlsToCrawl = new HashSet<URL>();

	public synchronized void start() {
		exec = new TrackingExecutor(Executors.newCachedThreadPool());
		for (URL url : urlsToCrawl) {
			submitCrawlTask(url);
		}
		urlsToCrawl.clear();
	}

	// 关闭时获取受影响的爬虫线程
	public synchronized void stop() throws InterruptedException {
		try {
			saveUncrawled(exec.shutdownNow());
			if (exec.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
				saveUncrawled(exec.getCancelledTasks());
			}
		} finally {
			exec = null;
		}
	}

	// 保存受影响的线程
	private void saveUncrawled(List<Runnable> uncrawled) {
		for (Runnable task : uncrawled) {
			urlsToCrawl.add(((CrawlTask) task).getPage());
		}
	}

	// 启动页面爬虫线程
	private void submitCrawlTask(URL u) {
		exec.execute(new CrawlTask(u));
	}

	// 处理页面所有url
	protected abstract List<URL> processPage(URL url);

	// 爬虫
	private class CrawlTask implements Runnable {
		private final URL url;

		public CrawlTask(URL u) {
			this.url = u;
		}

		public void run() {
			// 循环处理页面，不断添加新的url，启动新的任务
			for (URL link : processPage(url)) {
				if (Thread.currentThread().isInterrupted()) {
					return;
				}
				submitCrawlTask(link);
			}
		}

		public URL getPage() {
			return url;
		}
	}

}
