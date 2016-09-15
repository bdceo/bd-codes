package com.bdsoft.y2012.m10;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.bdsoft.utils.FileUtil;
import com.bdsoft.utils.NetTool;
import com.bdsoft.utils.SMS;
import com.google.gson.Gson;

public class SinaWeiboPicker {

	public static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm");

	public static String BASE_URL = "http://s.weibo.com/weibo/";
	public static String CHARSET = "UTF-8";
	public static Integer SLEEP = 15;

	public static Integer START = 1;
	public static String KEYWORDS = "招聘 java 简历";
	public static Integer PAGE = 50;

	public static String FILE_PATH = "d:/download/";
	public static String FILE_TYPE = ".txt";

	public static void main(String[] args) {
		List<WeiboFeed> feeds = new ArrayList<WeiboFeed>();
		try {
			for (int i = START; i <= PAGE; i++) {
				List<WeiboFeed> tmp = pick(KEYWORDS, i);
				feeds.addAll(tmp);
				System.out.print("分页倒计时-->");
				for (int j = SLEEP; j > 0; j--) {
					System.out.print(" " + j);
					Thread.sleep(1000);
				}
				System.out.println("");
			}

			// for (int i = 1; i <= PAGE; i++) {
			// System.out.println("第"+i+"页");
			// List<SinaSerFeed> tmp = pickByHand(i + ".htm");
			// feeds.addAll(tmp);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			saveFeeds(feeds);
		}
	}

	/**
	 * 保存文件
	 * 
	 * @param feeds
	 */
	private static void saveFeeds(List<WeiboFeed> feeds) {
		StringBuffer sb = new StringBuffer();
		for (WeiboFeed feed : feeds) {
			sb.append(feed.get_mail());
		}
		// System.out.println(sb.toString());
		String file = FILE_PATH + new Date().getTime() + KEYWORDS + FILE_TYPE;
		FileUtil.writeFile(file, sb.toString(), true);
	}

	/**
	 * 抓取搜索结果
	 * 
	 * @param key
	 *            关键字
	 * @param page
	 *            分页码
	 * @return Feed搜索结果集合
	 * 
	 * @throws Exception
	 */
	private static List<WeiboFeed> pick(String key, int page) throws Exception {
		System.out.println("\n开始抓取第<" + page + ">页");
		SinaEncoder pe = new SinaEncoder(key);
		// pe.add("Refer", "STopic_box");
		pe.add("page", "" + page);

		String url = BASE_URL + pe;
		System.out.println(url);

		String src = NetTool.getTextContent(url, CHARSET);
		// System.out.println(src);
		return pickSerFeeds(src);
	}

