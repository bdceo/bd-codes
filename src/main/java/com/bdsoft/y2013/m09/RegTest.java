package com.bdsoft.y2013.m09;

import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegTest {

	private static String CHARCODE = "utf-8";

	// 对URL进行解码
	public static String decode(String url) throws Exception {
		return URLDecoder.decode(url);
	}

	// 验证正则
	public static boolean checkReg(String reg, String str) {
		Pattern p1 = Pattern.compile(reg);
		return p1.matcher(str).matches();
	}

	// 获取正则分组信息
	public static String takeGroup(String reg, String str, int index) {
		Pattern p1 = Pattern.compile(reg);
		Matcher m1 = p1.matcher(str);
		if (m1.find()) {
			return m1.group(index);
		}
		return "no match group";
	}

	public static void main(String[] args) {
		t0901AmazonReg();
	}

	public static void t0901AmazonReg() {
		String reg = "(?:^.*/dp/)([a-zA-Z0-9]+).*?";
//		reg = "(?:http://www.amazon.c.*/dp/)([a-zA-Z0-9]+).*?";
		String url = "http://www.amazon.cn/dp/B00960YR3Q/ref=sd_allcat_kindle_l3_pad_kindle_fir/480-1315718-0182144";

//		reg = "http://www.amazon.cn/.*/b/.*node=([\\d]+).*";
//		url = "http://www.amazon.cn/%E8%BF%9B%E5%8F%A3%E5%8E%9F%E7%89%88%E5%9B%BE%E4%B9%A6/b/ref=sd_allcat_books_l3_b2045366051/480-1315718-0182144?ie=UTF8&node=2045366051&page=2";

//		reg = "http://www.amazon.cn/b/.*node=([\\d]+).*";
//		url = "http://www.amazon.cn/b/ref=sd_allcat_books_l3_b2088297051/480-1315718-0182144?ie=UTF8&node=2088297051&page=3";
		// "http://www.amazon.cn/b/ref=sr_aj?node=51878071&ajr=0"
		// "http://www.amazon.cn/%E6%89%8B%E6%9C%BA-%E9%80%9A%E8%AE%AF/b/ref=sd_allcat_wi_?ie=UTF8&node=664978051"
		// "http://www.amazon.cn/%e7%94%b5%e5%ad%90%e4%b9%a6-%e5%b0%91%e5%84%bf/b?ie=utf8&node=143276071"

//		reg = "http://www.amazon.cn/s/.*node=([\\d]+).*";
//		url = "http://www.amazon.cn/s/ref=s9_dnav_bw_ir01_s?__mk_zh_CN=%E4%BA%9A%E9%A9%AC%E9%80%8A%E7%BD%91%E7%AB%99&node=658390051,!658391051,658400051,658619051&page=1&sort=salesrank&bbn=658400051&pf_rd_m=A1AJ19PSB66TGU&pf_rd_s=center-2&pf_rd_r=1ECRJMEFETPJS17WT9B4&pf_rd_t=101&pf_rd_p=78920572&pf_rd_i=658400051";

		System.out.println("reg =\t" + reg);
		System.out.println("url =\t" + url);
		boolean b = checkReg(reg, url);
		System.out.println("match?\t" + b);
		String tmp = takeGroup(reg, url, 1);
		System.out.println("group\t" + tmp);

		reg = "http://www.amazon.cn/b/.*page=([\\d]+).*";
		tmp = takeGroup(reg, url, 1);
		System.out.println("group\t" + tmp);
	}
}
