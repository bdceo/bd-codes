package com.bdsoft.web;

import java.io.File;

import org.aspectj.util.FileUtil;

import com.bdsoft.utils.Lz4Compress;
import com.bdsoft.utils.http.BDHttpParam;
import com.bdsoft.utils.http.BDHttpUtil;

public class RestTest {
 
	public static void main(String[] args)  throws Exception{

		String url = "http://localhost:8080/juranhome/test/lz4";

		BDHttpParam pm = BDHttpParam.init();
		
		String path = "/home/dcy/tmp/bigjson";
		File file = new File(path);
		byte[] data = FileUtil.readAsByteArray(file);
		byte[] comp = Lz4Compress.compress(data);
		pm.addCommon("lz4", new String(comp,"utf-8"));

		String res = BDHttpUtil.sendPost(url, pm);
		System.out.println(res);
	}

}
