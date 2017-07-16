package com.bdsoft;

import com.bdsoft.utils.http.BDHttpParam;
import com.bdsoft.utils.http.BDHttpUtil;

public class RESTful {

	public static void main(String[] args) {
		
		String url = "http://alpha-www.gdfcx.net/member-app/v1/api/designers/20730531";
		
		BDHttpParam hp = BDHttpParam.init();
		hp.addHeader("hs_uid", "7db9447f-a024-4689-867b-1909ee16c04d");
		hp.addHeader("X-Token", "e2dd9c6f-6df6-43e9-9518-3be21c1c7a1d");
		
		String res = BDHttpUtil.sendGet(url, hp);
		System.out.println(res);
	}

}
