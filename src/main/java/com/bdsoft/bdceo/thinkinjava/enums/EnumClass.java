/**
 * EnumClass.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.enums;

import java.text.DateFormat;
import java.util.Date;

/**
 * 19.1--基本Enum特性
 * 
 * @author bdceo
 * @date 2016-12-29 上午11:13:40
 * @version V1.0
 */
public class EnumClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (Language lan : Language.values()) {
			System.out.println(lan.ordinal());
			System.out.println(lan.compareTo(Language.JAVA));
			System.out.println(lan.equals(Language.JAVA));
			System.out.println(lan == Language.JAVA);
			System.out.println(lan.getDeclaringClass());
			System.out.println(lan.name());
			System.out.println("------------------------");
		}

		// 反向查找enum中是否包含指定值，不包含则报出异常
		for (String str : "JAVA PYTHON GROOVY RUBY".split(" ")) {
			Language lan = Enum.valueOf(Language.class, str);
			System.out.println(lan);
		}

		// 通过class类的getEnumConstants方法获取所有枚举实例
		System.out.println("-------------------");
		for (Enum<Language> en : Language.JAVA.getClass().getEnumConstants()) {
			System.out.println(en);
		}

		System.out.println("---------随机----------");
		for (int i = 0; i < 10; i++) {
			System.out.println(Enums.random(Language.class));
		}

		System.out.println("---------枚举的分类组织----------");
		for (int i = 0; i < 5; i++) {
			SecurityCategory cat = Enums.random(SecurityCategory.class);
			System.out.println(cat + ">" + cat.randomSelection());
		}


		System.out.println("---------定义常量方法----------");
		for(ConstantSpecialMethod en : ConstantSpecialMethod.values()){
			System.out.println(en.getInfo());
		}
		 
		
		Enum.valueOf(Language.class, "C@".toUpperCase());
	}

}

enum Language {

	JAVA("java"), PYTHON("python"), GROOVY("groovy"), RUBY("ruby");

	private String info;

	Language(String info) {
		this.info = info;
	}

	String getInfo() {
		return this.info;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.ordinal()).append(">").append(this.name()).append(":").append(this.info);
		return sb.toString();
	}
}

enum SecurityCategory {

	STOCK(Security.Stock.class), BOND(Security.Bond.class);

	Security[] values;

	SecurityCategory(Class<? extends Security> kind) {
		values = kind.getEnumConstants();
	}

	interface Security {
		enum Stock implements Security {
			SHORT, LONG, MARGIN
		}

		enum Bond implements Security {
			MUNICIPAL, JUNK
		}
	}

	public Security randomSelection() {
		return Enums.random(values);
	}

}

enum ConstantSpecialMethod {
	CLASSPATH {
		String getInfo() {
			return System.getenv("JAVA_HOME");
		}
	},
	DATE_TIME {
		String getInfo() {
			return DateFormat.getDateInstance().format(new Date());
		}
	},
	VERSION {
		String getInfo() {
			return System.getProperty("java.version");
		}
	};

	abstract String getInfo();

}
