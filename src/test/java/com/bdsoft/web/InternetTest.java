package com.bdsoft.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

public class InternetTest {

	// 从网络获取图片
	@Test
	public void getImg() throws Exception {
		String urlPath = "http://ww4.sinaimg.cn/bmiddle/621a3ea7jw1di8oi0tovgj.jpg";
		URL url = new URL(urlPath);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(6 * 1000);
		System.out.println("请求结果：" + conn.getResponseCode());
		if (conn.getResponseCode() == 200) {
			InputStream inStream = conn.getInputStream();
			byte[] data = readStream(inStream);
			File file = new File("d:/download/bd_test.jpg");
			FileOutputStream outStream = new FileOutputStream(file);
			outStream.write(data);
			outStream.close();
		}
	}

	// 从网络获取网页
	@Test
	public void getHtml() throws Exception {
		String urlPath = "http://www.sina.com.cn";
		URL url = new URL(urlPath);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(6 * 1000);
		System.out.println("请求结果：" + conn.getResponseCode());
		if (conn.getResponseCode() == 200) {
			InputStream inStream = conn.getInputStream();
			byte[] data = readStream(inStream);
			System.out.println(new String(data));
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
