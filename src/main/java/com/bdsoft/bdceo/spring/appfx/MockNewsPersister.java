package com.bdsoft.bdceo.spring.appfx;

public class MockNewsPersister implements IFXNewsPersister {

	private FXNewsBean newsBean;

	@Override
	public void persistNews(FXNewsBean newsBean) {
		persistNews();
	}

	public void persistNews() {
		System.out.println("persist bean is :" + getNewsBean());
	}

	public FXNewsBean getNewsBean() {
		return newsBean;
	}

	public void setNewsBean(FXNewsBean newsBean) {
		this.newsBean = newsBean;
	}

}
