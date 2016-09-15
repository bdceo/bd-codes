package com.bdsoft.y2013.m10.log;

import org.apache.log4j.Logger;

public class SubLog {

	private static Logger log = Logger.getLogger(SubLog.class.getName());

	public void sub() {
		log.info("log4j is ok");
	}

}
