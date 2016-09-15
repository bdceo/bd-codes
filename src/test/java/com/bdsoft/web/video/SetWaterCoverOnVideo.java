/**
 * SetWaterCoverOnVideo.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.web.video;

import java.io.File;

import org.springframework.util.FileCopyUtils;

/**
 * 给视频添加水印
 * 
 * http://blog.csdn.net/tanghui2qinghong/article/details/11593379
 * 
 * @author bdceo
 * @date 2016-8-16 上午9:50:39
 * @version V1.0
 */
public class SetWaterCoverOnVideo {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String src = "d:/home/test.mp4";

	}

	// 备份视频
	static void backup() throws Exception {
		String src = "d:/home/mv/yyt-2650114.mp4";
		File sf = new File(src);
		System.out.println(sf.exists());

		File df = new File("d:/home/test.mp4");
		FileCopyUtils.copy(sf, df);
	}

}
