package com.bdsoft.y2012.m09;

import java.net.URLEncoder;
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

import com.bdsoft.utils.NetTool;
import com.google.gson.Gson;

public class SinaJdPick {

	public static String BASE_URL = "http://s.weibo.com/weibo/";
	public static String CHARSET = "UTF-8";

	public static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm");

	public static void main(String[] args) throws Exception {
		String keys = "招聘 mail";

		ParameEncode pe = new ParameEncode(keys);
		pe.add("Refer", "index");
		pe.add("page", "1");

		String url = BASE_URL + pe;
		System.out.println(url);

		String src = NetTool.getTextContent(url, CHARSET);
		// System.out.println(src);

		Document html = Jsoup.parse(src);
		Elements scpts = html.getElementsByTag("script");
		int size = scpts.size();
		System.out.println(scpts.size());

		Element ele = scpts.get(size - 10);
		String json = ele.data();
		json = json.substring(json.indexOf("(") + 1, json.lastIndexOf(")"));
		// System.out.println(json);

		Gson g = new Gson();
		FeedJson fj = g.fromJson(json, FeedJson.class);
		// System.out.println(fj.getHtml());

		html = Jsoup.parse(fj.getHtml());
		Elements fls = html.getElementsByClass("feed_list");
		size = fls.size();
		System.out.println(size);
		List<Feed> feeds = new ArrayList<Feed>();
		for (Element fl : fls) {
			Feed f = new Feed();
			Element face = fl.getElementsByClass("face").get(0);
			Element a = face.getElementsByTag("a").get(0);
			f.setUserHome(a.attr("href"));
			f.setUserName(a.attr("title"));

			Element content = fl.getElementsByClass("content").get(0);
			Element cp = content.getElementsByTag("p").get(0);
			f.setEm(cp.getElementsByTag("em").get(0).text());

			cp = content.getElementsByTag("p").get(1);
			Element span = cp.getElementsByTag("span").get(0);
			a = span.getElementsByTag("a").get(0);
			f.setShare(f.parseCount(a.text()));
			a = span.getElementsByTag("a").get(1);
			f.setFav(f.parseCount(a.text()));
			a = span.getElementsByTag("a").get(2);
			f.setComm(f.parseCount(a.text()));

			a = cp.getElementsByClass("date").get(0);
			f.setDate(new Date(Long.parseLong(a.attr("date"))));

			System.out.println(f.toString());
			parse(f.toString());
			feeds.add(f);
		} 
		
	}

	private static void parse(String line) {
		Pattern p=Pattern.compile("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+");
		Matcher m=p.matcher(line);
		while(m.find()){
			System.out.println(m.group());
		}
	}

}

class Feed {
	private String userHome;
	private String userName;
	private String em;
	private int share;
	private int fav;
	private int comm;
	private Date date;
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("昵称：");
		sb.append(this.userName);
		sb.append("\n\t日期：");
		sb.append(this.getDate().toLocaleString());
		sb.append("\n\t微博：");
		sb.append(this.getEm());
		return sb.toString();
	}

	public int parseCount(String str) {
		if (str.indexOf("(") > 0) {
			String tmp = str.substring(str.indexOf("(") + 1,
					str.lastIndexOf(")"));
			return Integer.parseInt(tmp);
		}
		return 0;
	}

	public String getUserHome() {
		return userHome;
	}

	public void setUserHome(String userHome) {
		this.userHome = userHome;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEm() {
		return em;
	}

	public void setEm(String em) {
		this.em = em;
	}

	public int getShare() {
		return share;
	}

	public void setShare(int share) {
		this.share = share;
	}

	public int getFav() {
		return fav;
	}

	public void setFav(int fav) {
		this.fav = fav;
	}

	public int getComm() {
		return comm;
	}

	public void setComm(int comm) {
		this.comm = comm;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}

class FeedJson {
	private String pid;
	// private String js;
	// private String css;
	private String html;

	public FeedJson() {
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	//
	// public String getJs() {
	// return js;
	// }
	//
	// public void setJs(String js) {
	// this.js = js;
	// }
	//
	// public String getCss() {
	// return css;
	// }
	//
	// public void setCss(String css) {
	// this.css = css;
	// }

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

}

class ParameEncode {
	private StringBuffer parame = new StringBuffer();

	public ParameEncode(String value) {
		encode(value);
	}

	public ParameEncode(String name, String value) {
		encode(name, value);
	}

	public synchronized void add(String name, String value) {
		parame.append("&");
		encode(name, value);
	}

	private synchronized void encode(String value) {
		parame.append(sinaEncode(value));
	}

	private synchronized void encode(String name, String value) {
		parame.append(sinaEncode(name));
		parame.append("=");
		parame.append(sinaEncode(value));
	}

	public String getParame() {
		return parame.toString();
	}

	public String toString() {
		return getParame();
	}

	public String sinaEncode(String url) {
		try {
			String first = URLEncoder.encode(url, SinaJdPick.CHARSET);
			first = first.replaceAll("\\+", "%20");
			return URLEncoder.encode(first, SinaJdPick.CHARSET);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
