package com.bdsoft.y2012.m12;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.bdsoft.utils.StringUtil;

public class DouBuyLinks {

	public static String CHARSET = "utf-8";

	// 中国经济到了最危险的边缘-cn
	public static String url = "http://book.douban.com/subject/10607197/buylinks";
	// public static String PATH = "D:\\download\\douban\\buy10607197.html";

	public static String PATH = "D:\\download\\douban\\buy10566136.html";

	public static void main(String[] args) throws Exception {
		// String src = NetTool.getTextContent(url, CHARSET);
		String src = DouBook.fromFile(PATH);

		Document html = Jsoup.parse(src);
		// System.out.println(html.outerHtml());

		String bookUrl = url.substring(0, url.lastIndexOf("/") + 1);
		System.out.println(bookUrl);
		Element root = html.getElementById("buylink-table");
		if (root == null) {
			return;
		}
		Elements es = root.getElementsByTag("tr");
		int size = es.size();
		for (int i = 1; i < size; i++) {
			Elements tds = es.get(i).getElementsByTag("td");
			Element e = tds.get(1).getElementsByTag("a").get(0);
			System.out.println(e.text());
			System.out.println(decode(e.attr("href")));
			e = tds.get(3).getElementsByTag("a").get(0);
			System.out.println("售价=" + e.text());
			if (tds.size() > 4) {
				e = tds.get(4);
				if (!StringUtil.isEmpty(e.text())) {
					System.out.println("节省=" + e.text());
				}
			}
		}
	}

	public static String decode(String url) throws Exception {
		url = URLDecoder.decode(url, CHARSET);
		Map<String, String> params = parse(url);
		if (url.contains("vendor=joyo")) {
			url = params.get("url");
		} else if (url.contains("vendor=360buy")) {
			url = params.get("to");
		} else if (url.contains("vendor=beifa")) {
			url = params.get("url");
		} else if (url.contains("vendor=dangdang")) {
			url = params.get("backurl");
		} else if (url.contains("vendor=99read")) {
			url = params.get("t");
		} else if (url.contains("vendor=chinapub")) {
			url = params.get("URL");
		} else if (url.contains("vendor=bookuu")) {
			url = URLDecoder.decode(url, CHARSET);
			params = parse(url);
			url = params.get("t");
		} else if (url.contains("vendor=taoshu")) {
			url = params.get("url");
		} else if (url.contains("vendor=xinhua")) {
			url = URLDecoder.decode(url, CHARSET);
			params = parse(url);
			url = params.get("url");
		} else if (url.contains("vendor=lanree")) {
			url = params.get("url");
		} else if (url.contains("vendor=kuaishubao")) {
			url = params.get("url");
		}
		// NetTool.openURL(url);
		return url;
	}

	public static Map<String, String> parse(String url) {
		String[] params = url.split("\\&");
		Map<String, String> pm = new HashMap<String, String>();
		for (String p : params) {
			int index = p.indexOf("=");
			pm.put(p.substring(0, index), p.substring(index + 1));
		}
		return pm;
	}
}
