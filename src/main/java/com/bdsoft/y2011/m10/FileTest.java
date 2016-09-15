package com.bdsoft.y2011.m10;

import java.io.File;

public class FileTest {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		
		File file = new File("d:/qr1.gif");
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getCanonicalPath());

	}

}
