package com.bdsoft.y2012.m10;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.bdsoft.utils.FileUtil;
import com.bdsoft.utils.NetTool;
import com.bdsoft.utils.SMS;
import com.google.gson.Gson;

public class SinaUserPicker {

	public static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm");

	public static String BASE_URL = "http://s.weibo.com/user/";
	public static String CHARSET = "UTF-8";
	public static Integer SLEEP = 15;

	public static Integer START = 1;
	public static String KEYWORDS = "hrd ";
	public static Integer PAGE = 9;

	public static String FILE_PATH = "d:/download/";
	public static String FILE_TYPE = ".txt";

	// java.lang.IllegalStateException: Invalid use of BasicClientConnManager:
	// connection still allocated.
	// Make sure to release the connection before allocating another one.
	public static void main(String[] args) {
		// try {
		// pickByHand();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// System.exit(1);

		DefaultHttpClient client = SinaLogin2.login();
		List<UserFeed> feeds = new ArrayList<UserFeed>();
		try {
			for (int i = START; i <= PAGE; i++) {
				// List<SinaUserFeed> tmp = pick(KEYWORDS, i);
				List<UserFeed> tmp = pickWithLogin(KEYWORDS, i, client);
				feeds.addAll(tmp);
				// Thread.sleep(SLEEP);
				System.out.print("休眠倒计时-->");
				for (int j = SLEEP; j > 0; j--) {
					System.out.print(" " + j);
					Thread.sleep(1000);
				}
				System.out.println("");
			}
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
	private static void saveFeeds(List<UserFeed> feeds) {
		// StringBuffer sb = new StringBuffer();
		// System.out.println(sb.toString());
		// String file = FILE_PATH + new Date().getTime() + KEYWORDS +
		// FILE_TYPE;
		// FileUtil.writeFile(file, sb.toString(), true);
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
	private static List<UserFeed> pick(String key, int page)
			throws Exception {
		System.out.println("\n开始抓取第<" + page + ">页");
		SinaEncoder pe = new SinaEncoder(key);
		pe.add("page", "" + page);
		pe.add("Refer", "weibo_user");
		// SinaEncoder pe = new SinaEncoder("nickname", key);
		// pe.add("page", "" + page);

		String url = BASE_URL + pe;
		System.out.println(url);

		String src = NetTool.getTextContent(url, CHARSET);
		// System.out.println(src);
		return pickSerFeeds(src);
	}

	/**
	 * 登陆后抓取，利用httpclient
	 * 
	 * @param key
	 * @param page
	 * @param client
	 * @return
	 * @throws Exception
	 */
	private static List<UserFeed> pickWithLogin(String key, int page,
			DefaultHttpClient client) throws Exception {
		System.out.println("\n开始抓取第<" + page + ">页");
		SinaEncoder pe = new SinaEncoder(key);
		pe.add("page", "" + page);
		pe.add("Refer", "weibo_user");

		String url = BASE_URL + pe;
		System.out.println(url);

		String src = NetTool.getPageContent(url, client);
//		System.out.println(src);
		return pickSerFeeds(src);
	}

	private static void pickByHand() throws Exception {
		File file = new File(FileUtil.getClassPath() + "lr.html");
		FileInputStream fi = new FileInputStream(file);
		String src = NetTool.getContentFromStream(fi, CHARSET);
		pickSerFeeds(src);
	}

	/**
	 * 提取新浪搜索结果Feed
	 * 
	 * @param src
	 *            搜索结果页
	 * @return Feed搜索结果集合
	 */
	private static List<UserFeed> pickSerFeeds(String src) throws Exception {
		List<UserFeed> feeds = new ArrayList<UserFeed>();
		Document html = Jsoup.parse(src);
		Elements scpts = html.getElementsByTag("script");
		// 找到搜索结果的script标签，搜索结果是通过js输出的
		int size = scpts.size();

		System.out.println("script size = " + scpts.size());
		if (size < 10) {
			SMS.sendSMS("18668639647", "script小于10个，IP被封...");
			throw new Exception("script小于10个，IP被封...");
		}

		// 索引重要，改版可能会变7
		Element ele = scpts.get(7);
		String json = ele.data(); // 搜索结果的js输出内容是json结构
		json = json.substring(json.indexOf("(") + 1, json.lastIndexOf(")"));
		System.out.println(json);
		Gson g = new Gson();
		SinaFeedJson fj = g.fromJson(json, SinaFeedJson.class);
		// System.out.println(fj.getHtml());
		// 解析出json结构中的feed内容-转成html
		html = Jsoup.parse(fj.getHtml());
		// System.out.println(html);

		Elements fls = html.getElementsByClass("pl_personlist").get(0)
				.getElementsByClass("list_person");
		size = fls.size();
		if (size == 0) {
			System.out.println("分页完毕，没有新结果了，返回");
			return feeds;
		}
		System.out.println("feed size = " + size);
		// 遍历Feed输出转换
		for (int i = 0; i < size; i++) {
			Element fl = fls.get(i);
			UserFeed f = new UserFeed();
			Element detail = fl.getElementsByClass("person_detail").get(0);
			Element pn = detail.getElementsByTag("p").get(0);// person_name
			Element tmp = pn.getElementsByTag("a").get(0);
			f.setUser(tmp.attr("title"));
			f.setUid(tmp.attr("uid"));
			f.setUserUrl(tmp.attr("href"));

			pn = detail.getElementsByClass("person_addr").get(0);// person_addr
			tmp = pn.getElementsByTag("span").get(1);
			f.setAddr(tmp.text());

			int ss = detail.getElementsByClass("person_card").size();
			if (ss > 0) {
				pn = detail.getElementsByClass("person_card").get(0);// person_card
				f.setCard(pn.text());
			}

			pn = detail.getElementsByClass("person_num").get(0);// person_num
			tmp = pn.getElementsByTag("span").get(0);
			String num = tmp.getElementsByTag("a").get(0).text();
			f.setGz(Integer.parseInt(num));
			tmp = pn.getElementsByTag("span").get(1);
			num = tmp.getElementsByTag("a").get(0).text();
			f.setFs(Integer.parseInt(num.replaceAll("万", "0000")));
			tmp = pn.getElementsByTag("span").get(2);
			num = tmp.getElementsByTag("a").get(0).text();
			f.setWb(Integer.parseInt(num.replaceAll("万", "0000")));

			ss = detail.getElementsByClass("person_info").size();
			if (ss > 0) {
				pn = detail.getElementsByClass("person_info").get(0);// person_info
				String text = pn.getElementsByTag("p").get(0).text();
				f.setInfo(text);
			}

			Elements pls = detail.getElementsByClass("person_label");// person_label
			for (Element p : pls) {
				if (p.text().contains("标签")) {
					f.setLabel(p.text());
					continue;
				} else if (p.text().contains("教育信息")) {
					f.setSchool(p.text());
					continue;
				} else {
					f.setJob(p.text());
				}
			}

			System.out.println("第" + (i + 1) + "条\t" + f);
			feeds.add(f);
		}
		return feeds;
	}

	/**
	 * 手动抓取
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	private static List<UserFeed> pickByHand(String name) throws Exception {
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
		for (int i = 0; i < scpts.size(); i++) {
			ele = scpts.get(i);
			System.out.println(i + "\t" + ele.html());
			if (ele.html().contains("pl_user_feedList")) {
				break;
			}
		}
		return ele;
	}

}