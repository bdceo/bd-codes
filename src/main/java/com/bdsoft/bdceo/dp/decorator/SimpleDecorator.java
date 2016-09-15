package com.bdsoft.bdceo.dp.decorator;

public class SimpleDecorator {

	// 装饰模式:同适配器模式很像
	// 区别：适配器模式，适合主模块的扩展以达到扩展模块的自适应，一般继承实现单项适配较常用
	// 装饰模式：适合扩展模块的持有主模块的抽象引用，适应主模块的变化
	// 总结：两个模式的使用场景，在于那种模块的变化需要被适应
	public static void main(String[] args) {

		// IReader r = new FileReader();
		IReader r = new NetReader();

		Convert c = new Convert(r);
		c.convertFromReader();
	}

}

// 面向接口
interface IReader {
	void readFrom();
}

// 具体读取资源实现，主模块
class FileReader implements IReader {
	public void readFrom() {
		System.out.println("fileReader");
	}
}

class NetReader implements IReader {
	public void readFrom() {
		System.out.println("netReader");
	}
}

// 扩展模块，其中注入了主模块的接入
class Convert { // 装饰器
	
	private IReader reader;

	public Convert(IReader r) {
		this.reader = r;
	}

	public void convert() {
		System.out.println("convert");
	}

	public void convertFromReader() {
		reader.readFrom();// 读取
		this.convert();// 格式转换
	}
}
