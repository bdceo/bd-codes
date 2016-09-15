package com.bdsoft.gkh.singleton;

class FileOpe {

	private static FileOpe fo = null;

	// 构造函数私有化
	private FileOpe() {
		System.out.println("构造函数");
	}

	// 线程同步安全
	public static synchronized FileOpe getInstance() {
		if (fo == null) {
			fo = new FileOpe();
		}
		return fo;
	}

	public void createFile() {
		System.out.println("创建文件");
	}
}

// class Conf {
// public static FileOpe fo = new FileOpe();
// }

public class Singleton1 {

	public static void main(String[] args) {
		FileOpe fo1 = FileOpe.getInstance();
		fo1.createFile();
		// 1000行代码
		// FileOpe fo2 = new FileOpe();
		// FileOpe fo2 = fo1;
		FileOpe fo2 = FileOpe.getInstance();
		fo2.createFile();
	}
}
