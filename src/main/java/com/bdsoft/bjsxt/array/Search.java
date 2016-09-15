package com.bdsoft.bjsxt.array;

public class Search {

	public static void main(String[] args) {
		int[] arr = { 1, 2, 5, 6, 8, 9, 10, 15, 23, 25, 45 };
		int i = 9;
		if (search(arr, i) > 0) {
			System.out.println(binarySearch(arr, i));
		} else {
			System.exit(-1);
		}
		System.out.println(5 / 2);
	}

	public static int search(int[] a, int num) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == num) {
				return 1;
			}
		}
		return -1;
	}

	public static int binarySearch(int[] a, int num) {
		if (a.length == 0) {
			return -1;
		}

		int start = 0;
		int end = a.length - 1;
		int mid = (start + end) / 2;

		while (end >= start) {
			if (num == a[mid]) {
				return mid;
			}
			if (num > a[mid]) {
				start = mid + 1;
			}
			if (num < a[mid]) {
				end = mid - 1;
			}
			mid = (start + end) / 2;
		}
		return -1;
	}

	public int sort_2(int[] a, int num) {

		int len = a.length;

		int start = 0;
		int end = len - 1;
		int mid = (start + end) / 2;

		while (end >= start) {
			int m = a[mid];

			if (m == num) {
				return mid;
			}
			if (m > num) {
				start = mid + 1;
			}
			if (m < num) {
				end = mid - 1;
			}
			mid = (start + end) / 2;
		}
		return -1;

	}
}
