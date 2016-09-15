package com.bdsoft.y2016.m01;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;

import org.apache.poi.util.IOUtils;

import com.bdsoft.utils.MD5Encode;
import com.bdsoft.utils.NetTool;

/**
 * 下载
 * 
 * @author 丁辰叶
 * 
 */
public class Download {

	/**
	 * http://v.yinyuetai.com/video/h5/2509226?f=BFY-CNXH-list-TC-1-36
	 * H5版下载
	 * @param args
	 * @throws Exception
	 */
	
	public static void main(String[] args) throws Exception {
		String mp4Url = "http://s.yytcdn.com/swf/common/mvplayer.swf";
		String storeName = "d:/download/media/mvplayer.swf";
		// download(mp4Url, storeName);

		String vid = "2532217";
		String ver = "1.8.4.1";
		// String t = "" + (new Date().getTime() / 300000);
		String t = "4863542";
		System.out.println("t=" + t);

		String sc = ":" + vid + ":" + t + ":" + ver;
		String d5 = MD5Encode.encode(sc);
		System.out.println(d5);
		d5 = MD5Encode.encode(d5 + sc);
		System.out.println(d5);
		System.out.println("17f151ed294f5a5c3859725d2bda8b70");

		// http://www.yinyuetai.com/main/get-mv-info?flex=true&sc=12a0575e9a7eab081f45b9ca97fa89dd&t=4863542&v=1.8.4.1&videoId=2532217
		String mvInfoUrl = "http://www.yinyuetai.com/main/get-mv-info?flex=true&sc=#SC#&t=#T#&v=1.8.4.1&videoId=#VID#";
		mvInfoUrl = mvInfoUrl.replaceAll("#SC#", sc).replaceAll("#T#", t)
				.replaceAll("#VID#", vid);
		System.out.println(mvInfoUrl);
		System.exit(1);

		String url = "http://api.yinyuetai.com/mv/secret-key";
		// String html = NetTool.getTextContent(url, "utf-8");
		String html = new String(NetTool.readStream(NetTool
				.getContentStreamByPost(url, "utf-8")));
		// String html =
		// NetTool.getContentFromStream(NetTool.getContentStreamByPost(url,
		// "utf-8"), "utf-8");
		System.out.println(html);

		// FileUtil.writeFile("d:/download/media/info", html, true);

	}

	public static void download(String from, String to) {
		InputStream in = null;
		OutputStream out = null;
		try {
			URL apdf = new URL(from);
			in = apdf.openStream();
			System.out.println("开始下载：" + from);
			out = new FileOutputStream(new File(to));
			IOUtils.copy(in, out);
			out.flush();
			System.out.println("下载完成：" + to);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
