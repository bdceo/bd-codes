package com.bdsoft.y2013.m10.log;

import org.apache.log4j.Logger;

public class Action {

	private static Logger log = Logger.getLogger(Action.class.getName());

	public Action() {
		log.debug("action init");
		new Service();
	}
}
