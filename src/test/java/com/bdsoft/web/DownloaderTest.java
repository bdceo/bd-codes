package com.bdsoft.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

public class DownloaderTest {

	@Test
	public void download() throws Exception {
		String urlPath = "http://music.baidu.com/data/music/file?link=http://yinyueshiting.baidu.com/data2/music/121099012/121078799255600128.mp3?xcode=33e5373ba09bc219b89e9d84f827b20874d88bfa901636a3&song_id=121078799";
		URL url = new URL(urlPath);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		int threadCount = 3;
		int fileSize = conn.getContentLength();// 获取文件大小
		int block = fileSize / 3 + 1;// 每条线程下载的文件大小
		conn.disconnect();

		File file = new File("d:/download/bd_test.mp3");
		RandomAccessFile ranFile = new RandomAccessFile(file, "rw");
		ranFile.setLength(fileSize);
		ranFile.close();
		for (int i = 0; i < threadCount; i++) {
			int startPos = i * block;
			RandomAccessFile threadFile = new RandomAccessFile(file, "rw");
			threadFile.seek(startPos);// 从文件的什么地方开始写入
			(new DownloadThread(i + 1, url, startPos, threadFile, block)).start();
		}
		
//		byte[] quit = new byte[1];
//		System.in.read(quit);
//		while (quit[0] != 'q') {
//			Thread.sleep(2 * 1000);
//		}
	}

	private class DownloadThread extends Thread {
		private int threadId;
		private URL url;
		private int startPos;
		private RandomAccessFile threadFile;
		private int block;

		public DownloadThread(int _tid, URL _url, int _startPos,
				RandomAccessFile _threadFile, int _block) {
			this.threadId = _tid;
			this.url = _url;
			this.startPos = _startPos;
			this.threadFile = _threadFile;
			this.block = _block;
		}

		public void run() {
			try {
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setRequestProperty("Range", "bytes=" + startPos + "-");
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(6 * 1000);
				InputStream inStream = conn.getInputStream();
				byte[] buffer = new byte[1024];
				int len = -1;
				int readTotal = 0;
				while ((readTotal < block)
						&& ((len = inStream.read(buffer)) != -1)) {
					readTotal += len;// 累计下载的文件大小
					threadFile.write(buffer, 0, len);
				}
				threadFile.close();
				conn.disconnect();
				System.out.println("线程  " + threadId + "  下载完成！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public byte[] readStream(InputStream ins) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = ins.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		outStream.close();
		ins.close();
		return outStream.toByteArray();
	}
}
