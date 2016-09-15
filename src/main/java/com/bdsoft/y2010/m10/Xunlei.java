package com.bdsoft.y2010.m10;

import java.io.IOException;

public class Xunlei {

	// 迅雷笔试题
	public static void main(String[] args) {
		int k = 0;
		int j = 0;
		int w = 0;
		for (int i = 0; i < 5; i++) {
			if (++k > 2 && j++ > 2 && ++w > 2) {
				k++;
				++j;
				++w;
			}
		}
		System.out.println("k=" + k + "  " + "j=" + j + "  " + "w=" + w);
	}
}

interface IntTemp {
	public void read() throws IOException;

	public void write() throws Exception;
}

// 实现类抛出异常需要时父类的子类或不抛出
class ImpTemp implements IntTemp {
	// public void read() throws Exception {
	public void read() throws IOException {

	}

	public void write() {
	}
}
