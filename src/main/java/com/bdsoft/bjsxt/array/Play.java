package com.bdsoft.bjsxt.array;

public class Play {

	public static void main(String[] args) {
		boolean[] arr = new boolean[500];
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			arr[i] = true;
		}

		int leftCount = len;
		int countNum = 0;
		int index = 0;

		while (leftCount > 1) {
			if (arr[index] == true) {
				countNum++;
				if (countNum == 3) {
					countNum = 0;
					arr[index] = false;
					leftCount--;
				}
			}
			index++;
			if (index == len) {
				index = 0;
			}
		}

		for (int i = 0; i < len; i++) {
			if (arr[i] == true) {
				System.out.println("最后的那个人在：" + (i + 1));
			}
		}

	}
}