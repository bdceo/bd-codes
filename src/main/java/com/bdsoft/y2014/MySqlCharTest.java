package com.bdsoft.y2014;

public class MySqlCharTest {

	// 中文，在utf-8字符集，一个汉字占3个字节
	// mysql，varchar和char类型字段，是按字符计算的，而非字节存储，
	// 所以在存储前，最好用字符串的length方法校验
	public static void main(String[] args) throws Exception {
		String name = "丁s";
		int len = name.getBytes("UTF-8").length;
		System.out.println("字节(U8)长度=" + len);

		len = name.length();
		System.out.println("字符长度=" + len);
		
		name = "中文，在utf-8字符集，一个汉字占3个字节";
		len = name.length();
		System.out.println(len);
		System.out.println(name.substring(0, 6));
	}

}
