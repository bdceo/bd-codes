package com.bdsoft.bdceo.j2se.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailSpider {

	public static void main(String[] args) {
		String line = "bdceo303@dd.com";
		 line = "bdce_o303@dd.com";
		 line = "bdce_o3.3@dd.com";
		 line = "bdceo_-33@dd.com.cn";

		 // \w 代表：[0-9a-zA-Z_]
		String reg = "[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(line);

		System.out.println("邮箱：" + line + "\n合格：" + m.matches());
	}
}
