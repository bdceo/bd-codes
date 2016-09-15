package com.bdsoft.y2012.m10;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailParser {
	
	public static void main(String[] args) {
		String[] rep = new String[] { "#", " # ", "（at）", "(at)", "（AT）",
				"(AT)", "at", " at ", "AT", " AT " };
		String mail = "电话：020-81897268 QQ：932351980 邮箱：jack.uswebuy（at）gmail.com 谢谢！";
		mail.replaceAll("", "");

	}

	private static String pickEmail(String content) {
		Pattern p = Pattern.compile("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+");
		Matcher m = p.matcher(content);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			sb.append(m.group());
			sb.append("\n");
		}
		return sb.toString();
	}
}
