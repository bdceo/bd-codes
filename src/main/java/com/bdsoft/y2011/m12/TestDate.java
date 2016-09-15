package com.bdsoft.y2011.m12;

import java.util.Calendar;
import java.util.Date;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class TestDate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		
		System.out.println(calendar.get(Calendar.DAY_OF_WEEK)-1);
		
		System.out.println(new Date().toLocaleString()); 
		
		
		System.out.println(((new Date()).getTime()+"").toString().length());
		
		System.out.println((new Date()).getTime());
	}

}
