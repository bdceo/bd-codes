package com.bdsoft.y2012.m10;

public class XEO {
	private String s1;// 账号状态
	private String name;
	private String pwd;
	private String domain;
	private String sender;
	private String port;
	private String type;// 验证方式
	private String s2;

	public String toString() {
		return new StringBuffer(this.s1).append("|").append(this.name).append(
				"|").append(this.pwd).append("|").append(this.domain).append(
				"|").append(this.sender).append("|").append(this.port).append(
				"|").append(this.type).append("|").append(s2).toString();
	}

	public XEO() {
	}

	public XEO(String line) {
		String[] tmp = line.split("\\|");
		this.setS1(tmp[0]);
		this.setName(tmp[1]);
		this.setPwd(tmp[2]);
		this.setDomain(tmp[3]);
		this.setSender(tmp[4]);
		this.setPort(tmp[5]);
		this.setType(tmp[6]);
		this.setS2(tmp[7]);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getS1() {
		return s1;
	}

	public void setS1(String s1) {
		this.s1 = s1;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getS2() {
		return s2;
	}

	public void setS2(String s2) {
		this.s2 = s2;
	}

}