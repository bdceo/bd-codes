package com.bdsoft.bjsxt.array;

public class SortObject {

	public static void main(String[] args) {
		Date[] days = new Date[5];
		days[0] = new Date(2006, 5, 4);
		days[1] = new Date(2006, 7, 4);
		days[2] = new Date(2008, 5, 4);
		days[3] = new Date(2004, 5, 9);
		days[4] = new Date(2004, 5, 4);
		bubbleSort(days);
		for (int i = 0; i < days.length; i++) {
			System.out.println(days[i]);
		}
	}

	public static Date[] bubbleSort(Date[] a) {
		int len = a.length;
		for (int i = len - 1; i >= 1; i--) {
			for (int j = 0; j <= i - 1; j++) {
				if (a[j].compare(a[j + 1]) > 0) {
					Date temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
		return a;
	}
}

class Date {
	int year, day, month;

	public Date(int y, int m, int d) {
		this.year = y;
		this.month = m;
		this.day = d;
	}

	public int compare(Date d) {
		return year > d.year ? 1 : year < d.year ? -1 : month > d.month ? 1
				: month < d.year ? -1 : day < d.day ? 1 : day > d.day ? -1 : 0;
	}

	public String toString() {
		return year + "年" + month + "月" + day + "日";
	}

}
