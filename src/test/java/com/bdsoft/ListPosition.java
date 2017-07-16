package com.bdsoft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListPosition {

	public static void main(String[] args) {

		List<Work> list = new ArrayList<Work>();
		list.add(new Work("133", "bdcxo"));
		list.add(new Work("134", "bdceo"));
		list.add(new Work("135", "bdcto"));
		list.add(new Work("136", "bdcfo"));
		list.add(new Work("137", "bdcpo"));
		list.add(new Work("138", "bdcio"));
		list.add(new Work("139", "bdcko"));

		Map<String, Integer> postion = new HashMap<>();
		list.forEach(item -> {
			postion.put(item.getMobile(), list.indexOf(item));
		});

		String mobile = "136";
		int start = postion.get(mobile);

		for (int i = start; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

}

class Work {

	String mobile;
	String name;

	public Work(String m, String n) {
		this.mobile = m;
		this.name = n;
	}

	@Override
	public String toString() {
		return "Work [mobile=" + mobile + ", name=" + name + "]";
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}