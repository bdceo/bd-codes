package com.bdsoft.y2012.m10;

/**
 * 人搜索结果 Feed
 * 
 * @author bdceo
 * 
 */
public class UserFeed {

	private String user;
	private String uid;
	private String userUrl;
	private int fs;
	private int gz;
	private int wb;

	private String addr;// 地址
	private String info;// 简介
	private String label;// 标签
	private String school;// 学校
	private String job;// 职业
	private String card;// 个人名片

	public UserFeed() {
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("用户：" + user + "(" + gz + "|" + fs + "|" + wb + ")");
		sb.append("\t名片：" + card);
		sb.append("\t地址：" + addr);
		sb.append("\t简介：" + info);
		sb.append("\t标签：" + label);
		sb.append("\t学校：" + school);
		sb.append("\t职业：" + job);
		return sb.toString();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUserUrl() {
		return userUrl;
	}

	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}

	public int getFs() {
		return fs;
	}

	public void setFs(int fs) {
		this.fs = fs;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getGz() {
		return gz;
	}

	public void setGz(int gz) {
		this.gz = gz;
	}

	public int getWb() {
		return wb;
	}

	public void setWb(int wb) {
		this.wb = wb;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

}