	/**
	 * 提取新浪搜索结果Feed
	 * 
	 * @param src
	 *            搜索结果页
	 * @return Feed搜索结果集合
	 */
	private static List<WeiboFeed> pickSerFeeds(String src) throws Exception {
		List<WeiboFeed> feeds = new ArrayList<WeiboFeed>();
		Document html = Jsoup.parse(src);
		Elements scpts = html.getElementsByTag("script");
		// 找到搜索结果的script标签，搜索结果是通过js输出的
		int size = scpts.size();
		System.out.println("script size = " + scpts.size());
		if (size < 10) {
			SMS.sendSMS("18668639647", "script小于10个，IP被封...");
			throw new Exception("script小于10个，IP被封...");
		}

		// 索引重要，改版可能会变9，11，13
		// Element ele = scpts.get(13);// 1-数数
		
		Element ele = getJSfeed(scpts);// 2-循环
		String json = ele.data();
		
//		String json = getJSfeed2(scpts);// 3-正则
		
		// 搜索结果的js输出内容是json结构
		json = json.substring(json.indexOf("(") + 1, json.lastIndexOf(")"));
		// System.out.println(json);

		// 解析出json结构中的feed内容-转成html
		Gson g = new Gson();
		SinaFeedJson fj = g.fromJson(json, SinaFeedJson.class);
		// System.out.println(fj.getHtml());
		html = Jsoup.parse(fj.getHtml());
		// System.out.println(html);

		// 未找到“关键词”相关结果
		if (html.getElementsByClass("pl_noresult").size() > 0) {
			System.out.println("分页完毕，没有新结果了，返回");
			return feeds;
		}

		Elements fls = html.getElementsByClass("feed_list");
		size = fls.size();
		System.out.println("feed size = " + size);
		// 遍历Feed输出转换
		int i = 1;
		for (Element fl : fls) {
			WeiboFeed f = new WeiboFeed();
			Element face = fl.getElementsByClass("face").get(0);
			Element a = face.getElementsByTag("a").get(0);
			f.setUserHome(a.attr("href"));
			f.setUserName(a.attr("title"));

			Element content = fl.getElementsByClass("content").get(0);
			Element cp = content.getElementsByTag("p").get(0);
			f.setEm(cp.getElementsByTag("em").get(0).text());
			// 转发内容
			int s = content.getElementsByTag("dl").size();
			if (s > 0) {
				Element dl = content.getElementsByTag("dl").get(0);
				s = dl.getElementsByTag("dt").size();
				if (s > 0) {
					Element dt = dl.getElementsByTag("dt").get(0);
					s = dt.getElementsByTag("em").size();
					if (s > 0) {
						String txt = f.getEm()
								+ dt.getElementsByTag("em").get(0).text();
						f.setEm(txt);
					}
				}
			}

			cp = content.getElementsByTag("p").get(1);
			// feed包含地图坐标信息，需要拿第三个p
			if (cp.hasClass("map_data")) {
				cp = content.getElementsByTag("p").get(2);
			}
			Element span = cp.getElementsByTag("span").get(0);
			a = span.getElementsByTag("a").get(0);
			f.setShare(f.parseCount(a.text()));
			a = span.getElementsByTag("a").get(1);
			f.setFav(f.parseCount(a.text()));
			a = span.getElementsByTag("a").get(2);
			f.setComm(f.parseCount(a.text()));
			// 猎头发布
			if (SinaPickFilter.getInst().isHunter(a.text())) {
				continue;
			}
			// 发布时间
			a = cp.getElementsByClass("date").get(0);
			f.setDate(new Date(Long.parseLong(a.attr("date"))));
			f.set_mail(pickEmail(f.toString()));

			System.out.println("第" + i + "条\t" + f);
			System.out.println(pickEmail(f.toString()));
			feeds.add(f);
			i++;
		}
		return feeds;
	}

	/**
	 * 提取email，换行返回
	 * 
	 * @param content
	 * @return
	 */
	private static String pickEmail(String content) {
		Pattern p = Pattern.compile("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+");
		Matcher m = p.matcher(content);
		StringBuffer sb = new StringBuffer("");
		while (m.find()) {
			sb.append(m.group());
			sb.append(";");
		}
		String mail = sb.toString();
		if (mail.length() > 0) {
			mail = mail.substring(0, mail.length() - 1);
		}
		return mail;
	}

	/**
	 * 手动抓取
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	private static List<WeiboFeed> pickByHand(String name) throws Exception {
		File file = new File(FILE_PATH + name);
		FileInputStream fi = new FileInputStream(file);
		String src = NetTool.getContentFromStream(fi, CHARSET);
		return pickSerFeeds(src);
	}

	/**
	 * 从js中获取微博feed
	 * 
	 * @param scpts
	 * @return
	 */
	private static Element getJSfeed(Elements scpts) {
		Element ele = null;
		int size = scpts.size();
		long start = new Date().getTime();
		for (int i = size - 1; i > 0; i--) {
			ele = scpts.get(i);
			System.out.println(i + "\t" + ele.html());
			if (ele.html().contains("\"pid\":\"pl_weibo_feedlist\"")) {
				break;
			}
		}
		long stop = new Date().getTime();
		System.out.println("#1 = " + (stop - start));
		return ele;
	}

	/**
	 * 正则提取微博feed的script标签
	 * @param scpts
	 * @return
	 */
	private static String getJSfeed2(Elements scpts) {
		long start = new Date().getTime();
		String script = "";
		String reg = "<script[^>]*?>.*?\"pid\":\"pl_weibo_feedlist\"[\\s\\S]*?<\\/script>";
		Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(scpts.outerHtml());
		if (matcher.find()) {
			script = matcher.group();
		}
		long stop = new Date().getTime();
		System.out.println("#2 = " + (stop - start));
		return script;
	}

}