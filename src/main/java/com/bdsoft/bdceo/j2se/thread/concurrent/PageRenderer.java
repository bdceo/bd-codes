package com.bdsoft.bdceo.j2se.thread.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.apache.ibatis.executor.ExecutorException;

public class PageRenderer {

	private final ExecutorService executor;

	public PageRenderer(ExecutorService executorService) {
		this.executor = executorService;
	}

	void renderPage(String url) {
		List<Object> info = scanForImageInfo(url);

		// 并行下载图片
		CompletionService<Object> completionService = new ExecutorCompletionService<Object>(
				executor);
		for (final Object o : info) {
			completionService.submit(new Callable<Object>() {
				public Object call() {
					// 处理图片下载
					return o.getClass();
				}
			});
		}

		// 准备渲染
		renderText(url);

		try {
			for (int i = 0; i < info.size(); i++) {
				Future<Object> f = completionService.take();
				Object img = f.get();
				renderImage(img);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} catch (ExecutorException e) {
			e.getCause();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void renderImage(Object img) {

	}

	private void renderText(String url) {

	}

	List<Object> scanForImageInfo(String url) {
		return new ArrayList<Object>();
	}

}
