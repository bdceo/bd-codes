package com.bdsoft.bdceo.spring.appfx.proxy;

public class SubjectImp implements ISubject {

	@Override
	public String request() {
		System.out.println(this.getClass().getCanonicalName() + "-->request");
		return null;
	}

}
