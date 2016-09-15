package com.bdsoft.y2011.m03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class ReadTxt {

	public static void main(String[] args) throws Exception {
		String path = "c:/tmp.txt";
		File file = new File(path);
		Reader reader = new FileReader(file);
		BufferedReader bufferReader = new BufferedReader(reader);
		String line = null;
		while ((line = bufferReader.readLine()) != null) {
			System.out.println(line);
		}
	}

}
