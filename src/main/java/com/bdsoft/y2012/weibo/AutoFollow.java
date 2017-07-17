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
//public class AutoFollow {
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
//			// 指定要关注的用户
//			List<User> tmp = new ArrayList<User>();
//			User u = new User();
////			u.setId("2129755043");
////			u.setScreenName("猎头助手");
////			tmp.add(u);
////			u = new User();
////			u.setId("2036673221");
////			u.setScreenName("北京中网银科猎头");
////			tmp.add(u);
//
//			// 自动关注
//			Friendships fm = new Friendships();
//			fm.client.setToken(at.getAccessToken());
//			for (User t : tmp) {
//				try {
//					User n = fm.createFriendshipsById(t.getId());
//					System.out.println(n.toString());
//				} catch (Exception e) {
//					e.printStackTrace();
//					continue;
//				}
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
