package com.bdsoft.bjsxt.util;

public class StringTest3 {

	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("I");
		sb.append(" love");
		System.out.println(sb.toString());
		sb.insert(2, "df");
		sb.delete(1, 3);
		sb.reverse();

		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.SIZE);

		String str = "1,2;3,4;4,5,2,5";
		String[] arr = str.split(";");
		int len = arr.length;
		double[][] d = new double[len][];
		for (int i = 0; i < len; i++) {
			String[] val = arr[i].split(",");
			d[i] = new double[val.length];
			for (int j = 0; j < val.length; j++) {				
				d[i][j] = Double.parseDouble(val[j]);
			}
		}
		for (int i = 0; i < d.length; i++) {
			for (int j = 0; j < d[i].length; j++) {
				System.out.print(d[i][j] + "  ");
			}
			System.out.println();
		}
	}

}
