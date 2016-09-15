package com.bdsoft.y2011.m05;

public class AyyarCopy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] src = { 103, 1, 6, 1, 0, 0, 3, -56, 11, 10, 41, 5, 11 };
		int[] dest = new int[9];
		System.arraycopy(src, 4, dest, 0, src.length);
		for (int i = 0; i < dest.length; i++) {
			System.out.println(dest[i]);
		}

	}

}
