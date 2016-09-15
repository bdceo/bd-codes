package com.bdsoft.y2012.m10;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class QQLogin {

	// 参考：http://blog.csdn.net/wolfphantasms/article/details/7648547
	// MD5-Java: http://hi.baidu.com/royripple/item/8bbf740fef8783cc75cd3ce0
	// 较早：http://blog.csdn.net/wolfphantasms/article/details/7416533
	private static String USER = "1366802914";
	private static String PWD = "bdceo0517--";

	// private static String QQ_MD5 = "src/qqmd5.js"; // md5加密文件

	public static void main(String[] args) {
		login();
	}

	public static DefaultHttpClient login() {
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter("http.protocol.cookie-policy",
				CookiePolicy.BROWSER_COMPATIBILITY);
		client.getParams().setParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, 5000);
		try {

			/********************* 获取验证码 ***********************/
			HttpGet get = new HttpGet("http://check.ptlogin2.qq.com/check?uin="
					+ USER + "&appid=46000101&ptlang=2052&r=" + Math.random());
			get.setHeader("Host", "check.ptlogin2.qq.com");
			get.setHeader("Referer", "http://t.qq.com/?from=11");

			get
					.setHeader("User-Agent",
							"Mozilla/5.0 (Windows NT 5.1; rv:13.0) Gecko/20100101 Firefox/13.0");
			HttpResponse response = client.execute(get);

			String entity = EntityUtils.toString(response.getEntity());
			System.out.println("获取验证码：" + entity);

			String[] checkNum = entity.substring(entity.indexOf("(") + 1,
					entity.lastIndexOf(")")).replace("'", "").split(",");
			System.out.println(checkNum[0]);
			System.out.println(checkNum[1]);
			System.out.println(checkNum[2].trim());
			System.out.println(checkNum[2].trim().replace("\\x", ""));
			String pass = "";

			/******************** *加密密码 ***************************/
			// ScriptEngineManager manager = new ScriptEngineManager();
			// ScriptEngine engine = manager.getEngineByName("javascript");
			// // 读取js文件
			// FileReader reader = new FileReader(QQ_MD5);
			// engine.eval(reader);
			//
			// if (engine instanceof Invocable) {
			// Invocable invoke = (Invocable) engine;
			// // 调用preprocess方法，并传入两个参数密码和验证码
			// pass = invoke.invokeFunction("preprocess",
			// checkNum[2].trim(), PWD, checkNum[1].trim()).toString();
			// System.out.println("c = " + pass);
			// }
			// reader.close();

			pass = QQmd5.GetPassword(checkNum[2].trim(), PWD, checkNum[1]
					.trim());
			/************************* 登录 ****************************/
			get = new HttpGet(
					"http://ptlogin2.qq.com/login?ptlang=2052&u="
							+ USER
							+ "&p="
							+ pass
							+ "&verifycode="
							+ checkNum[1]
							+ "&low_login_enable=1&low_login_hour=720&aid=46000101&u1=http%3A%2F%2Ft.qq.com&ptredirect=1&h=1&from_ui=1&dumy=&fp=loginerroralert&action=1-2-11125&g=1&t=1&dummy=");
			get.setHeader("Connection", "keep-alive");
			get.setHeader("Host", "ptlogin2.qq.com");
			get.setHeader("Referer", "http://t.qq.com/?from=11");
			get
					.setHeader("User-Agent",
							"Mozilla/5.0 (Windows NT 5.1; rv:13.0) Gecko/20100101 Firefox/13.0");
			response = client.execute(get);
			entity = EntityUtils.toString(response.getEntity());
			System.out.println("登陆返回：" + entity);
			if (entity.indexOf("登录成功") > -1) {
				get = new HttpGet("http://t.qq.com");
				response = client.execute(get);
				entity = EntityUtils.toString(response.getEntity());
				Document doc = Jsoup.parse(entity);
				Element es = doc.getElementById("topNav1");
				String displayName = "";
				if (es != null) {
					Elements e = es.getElementsByTag("u");
					if (e != null && e.size() > 0)
						displayName = e.get(0).text();
				}
				System.out.println("\n\n登陆用户：" + displayName);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
		return client;
	}
}
