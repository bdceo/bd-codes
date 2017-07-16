package com.bdsoft.y2017.m03;

import java.net.URLEncoder;

import com.bdsoft.utils.http.BDHttpParam;
import com.bdsoft.utils.http.BDHttpUtil;

public class GDsms {

	public static void main(String[] args) throws Exception {
		// 221.179.180.158 , 221.179.172.68:8000
		String server = "http://221.179.172.68:8000";
		String url = server + "/QxtSms/QxtFirewall";

		String sms = "【居然之家】您的验证码是：880517";
		sms = URLEncoder.encode(sms, "GBK");

		BDHttpParam hp = BDHttpParam.init();
		hp.addCommon("OpenID", "jrjt");
		hp.addCommon("OperPass", "Nx7iVgCc");
		hp.addCommon("DesMobile", "13426479431");
		hp.addCommon("Content", sms);
		hp.addCommon("SendTime", "");
		hp.addCommon("ValidTime", "");
		hp.addCommon("AppendID", "");

		String res = BDHttpUtil.sendGet(url, hp);
		System.out.println(res);
	}

}
