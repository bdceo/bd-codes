package com.bdsoft.bdceo.spring.mvc;

import java.util.List;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class RateController {

/*extends AbstractController {

	private IRateService rateService;
	private String viewName;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List list = rateService.getRatesToday();

		String date = ServletRequestUtils.getStringParameter(request,
				"tradeDate");
		rateService.getRatesByDate(date);

		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("data", list);
		return mav;
	}

	public IRateService getRateService() {
		return rateService;
	}

	public void setRateService(IRateService rateService) {
		this.rateService = rateService;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
*/
}
