package com.bdsoft.y2012.m12;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class DouReview {

	public static String CHARSET = "utf-8";

	// 经济学界里面的“韩寒”-cn
	public static String url = "http://book.douban.com/review/5616496/";
	// public static String url = "http://book.douban.com/review/5605303/";
	public static String PATH = "D:\\download\\douban\\review5616496.html";

	public static void main(String[] args) throws Exception {
		// String src = NetTool.getTextContent(url, CHARSET);
		String src = DouBook.fromFile(PATH);

		Document html = Jsoup.parse(src);
		// System.out.println(html.outerHtml());

		Element root = html.getElementsByClass("article").get(0);
		Element e = html.getElementsByTag("h1").get(0);

		String tittle = e.text();
		System.out.println("标题=" + tittle);

		root = root.getElementsByClass("piir").get(0);
		e = root.getElementsByClass("mn").get(0);
		String date = e.text();
		System.out.println("日期=" + date);

		e = root.getElementsByClass("pl2").get(0);
		e = e.getElementsByTag("a").get(0);
		System.out.println("用户=" + e.text() + "\t主页=" + e.attr("href"));

		e = root.getElementById("link-report");
		String info = e.text();
		System.out.println("评论=" + info);

	}

}
