package com.bdsoft.y2013.m10;

import java.io.File;

public class DiskCheck {

	private static File[] space = File.listRoots();

	static {
	}

	public static void main(String[] args) {
		for (File f : space) {
			System.out.println(f.getPath());
			long total = f.getTotalSpace() / 1024 / 1024 / 1024;
			long uesed = f.getUsableSpace() / 1024 / 1024 / 1024;
			long free = f.getFreeSpace() / 1024 / 1024 / 1024;
			System.out.println("总大小：" + total);
			System.out.println("已使用：" + uesed);
			System.out.println("可用：" + free);
		}
	}

	public static long getFree(String name) {
		long free = 0L;
		for (File f : space) {
			String path = f.getPath().toLowerCase();
			if (path.contains(name.toLowerCase())) {
				free = f.getFreeSpace() / 1024 / 1024 / 1024;
				System.out.println(name + " 盘，可用空间：" + free);
				break;
			}
		}
		return free;
	}

}
