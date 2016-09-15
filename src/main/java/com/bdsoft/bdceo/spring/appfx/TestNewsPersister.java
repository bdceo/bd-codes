package com.bdsoft.bdceo.spring.appfx;

import org.springframework.beans.factory.ObjectFactory;

public class TestNewsPersister implements IFXNewsPersister {

	// ObjectFactoryCreatingFactoryBean接口，通过FactoryBean实现与容器的交互
	private ObjectFactory newsBeanFactory;

	public FXNewsBean getNewsBean() {
		return (FXNewsBean) newsBeanFactory.getObject();
	}

	public void setNewsBeanFactory(ObjectFactory objectFactory) {
		this.newsBeanFactory = objectFactory;
	}

	@Override
	public void persistNews(FXNewsBean newsBean) {
		System.out.println("TestNewsPersister-->persistNews with param");
	}

	@Override
	public void persistNews() {
		System.out.println("TestNewsPersister-->persistNews:" + getNewsBean());
	}

}
