package com.bdsoft.y2015;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CloneTest {

	// java中对象克隆机制测试
	// 参考：http://www.cnblogs.com/yxnchinahlj/archive/2010/09/20/1831615.html
	public static void main(String[] args) throws Exception {
		Test t1 = new Test(4, "bdceo");
		t1.ref = new Ref(10);
		System.out.println(t1);

		System.out.println("---------Clone--------");

		Test t2 = (Test) t1.clone();
		// 因为对象已克隆，所以在此修改属性值不会影响源对象t1
		t2.id = 3;
		t2.name = "bdcfo";
		t2.ref.pk = 43;

		System.out.println(t2);

		System.out.println("---------DeepClone--------");
		Test t3 = (Test) t2.deepClone();
		t3.id = 9;
		t3.name = "bdcto";
		t3.ref.pk = 12;
		System.out.println(t3);
	}

}

class Test implements Cloneable, Serializable {

	private static final long serialVersionUID = -8060775378932019949L;

	int id;
	String name;
	Ref ref; // 引用类型

	public Test(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", name=" + name + ", ref=" + ref + "]@"
				+ this.hashCode();
	}

	// 借助Cloneable 接口实现clone方法，实现对象克隆
	protected Object clone() throws CloneNotSupportedException {
		Test o = null;
		try {
			// 模拟对象不克隆
			// o = this;

			// 模拟对象浅克隆，只是复制基本数据类型
			// o = (Test) super.clone();

			// 深克隆，对象中的引用类型变量同时被克隆复制
			o = (Test) super.clone();
			o.ref = (Ref) ref.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	// 列用串行化机制，通过类的序列化，实现对象的深克隆
	public Object deepClone() throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream oout = new ObjectOutputStream(out);
		oout.writeObject(this);// 序列化到内存

		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		ObjectInputStream iin = new ObjectInputStream(in);
		return (iin.readObject());// 从内存中反序列化对象
	}

}

class Ref implements Cloneable, Serializable {

	private static final long serialVersionUID = -5752617791037887813L;

	int pk;

	public Ref(int pk) {
		this.pk = pk;
	}

	@Override
	public String toString() {
		return "Ref [pk=" + pk + "]@" + this.hashCode();
	}

	protected Object clone() throws CloneNotSupportedException {
		Ref o = null;
		try {
			o = (Ref) super.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

}