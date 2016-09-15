package com.bdsoft.y2010.m04;

public class ReplaceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) { 
		String str="action.do?method=add&bid=$id";
		str=str.replace("$id", "001");
		System.out.println(str);

	}

}
