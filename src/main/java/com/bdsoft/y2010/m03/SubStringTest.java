package com.bdsoft.y2010.m03;

public class SubStringTest {
	public static void main(String[] sdf){
		String str="110-3,220-4,123-9";
		String[] ss=str.split(",");
		String temp=ss[0];
		System.out.println(temp.substring(0,temp.lastIndexOf("-")));
		System.out.println(temp.substring(temp.lastIndexOf("-")+1,temp.length()));
	}
}
