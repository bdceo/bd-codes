package com.bdsoft.y2012.m11;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;

import com.bdsoft.utils.StringUtil;

public class MailInst {

	private String host;

	private boolean validate = true;

	private String email;
	private String account;
	private String password;

	private String nickname;

	private String to;
	private String cc;
	private String bcc;

	private String reply;

	private String subject;
	private String content;

	public MailInst() {
		super();
	}

	/**
	 * 发邮件实例对象
	 * 
	 * @param host
	 *            smtp域名
	 * @param account
	 *            邮箱名
	 * @param password
	 *            密码
	 * @param mc
	 *            邮件内容
	 */
	public MailInst(String host, String email, String password, MailContent mc) {
		this.host = host;
		this.email = email;
		this.account = email.substring(0, email.indexOf("@"));
		this.password = password;
		this.subject = mc.getSubject();
		this.content = mc.getContent();
	}

	/**
	 * 发邮件实例对象
	 * 
	 * @param ma
	 *            发信账户
	 * @param mc
	 *            邮件内容
	 */
	public MailInst(MailAccount ma, MailContent mc) {
		this.host = ma.getHost();
		this.email = ma.getEmail();
		this.account = email.substring(0, email.indexOf("@"));
		this.password = ma.getPassword();
		this.subject = mc.getSubject();
		this.content = mc.getContent();
	}

	public Properties getPPS() {
		Properties pps = System.getProperties();
		pps.put("mail.smtp.host", host);
		pps.put("mail.smtp.port", MailSender.SMTP_PORT);
		pps.put("mail.smtp.auth", validate ? "true" : "false");
		pps.put("mail.transport.protocol", "smtp");
		return pps;
	}

	public Session getSession() {
		Session session = null;
		if (validate) {
			MailAuth auth = new MailAuth(account, password);
			session = Session.getDefaultInstance(getPPS(), auth);
		} else {
			session = Session.getDefaultInstance(getPPS());
		}
		// if (validate) {
		// session = Session.getDefaultInstance(getPPS(), new Authenticator() {
		// protected PasswordAuthentication getPasswordAuthentication() {
		// return new PasswordAuthentication(account, password);
		// }
		// });
		// } else {
		// session = Session.getDefaultInstance(getPPS());
		// }
		return session;
	}

	public InternetAddress getMailFrom() throws Exception {
		InternetAddress add = new InternetAddress(email);
		if (!StringUtil.isEmpty(nickname)) {
			add.setPersonal(nickname);
		}
		return add;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

}
