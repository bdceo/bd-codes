package com.bdsoft.y2012.m03;

import java.text.DecimalFormat;

public class TestDecimalFormat {

	public static void main(String[] ewStrings) throws Exception {
		DecimalFormat df = new DecimalFormat("0.00");
		String sp = df.format(new Double("1.1"));
		System.out.println(sp); 
	}
}
