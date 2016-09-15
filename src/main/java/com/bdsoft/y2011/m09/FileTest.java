package com.bdsoft.y2011.m09;

import java.io.File;
import java.io.RandomAccessFile;

public class FileTest {
 
	public static void main(String[] args) {
		String fileName="D:"+File.separator+"hello.txt";  
        File f=new File(fileName);  
        try {
			RandomAccessFile demo = new RandomAccessFile(f, "rw");
			demo.writeBytes("asdsad");
			demo.writeInt(12);
			demo.writeBoolean(true);
			demo.writeChar('A');
			demo.writeFloat(1.21f);
			demo.writeDouble(12.123);
			demo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
