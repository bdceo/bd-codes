package com.bdsoft.y2012.m10;

import java.util.HashMap;
import java.util.Map;

public class SMTPServer {

	private static Map<String, String> smtps = new HashMap<String, String>();

	static {
		smtps.put("tom.com", "smtp.tom.com");
		// smtps.put("hexun.com", "smtp.hexun.com");// 验证码
		// smtps.put("sogou.com", "mx.mail.sogou.com");// 太慢
		// smtps.put("sogou.com", "smtp.sogou.com");
		// smtps.put("sohu.com", "smtp.sohu.com");
		smtps.put("163.com", "smtp.163.com");
		smtps.put("126.com", "smtp.126.com");
		// smtps.put("21cn.com", "smtp.21cn.com");
		smtps.put("yeah.net", "smtp.yeah.net");
		// smtps.put("173.com", "smtp.173.com");
	}

	public static String getSmtp(String domain) {
		if (smtps.get(domain) == null) {
			return "";
		}
		return smtps.get(domain);
	}

}
