package com.bdsoft.y2016.m09;

import java.text.MessageFormat;

public class StringLocation {

	public static void main(String[] args) {
		String str = "git pull {0} {1}";
		MessageFormat mf = new MessageFormat(str);
		str = mf.format(new Object[] { "origin", "master" });
		System.out.println(str);
	}

}
