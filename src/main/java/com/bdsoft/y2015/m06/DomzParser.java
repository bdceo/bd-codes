package com.bdsoft.y2015.m06;

import java.util.regex.Pattern;

/**
 * 网友nutch问题测试
 * 
 * @author bdceo
 */
public class DomzParser {

	public static void main(String[] args) {
		
		String regExp = "^(Top/Arts|Top/Computers).*";
		Pattern topicPattern = Pattern.compile(regExp);
		
		String curSection = "Top/Arts/Genres/Science_Fiction_and_Fantasy/Fandom/Conventions_and_Organizations/Regional/United_States/Illinois"; // r:id
		
		System.out.println(topicPattern.matcher(curSection).matches());
		
	}

}
