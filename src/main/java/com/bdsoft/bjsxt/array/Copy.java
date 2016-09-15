package com.bdsoft.bjsxt.array;

public class Copy {

	public static void main(String[] args) {
		int[] src = { 0, 11, 22, 33, 44, 55, 66, 77, 88, 99 };
		int[] dest = new int[6];
		System.arraycopy(src, 2, dest, 2, 4);
		for (int i = 0; i < dest.length; i++) {
			System.out.print(dest[i]+"    ");
		}
	}

}
