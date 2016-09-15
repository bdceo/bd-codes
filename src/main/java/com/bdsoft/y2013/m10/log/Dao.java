package com.bdsoft.y2013.m10.log;

import org.apache.log4j.Logger;

public class Dao {

	private static Logger log = Logger.getLogger(Dao.class.getName());

	public Dao() {
		log.warn("dao init");
	} 

}
