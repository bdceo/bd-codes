package com.bdsoft.bdceo.spring.mvc;

import java.util.List;

public interface IRateService {

	List getRatesToday();

	List getRatesByDate(String date);
}
