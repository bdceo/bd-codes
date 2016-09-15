package com.bdsoft.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class ManDeploy {

	private static String SRC_PATH = "D:/download/fetch/";
	private static String APP_PATH = "D:/download/app/";

	// 手动热部署
	public static void main(String[] args) {
		File src = new File(SRC_PATH);
		File app = new File(APP_PATH);
		if (!src.exists()) {
			System.out.println("源文件目录不存在 --》 " + SRC_PATH);
			return;
		}
		if (!app.exists()) {
			System.out.println("部署文件目录不存在 --》 " + APP_PATH);
			return;
		}
		FileChannel srcChannel = null;
		FileChannel appChannel = null;
		try {
			srcChannel = new FileInputStream(src).getChannel();
			appChannel = new FileOutputStream(app).getChannel();
			long srcSize = srcChannel.size();
			System.out.println("源文件大小 = " + srcSize);

			srcChannel.transferTo(0, srcSize, appChannel);

			System.out.println("拷贝完毕");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (srcChannel != null) {
					srcChannel.close();
				}
				if (appChannel != null) {
					appChannel.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
