package com.bdsoft.bdceo.ibatis.bean;

public class User {

	private int pk;
	private String name;
	private String pwd;

	public User() {
		super();
	}

	public User(String name, String pwd) {
		this.name = name;
		this.pwd = pwd;
	}

	public String toString() {
		return "User [pk=" + pk + ", name=" + name + ", pwd=" + pwd + "]";
	}

	public int getPk() {
		return pk;
	}

	public void setPk(int id) {
		this.pk = id;
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

}
