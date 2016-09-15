package com.bdsoft.y2010.m01;

public class EncodTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		String str="æ¥è-æ¨¡çææ";
		System.out.println(str);
		
		String after=java.net.URLDecoder.decode(str,"utf-8");
		System.out.println(after);
		
		after=new String(str.getBytes("ISO8859-1"),"UTF-8");
		System.out.println(after);		

	}
}