package com.bdsoft.y2009.m10;

public class PrintHanzi {

	// ���ַ��еĺ��ִ�ӡ��4
	public static void main(String[] args) {
		String s = "112444��59��888��dfe��ӡ222��4";
		for (int i = 0; i < s.length(); i++) {
			int n = (int) s.charAt(i);
			if (n >= 19968 && n <= 171941) {
				System.out.print(s.charAt(i));
			}
		}
	}
}