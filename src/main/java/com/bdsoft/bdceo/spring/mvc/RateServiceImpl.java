package com.bdsoft.bdceo.spring.mvc;

import java.util.List;

public class RateServiceImpl implements IRateService {

	@Override
	public List getRatesToday() {
		System.out.println("RateServiceImpl-->getRatesToday");
		return null;
	}

	@Override
	public List getRatesByDate(String date) {
		System.out.println("RateServiceImpl-->getRatesByDate:" + date);
		return null;
	}

}
