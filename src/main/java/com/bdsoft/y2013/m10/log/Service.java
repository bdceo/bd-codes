package com.bdsoft.y2013.m10.log;

import org.apache.log4j.Logger;

public class Service {

	private static Logger log = Logger.getLogger(Service.class.getName());

	public Service() {
		log.info("service init");
		new Dao();
	}

}
