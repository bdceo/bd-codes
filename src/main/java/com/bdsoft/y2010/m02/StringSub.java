package com.bdsoft.y2010.m02;

public class StringSub {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String temp = "cs_2011_332";
		String deptId = temp
				.substring(temp.indexOf("_")+1, temp.lastIndexOf("_"));
		String userId = temp.substring(temp.lastIndexOf("_")+1, temp.length());
		System.out.println(deptId + " - " + userId);
	}

}
