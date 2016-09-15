package com.bdsoft.y2013.m07;

import java.io.InputStream;

import com.bdsoft.utils.FileUtil;
import com.bdsoft.utils.NetTool;

public class XinliFM {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int start = 1;
		int end = 507;
		String encode = "utf-8";
		String rep = "#N";
		String base = "http://fm.xinli001.com/" + rep + "/";
		String down = "http://fm.xinli001.com/" + rep + "/download/";
		String path = "d:/download/media/";
		for (int i = start; i <= end; i++) {
			// 收听页面
			String url = base.replaceAll(rep, "" + i);
			System.out.println("url --> "+url);
			try {
				String src = NetTool.getTextContent(url, encode);
				// 下载地址
				url = down.replaceAll(rep, "" + i);
				InputStream in = NetTool.getContentStreamByPost(url, encode);
				String file = path + i + ".mp3";
				FileUtil.writeFile(file, in, true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
