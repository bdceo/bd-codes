package com.bdsoft.bdceo.dp.factory.nvwa;

public class Main {
 
	public static void main(String[] args) {
		NvwaFactory yin = new YinSheng();
		yin.genAnimal("雌性动物");

		NvwaFactory yang = new YangSheng();
		yang.genPerson("男人");
	}

}
