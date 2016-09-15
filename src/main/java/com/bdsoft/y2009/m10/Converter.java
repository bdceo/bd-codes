package com.bdsoft.y2009.m10;

public class Converter {

	/*
	 * double��ǿ������ת��Ϊint��ֻ����������֣�
	 * 
	 * ��û��ִ������������� char���Ϳ����Զ�ת��Ϊint����
	 */
	public static void main(String[] args) {
		double above = 69.7, below = 33.6;
		System.out.println("above:" + above);
		System.out.println("below:" + below);

		System.out.println("(int)above:" + (int) above);
		System.out.println("(int)below:" + (int) below);
		System.out.println("(char)above:" + (char) above);
		System.out.println("(char)below:" + (char) below);

		printASCII();
	}

	/**
	 * ���������ַ��ASCII��
	 */
	public static void printASCII() {
		for (int i = 33; i < 65; i++) {
			System.out.print((char) i + "\t");
			if (i > 0 && i % 10 == 0) {
				System.out.println();
			}
		}
	}
}
