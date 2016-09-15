package com.bdsoft.bdceo.spring.appfx;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

@Component
public class DowJonesNewsPersister implements IFXNewsPersister,
		BeanFactoryAware {

	// BeanFactoryAware接口，容器自动注入
	private BeanFactory beanFactory;

	@Override
	public void setBeanFactory(BeanFactory bf) throws BeansException {
		this.beanFactory = bf;
	}

	@Override
	public void persistNews(FXNewsBean newsBean) {
		System.out.println("DowJonesNewsPersister-->persistNews with param");
	}

	public void persistNews() {
		System.out.println("DowJonesNewsPersister-->persistNews:"
				+ getNewsBean());
	}

	public FXNewsBean getNewsBean() {
		return (FXNewsBean) beanFactory.getBean("newsBean");
	}

}
