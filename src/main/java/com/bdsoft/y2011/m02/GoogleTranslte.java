package com.bdsoft.y2011.m02;

public class GoogleTranslte {

	public static void main(String[] args) {
		String chanceol = "http://translate.google.cn/translate?"
				+ "prev=hp&hl=zh-CN" + "&js=y&u=http%3A%2F%2Fwww.chanceol.com"
				+ "&sl=zh-CN&tl=en&history_state0=&swap=1";

		String baidu = "http://translate.google.cn/translate?"
				+ "hl=zh-CN&sl=zh-CN&tl=en" + "&u=http%3A%2F%2Fwww.baidu.com";

		String javaeye = "http://translate.google.cn/translate?"
				+ "hl=zh-CN&sl=zh-CN&tl=en"
				+ "&u=http%3A%2F%2Fwww.javaeye.com%2F";
		System.out.println(baidu);
	}
}
