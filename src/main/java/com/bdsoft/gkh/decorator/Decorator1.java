 package com.bdsoft.gkh.decorator;

import com.bdsoft.gkh.decorator.Convert;
import com.bdsoft.gkh.decorator.IRead;
import com.bdsoft.gkh.decorator.ReadStrFromFile;
import com.bdsoft.gkh.decorator.ReadStrFromNet;

/*
 * 单向适配器模式是在主模块上接入一个扩展模块，用继承法：
 * 优势：能够很好的应付扩展模块的切换
 * 缺陷：扩展模块无法很好的应付主模块的切换
 * 问题类比：Convert为ReadStrFromFile服务，以后又要为其他类服务，
 * 用单向适配器模式，就必须为其他类编写子类，很麻烦
 * */
//装饰模式要点：主模块接口注入扩展模块
interface IRead {
	public abstract void read();
}

class ReadStrFromFile implements IRead {// 主模块
	public void read() {
		System.out.println("从文件读取字符串");
	}
}

class ReadStrFromNet implements IRead {// 主模块
	public void read() {
		System.out.println("从网络读取字符串");
	}
}

// 装饰模式
class Convert {// 扩展模块,可以用Spring装配，可以为任何实现了IRead接口的类服务
	private IRead iread;

	public Convert(IRead iread) {
		this.iread = iread;
	}

	public void convert() {
		iread.read();
		System.out.println("转大写字母");
	}
}

// 适配器模式：
class SubReadStrFromFile extends ReadStrFromFile {
	private Convert convert;

	public SubReadStrFromFile(Convert con) {
		this.convert = con;
	}

	public void read() {
		super.read();
		convert.convert();
	}
}

public class Decorator1 {
	public static void main(String[] args) {
		// SubReadStrFromFile srsff = new SubReadStrFromFile(new Convert());
		// srsff.read();

		Convert convert = new Convert(new ReadStrFromNet());
		convert.convert();
	}
}
