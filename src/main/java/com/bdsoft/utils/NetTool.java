package com.bdsoft.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class NetTool {

	public static void main(String[] args) {

	}

	// 从网络获取图片
	public static byte[] getImg(String urlPath) throws Exception {
		URL url = new URL(urlPath);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setRequestMethod("GET");
		http.setConnectTimeout(5 * 1000);
		if (http.getResponseCode() == 200) {
			InputStream inStream = http.getInputStream();
			return readStream(inStream);
		} else {
			return null;
		}
	}

	public static String getPageContent(String url, DefaultHttpClient client)
			throws Exception {
		HttpGet get = new HttpGet(url);
		HttpResponse response = client.execute(get);
		return EntityUtils.toString(response.getEntity());
	}

	// 从网络获取指定内容
	public static String getTextContent(String urlPath, String encoding)
			throws Exception {
		URL url = new URL(urlPath);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5 * 1000);
		if (conn.getResponseCode() == 200) {
			InputStream inStream = conn.getInputStream();
			byte[] data = readStream(inStream);
			return new String(data, encoding);
		} else {
			throw new Exception("响应吗：" + conn.getResponseCode());
		}
	}

	// 从输入中获取网页内容
	public static String getContentFromStream(InputStream in, String encoding)
			throws Exception {
		byte[] data = readStream(in);
		return new String(data, encoding);
	}

	// 从网络获取指定内容返回输入流
	public static InputStream getContentStreamByGet(String urlPath,
			String encoding) throws Exception {
		URL url = new URL(urlPath);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5 * 1000);
		if (conn.getResponseCode() == 200) {
			return conn.getInputStream();
		}
		return null;
	}

	// 从网络获取指定内容返回输入流
	public static InputStream getContentStreamByPost(String urlPath,
			String encoding) throws Exception {
		String param = "";
		byte[] data = param.getBytes();
		URL url = new URL(urlPath);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setConnectTimeout(5 * 1000);
		http.setRequestMethod("POST");
		http.setDoOutput(true);// 发送POST请求必须设置允许输出
		http.setUseCaches(false);// 不适用cache
		http.setRequestProperty("Connection", "Keep-Alive");
		http.setRequestProperty("Charset", encoding);
		http.setRequestProperty("Content-Length", String.valueOf(data.length));
		http.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		DataOutputStream out = new DataOutputStream(http.getOutputStream());
		out.write(data);
		out.flush();

		if (http.getResponseCode() == 200) {
			return http.getInputStream();
		}
		out.close();
		return null;
	}

	// 发送post请求
	public static InputStream sendPostRequest(String urlPath,
			Map<String, String> params, String encoding) throws Exception {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sb.append(entry.getKey()).append("=")
					.append(URLEncoder.encode(entry.getValue(), encoding));
			sb.append("&");
		}
		sb.deleteCharAt(sb.length() - 1);
		byte[] data = sb.toString().getBytes();
		URL url = new URL(urlPath);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setConnectTimeout(5 * 1000);
		http.setRequestMethod("POST");
		http.setDoOutput(true);// 发送POST请求必须设置允许输出
		http.setUseCaches(false);// 不适用cache
		http.setRequestProperty("Connection", "Keep-Alive");
		http.setRequestProperty("Charset", encoding);
		http.setRequestProperty("Content-Length", String.valueOf(data.length));
		http.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		DataOutputStream out = new DataOutputStream(http.getOutputStream());
		out.write(data);
		out.flush();

		if (http.getResponseCode() == 200) {
			return http.getInputStream();
		}
		out.close();
		return null;
	}

	// 发送post请求
	public static InputStream sendPostAndEncode(String urlPath,
			Map<String, String> params, String encoding) throws Exception {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sb.append(entry.getKey()).append("=")
					.append(URLEncoder.encode(entry.getValue(), encoding));
			sb.append("&");
		}
		sb.deleteCharAt(sb.length() - 1);
		String ps = sb.toString();
		byte[] data = ps.getBytes();
		URL url = new URL(urlPath);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setConnectTimeout(5 * 1000);
		http.setRequestMethod("POST");
		http.setDoOutput(true);// 发送POST请求必须设置允许输出
		http.setUseCaches(false);// 不适用cache
		http.setRequestProperty("Connection", "Keep-Alive");
		http.setRequestProperty("Charset", encoding);
		http.setRequestProperty("Content-Length", String.valueOf(data.length));
		http.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		http.setRequestProperty("User-Agent",
				"Mozilla/5.0 (X11; Linux i686; rv:8.0) Gecko/20100101 Firefox/8.0");
		DataOutputStream out = new DataOutputStream(http.getOutputStream());
		out.write(data);
		out.flush();

		if (http.getResponseCode() == 200) {
			return http.getInputStream();
		}
		out.close();
		return null;
	}

	// 发送xml
	public static InputStream sendXml(String urlPath, byte[] xmlData,
			String encoding) throws Exception {
		URL url = new URL(urlPath);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setConnectTimeout(5 * 1000);
		http.setRequestMethod("POST");
		http.setDoOutput(true);// 发送POST请求必须设置允许输出
		http.setUseCaches(false);// 不适用cache
		http.setRequestProperty("Connection", "Keep-Alive");
		http.setRequestProperty("Charset", encoding);
		http.setRequestProperty("Content-Length",
				String.valueOf(xmlData.length));
		http.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		DataOutputStream out = new DataOutputStream(http.getOutputStream());
		out.write(xmlData);
		out.flush();
		if (http.getResponseCode() == 200) {
			return http.getInputStream();
		}
		out.close();
		return null;
	}

	// 从流中读取信息
	public static byte[] readStream(InputStream ins) throws Exception {
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

	public static void openURL(String url) {
		try {
			browse(url);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
					null,
					"Error attempting to launch web browser:\n"
							+ e.getLocalizedMessage());
		}
	}

	private static void browse(String url) throws ClassNotFoundException,
			IllegalAccessException, IllegalArgumentException,
			InterruptedException, InvocationTargetException, IOException,
			NoSuchMethodException {
		String osName = System.getProperty("os.name", "");
		if (osName.startsWith("Mac OS")) {
			Class fileMgr = Class.forName("com.apple.eio.FileManager");
			Method openURL = fileMgr.getDeclaredMethod("openURL",
					new Class[] { String.class });
			openURL.invoke(null, new Object[] { url });
		} else if (osName.startsWith("Windows")) {
			Runtime.getRuntime().exec(
					"rundll32 url.dll,FileProtocolHandler " + url);
		} else { // assume Unix or Linux
			String[] browsers = { "firefox", "opera", "konqueror", "epiphany",
					"mozilla", "netscape" };
			String browser = null;
			for (int count = 0; count < browsers.length && browser == null; count++)
				if (Runtime.getRuntime()
						.exec(new String[] { "which", browsers[count] })
						.waitFor() == 0)
					browser = browsers[count];
			if (browser == null)
				throw new NoSuchMethodException("Could not find web browser");
			else
				Runtime.getRuntime().exec(new String[] { browser, url });
		}
	}
}
