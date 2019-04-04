package com.bdsoft.y2016;

import java.util.List;

import com.google.common.collect.Lists;

public class TestFilter {

	public static void main(String[] args) {
		
		List<String> uris = Lists.newArrayList();
		uris.add("/juranhome/images/userCenter/step005.png");
		uris.add("/juranhome/images/global/wen.jpg");
		uris.add("/juranhome/js/index/qrcode.js");
		uris.add("juranhome/css/public/date-time/bootstrap-datetimepicker.min.css");
		uris.add("/juranhome/myOrder/myNeedsList");
		uris.add("/juranhome-1.0.0-SNAPSHOT/angularjs/js/angular-route.min.js.map");
		
		
		String uri = "/juranhome-1.0.0-SNAPSHOT/designs";
		String reg = ".*\\.(png|jpg|gif|ico|ttf|svg|eot|js|map|css|json|mp4|woff|woff2|apk|htc)$";
		System.out.println(uri.matches(reg));

		for(String u : uris){
			System.out.println(String.format("check: %s\t res =%b", u, u.matches(reg)));
		}
	}

}
