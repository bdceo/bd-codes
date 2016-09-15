package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogWritter {

	private final BlockingQueue<String> queue;
	private final LoggerThread logger;

	private boolean isShutdown;
	private int reservations;

	public LogWritter(PrintWriter writer) {
		this.queue = new LinkedBlockingQueue<String>(10);
		this.logger = new LoggerThread(writer);
		this.isShutdown = false;
		this.reservations = 0;
	}

	public void start() {
		this.logger.start();
	}

	public void stop() {
		synchronized (this) {
			this.isShutdown = true;
		}
		logger.interrupt();
	}

	public void log(String msg) throws InterruptedException {
		synchronized (this) {
			if (isShutdown) {
				throw new IllegalStateException();
			}
			++reservations;
		}
		queue.put(msg);
	}

	private class LoggerThread extends Thread {

		private final PrintWriter writer;

		public LoggerThread(PrintWriter w) {
			this.writer = w;
		}

		public void run() {
			try {
				while (true) {
					synchronized (LogWritter.this) {
						if (isShutdown && reservations == 0) {
							break;
						}
					}
					String msg = queue.take();
					synchronized (LogWritter.this) {
						--reservations;
					}
					writer.println(msg);
				}
			} catch (Exception e) {
			} finally {
				writer.close();
			}
		}
	}

}
