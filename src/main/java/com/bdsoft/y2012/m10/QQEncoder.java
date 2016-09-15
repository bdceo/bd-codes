package com.bdsoft.y2012.m10;

import java.net.URLEncoder;

public class QQEncoder {
	private StringBuffer parame = new StringBuffer();

	public QQEncoder(String value) {
		encode(value);
	}

	public QQEncoder(String name, String value) {
		encode(name, value);
	}

	public synchronized void add(String name, String value) {
		parame.append("&");
		encode(name, value);
	}

	private synchronized void encode(String value) {
		parame.append(sinaEncode(value));
	}

	private synchronized void encode(String name, String value) {
		parame.append(sinaEncode(name));
		parame.append("=");
		parame.append(sinaEncode(value));
	}

	public String getParame() {
		return parame.toString();
	}

	public String toString() {
		return getParame();
	}

	public String sinaEncode(String url) {
		try {
			String first = URLEncoder.encode(url, SinaWeiboPicker.CHARSET);
			first = first.replaceAll("\\+", "%20");
			return first;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
