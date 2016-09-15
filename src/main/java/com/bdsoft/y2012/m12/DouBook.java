package com.bdsoft.y2012.m12;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sun.swing.StringUIClientPropertyKey;

import com.bdsoft.utils.NetTool;
import com.bdsoft.utils.StringUtil;

public class DouBook {

	public static String CHARSET = "utf-8";

	// 百年孤独-en
	// public static String url = "http://book.douban.com/subject/1786670/";
	// public static String PATH = "D:\\download\\douban\\book1786670.html";
	// 我和这个世界不熟-cn
	// public static String url = "http://book.douban.com/subject/19995776/";
	// public static String PATH = "D:\\download\\douban\\book19995776.html";
	// 中国经济到了最危险的边缘-cn
//	public static String url = "http://book.douban.com/subject/10607197/";
	public static String PATH = "D:\\download\\douban\\book10607197.html";

	// 中国经济到了最危险的边缘-cn
	// public static String url = "http://book.douban.com/subject/1085002/";
	// public static String PATH = "D:\\download\\douban\\book1085002.html";
	
//	public static String url = "http://book.douban.com/subject/4242094/";
	
	public static String url = "http://book.douban.com/subject/4242094/";	

	public static void main(String[] args) throws Exception {
		 String src = NetTool.getTextContent(url, CHARSET);
//		String src = fromFile(PATH);

		Document html = Jsoup.parse(src);
//		 System.out.println(html.outerHtml());

		// 解析书名
		Element root = html.getElementById("wrapper");
		Elements es = root.getElementsByTag("h1");
		String name = "";
		name = es.get(0).text();
		System.out.println(name);

		// 图片
		Element e = root.getElementById("mainpic");
		String pic = "";
		e = e.getElementsByTag("img").get(0);
		pic = e.attr("src");
		name = e.attr("alt");
		System.out.println("图片=" + pic);

		// 图书基本信息
		e = root.getElementById("info");
		String tmp = e.outerHtml();
		// System.out.println(tmp);
		es = e.getElementsByClass("pl");
		Map<String, String> infoMap = new HashMap<String, String>();
		for (Element ele : es) {
			String key = ele.text().replace(":", "");
			String value = "";
			if (key.equals("作者") || key.equals("译者")) {
				tmp = tmp.substring(tmp.indexOf("</span>") + 8);
				value = tmp.substring(0, tmp.indexOf("</span>"));
				tmp = tmp.substring(tmp.indexOf("<br />") + 6);
			} else {
				tmp = tmp.substring(tmp.indexOf("</span>") + 7);
				value = tmp.substring(0, tmp.indexOf("<br />"));
			}
			infoMap.put(key.trim(), value.trim());
		}
		mapCn(infoMap);
		System.out.println(infoMap.toString());

		// 评价分级
		e = root.getElementsByClass("rating_num").get(0);
		String rank = e.text();
		System.out.println("评分=" + rank);

		// 内容简介
		int i = 0;
		e = root.getElementById("link-report");
		if (e != null) {
			es = e.getElementsByClass("intro");
			i = es.size() > 1 ? 1 : 0;
			String bookIntro = es.get(i).text();
			System.out.println("内容简介=" + bookIntro);
		}

		// 作者简介
		es = root.getElementsByClass("related_info");
		es = es.get(0).getElementsByClass("indent");
		if (es.size() > 1) {
			e = es.get(1);// 作者简介
			es = e.getElementsByClass("intro");
			if (es.size() != 0) {
				i = es.size() > 1 ? 1 : 0;
				String authorIntro = es.get(i).text();
				System.out.println("作者简介=" + authorIntro);
			}
		}

		// 图书目录
		String bookDir = "";
		String urlId = pickUrlId();
		e = root.getElementById("dir_" + urlId + "_full");
		if (e != null) {
			bookDir = e.text();
		} else {
			e = root.getElementById("dir_" + urlId + "_short");
			if (e != null) {
				bookDir = e.text();
			}
		}
		System.out.println("目录=" + bookDir);

		// 标签
		e = root.getElementById("db-tags-section");
		es = e.getElementsByTag("a");
		System.out.print("标签=");
		for (Element ele : es) {
			System.out.print(ele.text() + "  ");
		}

		// 推荐图书
		e = root.getElementById("db-rec-section");
		if (e != null) {
			es = e.getElementsByTag("dl");
			System.out.println("\n推荐=");
			i = 0;
			for (Element ele : es) {
				i++;
				if ((i % 6) == 0) {
					i = 0;
					continue;
				}
				e = ele.getElementsByTag("a").get(1);
				System.out.println("\t" + e.text() + "\t" + e.attr("href"));
			}
		}

		// 购买链接
		String buyUrl = url + "buylinks";
		System.out.println("购买链接=" + buyUrl);

		// 所有评论
		// String reviewsUrl = url + "reviews?sort=time";
		String reviewsUrl = url + "reviews";
		System.out.println("评论链接=" + reviewsUrl);
	}

	public static String fromFile(String path) throws Exception {
		FileInputStream in = new FileInputStream(new File(path));
		return NetTool.getContentFromStream(in, CHARSET);
	}

	public static String pickUrlId() {
		String reg = "\\d+";
		Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(url);
		if (matcher.find()) {
			return matcher.group();
		}
		return "";
	}

	private static void mapCn(Map<String, String> infoMap) {
		for (Entry<String, String> en : infoMap.entrySet()) {
			if (en.getKey().equals("定价")) {
				infoMap.put(en.getKey(), en.getValue().replace("元", "").trim());
			} else if (en.getKey().equals("作者") || en.getKey().equals("译者")
					|| en.getKey().equals("丛书")) {
				String[] aus = en.getValue().split("</a>");
				StringBuffer sb = new StringBuffer();
				for (String tmp : aus) {
					if (StringUtil.isEmpty(tmp)) {
						continue;
					}
					sb.append(tmp.substring(tmp.indexOf(">") + 1))
							.append(" / ");
				}
				String tmp = sb.toString();
				tmp = tmp.substring(0, tmp.lastIndexOf("/"));
				infoMap.put(en.getKey(), tmp.trim());
			} else if (en.getKey().equals("页数")) {
				infoMap.put(en.getKey(), en.getValue().replace("页", "").trim());
			}
		}
		if (infoMap.get("统一书号") != null) {
			String val = infoMap.get("统一书号");
			infoMap.put("ISBN", val);
		}
	}
}
