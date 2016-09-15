package com.bdsoft.y2012.m12;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DouReviews {

	public static String CHARSET = "utf-8";

	// 中国经济到了最危险的边缘-cn
	public static String url = "http://book.douban.com/subject/10607197/reviews?sort=time";
	public static String PATH = "D:\\download\\douban\\reviews10607197.html";

	public static void main(String[] args) throws Exception {
		// String src = NetTool.getTextContent(url, CHARSET);
		String src = DouBook.fromFile(PATH);

		Document html = Jsoup.parse(src);
		// System.out.println(html.outerHtml());

		Element root = html.getElementsByClass("article").get(0);
		Elements es = root.getElementsByClass("ctsh");
		Element e = null;
		int size = es.size();
		for (Element ele : es) {
			Elements tmp = ele.getElementsByTag("li");
			tmp = tmp.get(0).getElementsByTag("a");// a
			e = tmp.get(2);
			System.out.println(e.text());
			System.out.println(e.attr("href"));
		}
	}

}
