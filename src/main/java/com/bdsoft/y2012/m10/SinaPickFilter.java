package com.bdsoft.y2012.m10;

public class SinaPickFilter {

	private static String[] HUNTER_KEYS = new String[] { "猎头" };
	private static String[] HR_KEYS = new String[] { "" };

	public boolean isHunter(String con) {
		boolean flag = false;
		for (String key : HUNTER_KEYS) {
			if (con.contains(key)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	private static SinaPickFilter spf = new SinaPickFilter();

	public static SinaPickFilter getInst() {
		if (spf == null) {
			spf = new SinaPickFilter();
		}
		return spf;
	}

	private SinaPickFilter() {
	}
}