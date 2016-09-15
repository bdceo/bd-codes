package com.bdsoft.y2012.m11;

public class MailAccount {

	private String email;
	private String password;
	private String host;

	public MailAccount() {
		super();
	}

	public MailAccount(String email, String password, String host) {
		super();
		this.email = email;
		this.password = password;
		this.host = host;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

}
