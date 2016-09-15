package com.bdsoft.bdceo.refactor.ch08;

/**
 * 以对象取代数组
 */
public class Demo05 {

	/**
	 * 重构前，数组的定义及使用方式
	 */
	private void howToUse() {
		// 定义数组及初始化
		String[] row = new String[3];
		row[0] = "bdceo";
		row[1] = "15";

		// 使用数组元素
		String name = row[0];
		int wins = Integer.parseInt(row[1]);
	}

	/**
	 * 第一步：生命数组，并提供对应索引的区设值函数
	 */
	static class PerformanceStep1 {
		// public String[] data = new String[3];
		private String[] data = new String[3];

		public String getName() {
			return data[0];
		}

		public void setName(String arg) {
			data[0] = arg;
		}

		public int getWins() {
			return Integer.parseInt(data[1]);
		}

		public void setWins(String arg) {
			data[1] = arg;
		}
	}

	/**
	 * 第二步，完全以对象属性字段的方式，替换原数组
	 */
	static class PerformanceStep2 {
		private String name;
		private int wins;

		public String getName() {
			return name;
		}

		public void setName(String n) {
			name = n;
		}

		public int getWins() {
			return wins;
		}

		public void setWins(int w) {
			wins = w;
		}
	}

}
