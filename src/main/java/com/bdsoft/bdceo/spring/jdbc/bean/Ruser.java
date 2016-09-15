package com.bdsoft.bdceo.spring.jdbc.bean;

public class Ruser {

	private int id;
	private String name;
	private String pwd;
	private String role;
	private int eid;

	public Ruser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ruser(int id, String name, String pwd, String role, int eid) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.role = role;
		this.eid = eid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

}
