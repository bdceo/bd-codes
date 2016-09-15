package com.bdsoft.y2013.m10;

import weibo4j.util.BareBonesBrowserLaunch;

public class AutoDownload {

	private static String REPLACE_ID = "#ID#";
	private static String BASE_URL = "http://www.baiduqvod.com/player.html?m=25604&e=#ID#&p=qvod";
	private static long SLEEP = 1000L * 5;

	public static void main(String[] args) throws Exception {

		int start = 16;
		int total = 52;
		for (int i = start; i <= total; i++) {
			String url = BASE_URL.replaceAll(REPLACE_ID, i + "");
			System.out.println("准备下载：" + url);
			BareBonesBrowserLaunch.openURL(url);
			Thread.sleep(SLEEP);
		}

	}

}
