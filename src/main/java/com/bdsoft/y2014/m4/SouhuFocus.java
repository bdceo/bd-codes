package com.bdsoft.y2014.m4;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.bdsoft.utils.NetTool;

/**
 * 搜狐焦点
 * 
 * @author 丁辰叶
 * @date 2014-4-9
 */
public class SouhuFocus {

	private static final String CHARSET = "GB2312";

	private static final String HOST = "http://xingtai.focus.cn";

	public static void main(String[] args) throws Exception {
		// fetchList(2);

		fetchDetail();
	}

	// 历史价格
	private static void fetchHistory() throws Exception {

	}

	// 详情
	private static void fetchDetail() throws Exception {
		String url = "http://xingtai.focus.cn/votehouse/135.html";

		String src = NetTool.getTextContent(url, CHARSET);
//		System.out.println(src);
		Document html = Jsoup.parse(src);

		Elements eles = html.getElementsByClass("blockRA");
		if (eles.size() > 0) {
			eles = eles.get(0).getElementsByTag("ul");
			if (eles.size() > 0) {
				eles = eles.get(0).getElementsByTag("li");
				
				Element ele = eles.get(0);
				String junJia = ele.getElementsByClass("red").get(0).text();
				System.out.println(junJia);

				ele = eles.get(2);
				String priceInfo = ele.ownText();
				System.out.println(priceInfo);

				ele = eles.get(3);
				String buildType = ele.ownText();
				System.out.println(buildType);

				ele = eles.get(4);
				String kaiPan = ele.ownText();
				System.out.println(kaiPan);

				ele = eles.get(5);
				String ruZhu = ele.ownText();
				System.out.println(ruZhu);

				ele = eles.get(6);
				String chanQuan = ele.ownText();
				System.out.println(chanQuan);

				ele = eles.get(7);
				String zhuangXiu = ele.ownText();
				System.out.println(zhuangXiu);

				ele = eles.get(8);
				String priceWuye = ele.ownText();
				System.out.println(priceWuye);

				ele = eles.get(9);
				String builder = ele.getElementsByTag("a").text();
				System.out.println(builder);

				ele = eles.get(10);
				String mianJi = ele.getElementsByTag("span").text();
				System.out.println(mianJi);

				ele = eles.get(11);
				String address = ele.ownText();
				System.out.println(address);

				ele = eles.get(12);
				String traffic = ele.ownText();
				System.out.println(traffic);
			}
		}

	}

	// 列表，分页
	private static void fetchList(int page) throws Exception {
		String url = "http://xingtai.focus.cn/search/0_0_0_0_0_0_0_0_0.html";

		if (page > 1) {
			String pager = "?&page="; // 分页
			url += pager + page;
		}

		System.out.println("抓取 》》 " + url);
		String src = NetTool.getTextContent(url, CHARSET);
		Document html = Jsoup.parse(src);

		Elements eles = html.getElementsByClass("searchList");
		if (eles.size() > 0) {
			eles = eles.get(0).getElementsByClass("box");
			for (Element ele : eles) {

				Element tmp = ele.getElementsByClass("img").get(0);
				if (tmp != null) {
					tmp = tmp.getElementsByTag("a").get(0);
				} else {
					continue;
				}
				String href = HOST + tmp.attr("href");
				System.out.println(href);
			}
		}
	}

}
