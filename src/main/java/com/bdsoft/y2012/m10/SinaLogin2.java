package com.bdsoft.y2012.m10;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.bdsoft.utils.Base64;

/**
 * 模拟登陆基本实现
 * 
 * @author bdceo
 * 
 */
public class SinaLogin2 {

	// 参考：http://blog.csdn.net/wolfphantasms/article/details/7398260
	private static String USER = "cooperate@sohu.com";
	private static String PWD = "zzzzzz";
	private static String LOGIN_URL = "http://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.3.19)";
	private static String LOGIN_WEIBO = "http://weibo.com/ajaxlogin.php?framelogin=1&callback=parent.sinaSSOController.feedBackUrlCallBack";

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
			String data = getServerTime();
			String nonce = makeNonce(6);
			String su = encodeAccount(USER);
			String sp = new SinaSSOEncoder().encode(PWD, data, nonce);

			HttpPost httpPost = new HttpPost(LOGIN_URL);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("entry", "weibo"));
			nvps.add(new BasicNameValuePair("gateway", "1"));
			nvps.add(new BasicNameValuePair("from", ""));
			nvps.add(new BasicNameValuePair("savestate", "7"));
			nvps.add(new BasicNameValuePair("useticket", "1"));
			nvps.add(new BasicNameValuePair("ssosimplelogin", "1"));
			nvps.add(new BasicNameValuePair("su", su));
			nvps.add(new BasicNameValuePair("service", "miniblog"));
			nvps.add(new BasicNameValuePair("servertime", data));
			nvps.add(new BasicNameValuePair("nonce", nonce));
			nvps.add(new BasicNameValuePair("pwencode", "wsse"));
			nvps.add(new BasicNameValuePair("sp", sp));
			nvps.add(new BasicNameValuePair("url", LOGIN_WEIBO));
			nvps.add(new BasicNameValuePair("returntype", "META"));
			nvps.add(new BasicNameValuePair("encoding", "UTF-8"));
			nvps.add(new BasicNameValuePair("vsnval", ""));

			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

			HttpResponse response = client.execute(httpPost);
			String entity = EntityUtils.toString(response.getEntity());
			System.out.println(entity);
			if (entity.indexOf("retcode=0") == -1) {
				System.out.println("登陆失败");
				return null;
			}
			String url = entity.substring(entity
					.indexOf("http://weibo.com/ajaxlogin.php?"), entity
					.indexOf("code=0") + 6);
			System.out.println("url=" + url);

			// 获取到实际url进行连接
			HttpGet httpGet = new HttpGet(url);
			response = client.execute(httpGet);
			entity = EntityUtils.toString(response.getEntity());
			// System.out.println(entity);
			entity = entity.substring(entity.indexOf("userdomain") + 13, entity
					.lastIndexOf("\""));
			System.out.println("userdomain=" + entity);

			List<Cookie> cs = client.getCookieStore().getCookies();
			for (Cookie c : cs) {
				System.out.println(c.getDomain() + ":" + c.getName() + "="
						+ c.getValue());
			}

			String test = "http://s.weibo.com/user/hrd&page=4";
			httpGet = new HttpGet(test);
			response = client.execute(httpGet);
			entity = EntityUtils.toString(response.getEntity());
			// System.out.println(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return client;
	}

	private static String encodeAccount(String account) {
		String userName = "";
		try {
			userName = Base64.encode(URLEncoder.encode(account, "UTF-8")
					.getBytes());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return userName;
	}

	private static String makeNonce(int len) {
		String x = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String str = "";
		for (int i = 0; i < len; i++) {
			str += x.charAt((int) (Math.ceil(Math.random() * 1000000) % x
					.length()));
		}
		return str;
	}

	private static String getServerTime() {
		long servertime = new Date().getTime() / 1000;
		return String.valueOf(servertime);
	}
}