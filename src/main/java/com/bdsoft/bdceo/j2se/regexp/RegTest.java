package com.bdsoft.bdceo.j2se.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegTest {

	/**
	 * 主要测试正则表达式的捕获与非捕获
	 * 
	 * 参考： http://mcj8089.iteye.com/blog/1183075
	 * http://blog.csdn.net/lovingprince/article/details/2774819
	 */
	public static void main(String[] args) {
		String text = "<textarea rows=\"20\" cols=\"70\">nexus maven repository index properties updating index central</textarea>";
		String regex = "<textarea.*?>.*?</textarea>";

		System.out.println("<textarea.*?>.*?</textarea>");
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(text);
		// 正常测试：验证html标记
		if (m.find()) {
			System.out.println(m.group());
		}

		// 只验证html标签之间的内容，利用捕获组
		System.out.println("\n(<textarea.*?>)(.*?)(</textarea>)");
		regex = "(<textarea.*?>)(.*?)(</textarea>)";
		p = Pattern.compile(regex);
		m = p.matcher(text);
		if (m.find()) {
			System.out.println(m.group(0));// <textarea rows="20"
											// cols="70">nexus maven repository
											// index properties updating index
											// central</textarea>
			System.out.println(m.group(1));// <textarea rows="20" cols="70">
			System.out.println(m.group(2));// nexus maven repository index
											// properties updating index central
			System.out.println(m.group(3));// </textarea>
		}

		// 非捕获组(?:regex)
		System.out.println("\n(?:<textarea.*?>)(.*?)(?:</textarea>)");
		regex = "(?:<textarea.*?>)(.*?)(?:</textarea>)";
		p = Pattern.compile(regex);
		m = p.matcher(text);
		if (m.find()) {
			System.out.println(m.group(0));// <textarea rows="20"
											// cols="70">nexus maven repository
											// index properties updating index
											// central</textarea>
			System.out.println(m.group(1));// nexus maven repository index
			// properties updating index central
		}

		// 非捕获组(?=regex)，仅当子表达式regex在 此位置的右侧匹配时才继续匹配
		System.out.println("\n非捕获组(?=regex)");
		text = "bdceo21";
		regex = "\\w+(?=\\d)";
		p = Pattern.compile(regex);
		m = p.matcher(text);
		System.out.println(m.find());

		// 非捕获组(?!regex)，仅当子表达式regex不在 此位置的右侧匹配时才继续匹配
		System.out.println("\n非捕获组(?!regex)");
		text = "b2";
		regex = "[a-z]+(?!\\d)";
		p = Pattern.compile(regex);
		m = p.matcher(text);
		System.out.println(m.find());

		// 非捕获组(?<=regex)，仅当子表达式regex在 此位置的左侧匹配时才继续匹配
		System.out.println("\n非捕获组(?<=regex)");
		text = "b2d2";// bdxxx will ok
		regex = "(?<=bd)\\w+";
		p = Pattern.compile(regex);
		m = p.matcher(text);
		System.out.println(m.find());

		// 非捕获组(?<!regex)，仅当子表达式regex不在此位置的左侧匹配时才继续匹配
		System.out.println("\n非捕获组(?<!regex)");
		text = "bdceo";// bdxxx will ok
		regex = "(?<!bd)ceo";
		p = Pattern.compile(regex);
		m = p.matcher(text);
		System.out.println(m.find());
	}

}
