/**
 * EncodeOfAnswer.java
 * com.bdsoft.y2015.m08
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
*/
package com.bdsoft.y2015.m08;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 
 * @author	丁辰叶
 * @date	2015-8-30
 */
public class EncodeOfAnswer {

	/** 
	 * ASCII表中可见字符从!开始，偏移位值为33(Decimal) 
	 */
	static final char DBC_CHAR_START = 33; // 半角!  

	/** 
	 * ASCII表中可见字符到~结束，偏移位值为126(Decimal) 
	 */
	static final char DBC_CHAR_END = 126; // 半角~  

	/** 
	 * 全角对应于ASCII表的可见字符从！开始，偏移值为65281 
	 */
	static final char SBC_CHAR_START = 65281; // 全角！  

	/** 
	 * 全角对应于ASCII表的可见字符到～结束，偏移值为65374 
	 */
	static final char SBC_CHAR_END = 65374; // 全角～  

	/** 
	 * ASCII表中除空格外的可见字符与对应的全角字符的相对偏移 
	 */
	static final int CONVERT_STEP = 65248; // 全角半角转换间隔  

	/** 
	 * 全角空格的值，它没有遵从与ASCII的相对偏移，必须单独处理 
	 */
	static final char SBC_SPACE = 12288; // 全角空格 12288  

	/** 
	 * 半角空格的值，在ASCII中为32(Decimal) 
	 */
	static final char DBC_SPACE = ' '; // 半角空格  

	public static void main(String[] args) throws Exception{
		String xx = "";
		
		xx = "\\xF0\\x9F\\x98\\x83";// 哭
		xx = "\\xF0\\x9F\\x91\\x8c"; // ok		
		System.out.println(URLEncoder.encode(xx));
		System.out.println(URLDecoder.decode(xx));

		int i = (0xf0 & 0xff) + (0x9f & 0xff) + (0x98 & 0xff) + (0x84 & 0xff);

		System.out.println(i);
		System.out.println((char) (i-CONVERT_STEP));

		System.out.println((0xf0 & 0xff));
		System.out.println((0x9f & 0xff));
		System.out.println((0x98 & 0xff));
		System.out.println((0x84 & 0xff));

		StringBuilder buf = new StringBuilder();
		buf.append((char) ((0x84 & 0xff) - CONVERT_STEP));
		buf.append((char) ((0x98 & 0xff) - CONVERT_STEP));
		buf.append((char) ((0x9f & 0xff) - CONVERT_STEP));
		buf.append((char) ((0xf0 & 0xff) - CONVERT_STEP));

		String s = buf.toString();
		System.out.println(s);

	}

	public static String qj2bj(String src) {
		if (src == null) {
			return src;
		}
		StringBuilder buf = new StringBuilder(src.length());
		char[] ca = src.toCharArray();
		for (int i = 0; i < src.length(); i++) {
			if (ca[i] >= SBC_CHAR_START && ca[i] <= SBC_CHAR_END) { // 如果位于全角！到全角～区间内  
				buf.append((char) (ca[i] - CONVERT_STEP));
			} else if (ca[i] == SBC_SPACE) { // 如果是全角空格  
				buf.append(DBC_SPACE);
			} else { // 不处理全角空格，全角！到全角～区间外的字符  
				buf.append(ca[i]);
			}
		}
		return buf.toString();
	}

	public static String toStringHex(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			s = new String(baKeyword, "UTF-8");//UTF-16le:Not 
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}
}
