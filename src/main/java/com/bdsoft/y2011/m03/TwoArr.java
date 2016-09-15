package com.bdsoft.y2011.m03;

public class TwoArr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[][] twoArrs = new String[][] { { "1", "2", "3" },
				{ "a", "b", "c", "d" } };
		int[] nums = new int[3];
		int xlen = twoArrs.length;
		for (int i = 0; i < xlen; i++) {
			String[] oneArrs = twoArrs[i];
			int ylen = oneArrs.length;
			System.out.print("第 " + (i + 1) + " 行: ");
			for (int j = 0; j < ylen; j++) {
				System.out.print(oneArrs[j] + "  ");
			}
			System.out.println("");
		}
		intToByte(13223);
	}

	public static void intToByte(int n) {
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			b[i] = (byte) (n >> (24 - i * 8));
		}
		for (byte bt : b) {
			System.out.print(bt + "  ");
		}
	}

}
