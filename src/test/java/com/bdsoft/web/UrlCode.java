package com.bdsoft.web;

import java.net.URLDecoder;

public class UrlCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String url = "json=%7B%27品牌%27%3A%271%27%2C%27价格%27%3A%274000-5000%27%7D&sort=0&sort2=0";
		System.out.println(ud(url));
		
		
		url = "json=%7B%27品牌%27%3A%2719%27%2C%27价格%27%3A%274000-5000%27%7D&sort=0&sort2=0";
		System.out.println(ud(url));
	}

	public static String ud(String url) {
		return URLDecoder.decode(url);
	}

}
