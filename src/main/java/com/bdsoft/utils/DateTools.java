/**
 * DateTools.java
 * com.bdsoft.utils
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
*/
package com.bdsoft.utils;

import java.net.URLDecoder;
import java.util.Date;

/**
 * 
 * @author	丁辰叶
 * @date	2015-12-1
 */
public class DateTools {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		String st = "1448957961738";
		Date t = new Date(Long.parseLong(st));
		System.out.println(t.toLocaleString());
		
		String enc = "_ck15120116192117432718271997133%7C10001300%7C%7C%7C";
		System.out.println(URLDecoder.decode(enc));
	}

}
