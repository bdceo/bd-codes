package com.bdsoft.bdceo.spring.appfx;

public interface IFXNewsListener {

	public String[] getAvaliableNewsIds();

	public FXNewsBean getNewsByPK(String newsId);

	public void postProcessIfNecessary(String newsId);
}
