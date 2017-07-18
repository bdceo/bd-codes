//package com.bdsoft.y2012.weibo;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//import weibo4j.Friendships;
//import weibo4j.Oauth;
//import weibo4j.Users;
//import weibo4j.http.AccessToken;
//import weibo4j.model.User;
//import weibo4j.model.WeiboException;
//import weibo4j.util.BareBonesBrowserLaunch;
//
//public class AutoATFollowers {
//
//	public static void main(String[] args) throws WeiboException, IOException {
//		Oauth oauth = new Oauth();
//		BareBonesBrowserLaunch.openURL(oauth.authorize("code"));
//		System.out.println(oauth.authorize("code"));
//		System.out.print("Hit enter when it's done.[Enter]:");
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String code = br.readLine();
//		System.out.println("code: " + code);
//		try {
//			AccessToken at = oauth.getAccessTokenByCode(code);
//			System.out.println(at);
//
//			// 获取用户信息
//			Users um = new Users();
//			um.client.setToken(at.getAccessToken());
//			User user = um.showUserById(at.getUid());
//
//			// 获取粉丝列表,50
//			Friendships fm = new Friendships();
//			fm.client.setToken(at.getAccessToken());
//			List<User> us = fm.getFollowersByName(user.getScreenName())
//					.getUsers();
//			// 随机获取5个粉丝
//			int a = 5;
//			if (a > us.size()) {
//				a = us.size();
//			}
//			List<User> tmp = new ArrayList<User>();
//			for (int i = 0; i < a; i++) {
//				tmp.add(us.get(new Random().nextInt(us.size())));
//			}
//			for(User u : tmp){
//				System.out.println(u.toString());
//			}
//
//		} catch (WeiboException e) {
//			if (401 == e.getStatusCode()) {
//				System.out.println("Unable to get the access token.");
//			} else {
//				e.printStackTrace();
//			}
//		}
//	}
//
//}
