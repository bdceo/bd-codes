package com.bdsoft.bdceo.spring.appfx;

import org.springframework.stereotype.Component;

@Component
public class DowJonesNewsListener implements IFXNewsListener, PasswordDecodable {

	private String password;

	@Override
	public String[] getAvaliableNewsIds() {
		// TODO Auto-generated method stub
		System.out.println("DowJonesNewsListener-->getAvaliableNewsIds");
		String[] ids = new String[] { "1" };
		return ids;
	}

	@Override
	public FXNewsBean getNewsByPK(String newsId) {
		// TODO Auto-generated method stub
		System.out.println("DowJonesNewsListener-->getNewsByPK");
		return null;
	}

	@Override
	public void postProcessIfNecessary(String newsId) {
		// TODO Auto-generated method stub
		System.out.println("DowJonesNewsListener-->postProcessIfNecessary");

	}

	@Override
	public String getEncodedPassword() {
		System.out
				.println("DowJonesNewsListener-->getEncodedPassword()");
		return this.password;
	}

	@Override
	public void setDecodedPassword(String password) {
		this.password = password;
	}

}
