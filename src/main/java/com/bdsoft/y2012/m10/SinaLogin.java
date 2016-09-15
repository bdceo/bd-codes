package com.bdsoft.y2012.m10;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bdsoft.utils.Base64;
import com.bdsoft.utils.NetTool;
import com.bdsoft.utils.SHA1;
import com.google.gson.Gson;

public class SinaLogin {

	// 参考：http://www.douban.com/note/201767245/

	// 获取servertime 和nonce参数
	private static String PRELOGIN_URL = "http://login.sina.com.cn/sso/prelogin.php?entry=weibo&callback=sinaSSOController.preloginCallBack&client=ssologin.js(v1.3.18)";

	private static String LOGIN_URL = "http://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.3.18)";

	private static String USER = "bdcoo@qq.com";
	private static String PWD = "bdceo0517";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// String data = "name = ~a+3";
		// data = URLEncoder.encode(data, "utf-8");
		// data = data.replaceAll("\\+", "%20");
		// System.out.println(data);
		// System.exit(1);
		// String data = "this is a teat";
		// data = Base64.encode(data.getBytes());
		// System.out.println(data.substring(0, data.length() - 1));
		// System.exit(1);
		// String data = "a test string";
		// data = new SHA1().getDigestOfString(data.getBytes());
		// System.out.println(data);
		// System.exit(1);

		
		Gson g = new Gson();

		String user = getUser();
		StringBuffer sb = new StringBuffer(PRELOGIN_URL);
		sb.append("&su=" + user);
		sb.append("&_=" + new Date().getTime());
		// System.out.println(sb.toString());

		String src = NetTool.getTextContent(sb.toString(), "UTF-8");
		// System.out.println(src);
		src = src.substring(src.indexOf("{"), src.lastIndexOf("}") + 1);
		System.out.println(src);
		PreLogin preLogin = g.fromJson(src, PreLogin.class);

		Map<String, String> ps = new HashMap<String, String>();
		ps.put("su", user);
		ps.put("sp", getPwd(preLogin));
		ps.put("servertime", preLogin.getServertime());
		ps.put("nonce", preLogin.getNonce());
		
		ps.put("entry","weibo");
		ps.put("from","");
		ps.put("vsnf","1");
		ps.put("vsnval","");
		ps.put("gateway","1");
		ps.put("savestate","7");
		ps.put("useticket","1");
		ps.put("ssosimplelogin","1");
		ps.put("service","miniblog");
		ps.put("pwencode","wsse");
		ps.put("encoding","UTF-8");
		ps.put("returntype","META");

		// 发请求
		InputStream is = NetTool.sendPostAndEncode(LOGIN_URL, ps, "UTF-8");
		src = NetTool.getContentFromStream(is, "GBK");
		System.out.println(src);
	}

	public static String getPwd(PreLogin preLogin) {
		String pwd = new SHA1().getDigestOfString(PWD.getBytes());
		pwd = new SHA1().getDigestOfString(pwd.toLowerCase().getBytes());
		pwd = pwd.toLowerCase() + preLogin.getServertime()
				+ preLogin.getNonce();
		pwd = new SHA1().getDigestOfString(pwd.getBytes());
		return pwd;
	}

	public static String getUser() throws Exception {
		String user = URLEncoder.encode(USER, "UTF-8");
		user = user.replaceAll("\\+", "%20");
		user = Base64.encode(user.getBytes());
		user = user.substring(0, user.length() - 1);
		return user;
	}

}

class PreLogin {
	private String retcode;
	private String servertime;
	private String pcid;
	private String nonce;

	public PreLogin() {
	}

	public PreLogin(String retcode, String servertime, String pcid, String nonce) {
		super();
		this.retcode = retcode;
		this.servertime = servertime;
		this.pcid = pcid;
		this.nonce = nonce;
	}

	public String getRetcode() {
		return retcode;
	}

	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}

	public String getServertime() {
		return servertime;
	}

	public void setServertime(String servertime) {
		this.servertime = servertime;
	}

	public String getPcid() {
		return pcid;
	}

	public void setPcid(String pcid) {
		this.pcid = pcid;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

}
