package com.bdsoft.y2012.m12;

import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bdsoft.utils.NetTool;

public class SinaWeiboScriptParser {

	public static String FILE_PATH = "d:/download/";
	public static String CHARSET = "UTF-8";

	public static void main(String[] args) throws Exception {
		File file = new File(FILE_PATH + "scripts.html");
		FileInputStream fi = new FileInputStream(file);
		String src = NetTool.getContentFromStream(fi, CHARSET);
//		System.out.println(src);

		String reg = "<script[^>]*?>[\\s\\S]*?<\\/script>";
		reg = "<script[^>]*?>.*?\"pid\":\"pl_weibo_feedlist\"[\\s\\S]*?<\\/script>";
		//reg = "<script[^>]*?>STK[\\s\\S]*?(?=(<\\/script>[\\s\\S]*?\"pid\":\"pl_weibo_feedlist\"))\\1+[\\s\\S]*?<\\/script>";
		System.out.println(reg);
		Pattern pt = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		Matcher m = pt.matcher(src); 
		int i = 1;
		while (m.find()) {
			System.out.println("\t哈哈" + i++);
			System.out.println(m.group());
		}
	}

}
