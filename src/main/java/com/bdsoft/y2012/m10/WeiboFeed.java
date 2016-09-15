package com.bdsoft.y2012.m10;

import java.util.Date;

/**
 * 微博搜索结果 Feed
 * 
 * @author bdceo
 * 
 */
public class WeiboFeed {
	private String userHome;
	private String userName;
	private String em;
	private int share;
	private int fav;
	private int comm;
	private Date date;
	private String _mail;

	public WeiboFeed() {
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("昵称：");
		sb.append(this.userName);
		sb.append("\n\t日期：");
		sb.append(this.getDate().toLocaleString());
		sb.append("\n\t微博：");
		sb.append(this.getEm());
		return sb.toString();
	}

	public int parseCount(String str) {
		if (str.indexOf("(") > 0) {
			String tmp = str.substring(str.indexOf("(") + 1,
					str.lastIndexOf(")"));
			return Integer.parseInt(tmp);
		}
		return 0;
	}

	public String getUserHome() {
		return userHome;
	}

	public void setUserHome(String userHome) {
		this.userHome = userHome;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEm() {
		return em;
	}

	public String get_mail() {
		return _mail;
	}

	public void set_mail(String _mail) {
		this._mail = _mail;
	}

	public void setEm(String em) {
		this.em = em;
	}

	public int getShare() {
		return share;
	}

	public void setShare(int share) {
		this.share = share;
	}

	public int getFav() {
		return fav;
	}

	public void setFav(int fav) {
		this.fav = fav;
	}

	public int getComm() {
		return comm;
	}

	public void setComm(int comm) {
		this.comm = comm;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
