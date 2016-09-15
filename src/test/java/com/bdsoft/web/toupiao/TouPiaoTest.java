package com.bdsoft.web.toupiao;

import java.util.Date;

public class TouPiaoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		long ts = 1471100784692L;

		Date dt = new Date(ts);
		System.out.println(dt.toLocaleString());
		
	}

}
