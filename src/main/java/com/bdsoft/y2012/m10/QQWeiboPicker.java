package com.bdsoft.y2012.m10;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.bdsoft.utils.FileUtil;
import com.bdsoft.utils.NetTool;
import com.bdsoft.utils.StringUtil;

public class QQWeiboPicker {

	public static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy年MM月dd日 hh:mm");
	public static SimpleDateFormat ymd = new SimpleDateFormat("yyyy年MM月dd日");
	public static SimpleDateFormat mdt = new SimpleDateFormat("MM月dd日 hh:mm");

	public static String BASE_URL = "http://search.t.qq.com/index.php?";
	public static String CHARSET = "UTF-8";
	public static Integer SLEEP = 15;

	public static Integer START = 1;
	public static String KEYWORDS = "简历 邮箱";
	public static Integer PAGE = 50;

	public static String FILE_PATH = "d:/download/";
	public static String FILE_TYPE = ".txt";

	public static void main(String[] args) {
		// try {
		// parseDate("2011年12月22日 18:52");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// System.exit(1);

		DefaultHttpClient client = QQLogin.login();

		List<WeiboFeed> feeds = new ArrayList<WeiboFeed>();
		try {
			for (int i = START; i <= PAGE; i++) {
				List<WeiboFeed> tmp = pick(KEYWORDS, i, client);
				if (tmp.size() == 0) {
					break;
				}
				feeds.addAll(tmp);
				// Thread.sleep(SLEEP);
				if (i == PAGE) {
					break;
				}
				System.out.print("休眠倒计时-->");
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
	 *            第几页
	 * @param client
	 *            登陆session
	 * @return Feed搜索结果集合
	 * 
	 * @throws Exception
	 */
	private static List<WeiboFeed> pick(String key, int page,
			DefaultHttpClient client) throws Exception {
		System.out.println("\n开始抓取第<" + page + ">页");
		QQEncoder pe = new QQEncoder("pos", "201");
		pe.add("k", key);
		pe.add("p", "" + page);

		String url = BASE_URL + pe;
		System.out.println(url);

		String src = NetTool.getPageContent(url, client);
		// System.out.println(src);
		return pickSerFeeds(src);
	}

	/**
	 * 提取腾讯搜索结果Feed
	 * 
	 * @param src
	 *            搜索结果页
	 * @return Feed搜索结果集合
	 */
	private static List<WeiboFeed> pickSerFeeds(String src) throws Exception {
		List<WeiboFeed> feeds = new ArrayList<WeiboFeed>();
		Document html = Jsoup.parse(src);
		Element talkUL = html.getElementById("talkList");
		if (talkUL == null) {
			System.out.println("关键词未搜索到数据");
			return feeds;
		}
		Elements eleLIs = talkUL.getElementsByTag("li");
		int size = eleLIs.size();
		System.out.println("feed size = " + size);

		// 分页条获取总页数
		Element pageNav = html.getElementsByClass("pageNav").get(0);
		size = pageNav.getElementsByTag("a").size();
		if (size <= 1) {
			PAGE = 1;
		} else {
			Elements pas = pageNav.getElementsByTag("a");
			Element a = pas.get(size - 1);
			if (StringUtil.isEmpty(a.attr("class"))) {
				PAGE = Integer.parseInt(a.text());
			} else {
				a = pas.get(size - 2);
				PAGE = Integer.parseInt(a.text());
			}
		}

		// 遍历Feed输出转换
		int i = 1;
		for (Element li : eleLIs) {
			WeiboFeed f = new WeiboFeed();
			Element tmp = li.getElementsByClass("userPic").get(0);// 头像
			Element e = tmp.getElementsByTag("a").get(0);
			f.setUserHome(e.attr("href"));

			tmp = li.getElementsByClass("msgBox").get(0);// 用户
			e = tmp.getElementsByClass("userName").get(0).getElementsByTag("a")
					.get(0);
			f.setUserName(e.text());

			e = tmp.getElementsByClass("msgCnt").get(0);// 内容
			f.setEm(e.text());

			// 转发
			int s = tmp.getElementsByClass("replyBox").size();
			if (s > 0) {
				e = tmp.getElementsByClass("replyBox").get(0);
				s = e.getElementsByClass("msgCnt").size();
				if (s > 0) {
					e = e.getElementsByClass("msgCnt").get(0);
					String txt = f.getEm() + "；转发：" + e.text();
					f.setEm(txt);
				}
			}

			s = tmp.getElementsByClass("replyBox").size();
			if (s > 0) {
				tmp = tmp.getElementsByClass("pubInfo").get(1);// 发布信息
			} else {
				tmp = tmp.getElementsByClass("pubInfo").get(0);// 发布信息
			}
			e = tmp.getElementsByClass("left").get(0);// 时间
			e = e.getElementsByTag("a").get(0);
			f.setDate(parseDate(e.text()));
			e = tmp.getElementsByClass("funBox").get(0);// 转评数
			tmp = e.getElementsByTag("a").get(0);// 转播
			f.setShare(Integer.parseInt(tmp.attr("num")));
			tmp = e.getElementsByTag("a").get(1);// 评论
			f.setComm(Integer.parseInt(tmp.attr("num")));
			f.setFav(0);

			System.out.println("第" + i + "条\t" + f);
			System.out.println(pickEmail(f.toString()));
			feeds.add(f);
			i++;
		}
		return feeds;
	}

	private static Date parseDate(String str) throws Exception {
		// System.out.println(str);
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		int num = 0;
		if (str.indexOf("分钟") >= 0) {
			String tmp = str.substring(0, str.indexOf("分钟"));
			// System.out.println(tmp);
			num = Integer.parseInt(tmp);
			c.add(Calendar.MINUTE, num * -1);
		} else if (str.indexOf("秒") >= 0) {
			String tmp = str.substring(0, str.indexOf("秒"));
			// System.out.println(tmp);
			num = Integer.parseInt(tmp);
			c.add(Calendar.SECOND, num * -1);
		} else if (str.indexOf("今天") >= 0) {
			String tmp = str.substring(str.indexOf("今天 ") + 2, str.length());
			tmp = ymd.format(now) + tmp;
			// System.out.println(tmp);
			c.setTime(sdf.parse(tmp));
		} else if (str.indexOf("昨天") >= 0) {
			String tmp = str.substring(str.indexOf("昨天 ") + 2, str.length());
			c.add(Calendar.DATE, -1);
			tmp = ymd.format(c.getTime()) + tmp;
			// System.out.println(tmp);
			c.setTime(sdf.parse(tmp));
		} else if (str.indexOf("年") >= 0) {
			now = sdf.parse(str);
			c.setTime(now);
		} else {
			now = mdt.parse(str);
			c.setTime(now);
		}
		return c.getTime();
	}

	/**
	 * 提取email，分号隔开
	 * 
	 * @param content
	 * @return
	 */
	private static String pickEmail(String content) {
		content = content.replaceAll("\\[at\\]", "@");
		content = content.replaceAll("#", "@");
		content = content.replaceAll(" at ", "@");
		content = content.replaceAll("@ ", "@");
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
		for (int i = 0; i < scpts.size(); i++) {
			ele = scpts.get(i);
			System.out.println(i + "\t" + ele.html());
			if (ele.html().contains("pl_weibo_feedlist")) {
				break;
			}
		}
		return ele;
	}

}