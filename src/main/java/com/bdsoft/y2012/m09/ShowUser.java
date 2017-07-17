//package com.bdsoft.y2012.m09;
//
//import org.apache.commons.logging.Log;
//
//import weibo4j.Users;
//import weibo4j.model.User;
//import weibo4j.model.WeiboException;
//
//public class ShowUser {
//	public static void main(String[] args) {
//		String access_token = "2.00nZOI1CpBbuBD7d7a195bec0lv59m";
//		String uid = "2129755043";
//		Users um = new Users();
//		um.client.setToken(access_token);
//		try {
//			User user = um.showUserById(uid);
//			System.out.println(user.toString());
//		} catch (WeiboException e) {
//			e.printStackTrace();
//		}
//	}
//}
