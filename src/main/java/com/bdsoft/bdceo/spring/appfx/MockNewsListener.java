package com.bdsoft.bdceo.spring.appfx;

public class MockNewsListener implements IFXNewsListener {

	@Override
	public String[] getAvaliableNewsIds() {
		try {
			throw new FXNewsRetrieveFailureException("抓取失败");
		} catch (FXNewsRetrieveFailureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public FXNewsBean getNewsByPK(String newsId) {
		// TODO Auto-generated atm stub
		return null;
	}

	@Override
	public void postProcessIfNecessary(String newsId) {
		// TODO Auto-generated atm stub

	}

}
