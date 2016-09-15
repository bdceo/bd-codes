package com.bdsoft.y2012.m11;

import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	private final static String PORT = "25";

	// private final static String EMAIL = "dingchenye@zhongwangyinke.cn";
	// private final static String SMTP = "smtp.ym.163.com";
	// private final static String ACCOUNT = "dingchenye";
	// private final static String PWD = "bdceo0517";

	// private final static String EMAIL = "nm970f0qs@tom.com";
	// private final static String SMTP = "smtp.tom.com";
	// private final static String ACCOUNT = "nm970f0qs";
	// private final static String PWD = "768e6zknx";

	// private final static String EMAIL = "ivpgmtzyh@sohu.com";
	// private final static String SMTP = "smtp.sohu.com";
	// private final static String ACCOUNT = "ivpgmtzyh";
	// private final static String PWD = "i44ezxv3hu";

	// private final static String EMAIL = "dzqomcqgq@sogou.com";
	// private final static String SMTP = "mx.mail.sogou.com";
	// private final static String ACCOUNT = "dzqomcqgq";
	// private final static String PWD = "l424ac669";

	// private final static String EMAIL = "lsivcsecd@163.com";
	// private final static String SMTP = "smtp.163.com";
	// private final static String ACCOUNT = "lsivcsecd";
	// private final static String PWD = "15pwxi515";

	// private final static String EMAIL = "l6j903bnd9b@126.com";
	// private final static String SMTP = "smtp.126.com";
	// private final static String ACCOUNT = "l6j903bnd9b@126.com";
	// private final static String PWD = "0i5w3i9xkz";

	// private final static String EMAIL = "lptlxthree@21cn.com";
	// private final static String SMTP = "smtp.21cn.com";
	// private final static String ACCOUNT = "lptlxthree";
	// private final static String PWD = "t0y6i84a2";

	// private final static String EMAIL = "wkx1nybj5fc@yeah.net";
	// private final static String SMTP = "smtp.yeah.net";
	// private final static String ACCOUNT = "wkx1nybj5fc";
	// private final static String PWD = "5d36u9a4o1";

	private final static String EMAIL = "bdceo@163.com";
	private final static String SMTP = "smtp.163.com";
	private final static String ACCOUNT = "bdceo";
	private final static String PWD = ACCOUNT+"0517";

	private static String MAIL_SP = ";";
	private static String REPLY = "bdceo@qq.com";

	public static void main(String[] args) throws Exception {
		String to = "18668639647@qq.com";
		String subject = "【通知】bd-dm：京东抓取异常";
		String content = "异常报警，立马解决。邮件内容：" + new Date().toLocaleString();

		sendMail(subject, content, to);
	}

	public static void sendMail(String subject, String content, String to) {
		try {
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

			// 账户设置
			Properties pps = System.getProperties();
			pps.put("mail.smtp.host", SMTP);
			pps.put("mail.smtp.auth", "true");
			pps.put("mail.transport.protocol", "smtp");
			pps.put("mail.smtp.port", PORT);
			// pps.put("mail.smtp.socketFactory.class", SSL_FACTORY);
			// pps.put("mail.smtp.socketFactory.fallback", "false");
			// pps.put("mail.smtp.socketFactory.port", PORT);

			// Session
			final String username = ACCOUNT;
			final String password = PWD;
			Session session = Session.getDefaultInstance(pps,
					new Authenticator() {
						@Override
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});
			session.setDebug(false);

			// 邮件内容(发件人，收件人，内容)
			Message msg = new MimeMessage(session);
			// 设置发件人
			InternetAddress from = new InternetAddress(EMAIL, "老丁");
			// from.setPersonal("高级商务经理");
			msg.setFrom(from);

			// 设置收件人
			String[] tos = to.split(MAIL_SP);
			int len = tos.length;
			InternetAddress[] toAddr = new InternetAddress[len];
			for (int i = 0; i < len; i++) {
				toAddr[i] = new InternetAddress(tos[i]);
			}
			msg.setRecipients(Message.RecipientType.TO, toAddr);// 主收件人
			msg.setRecipient(Message.RecipientType.CC, new InternetAddress(
					"bdceo@qq.com"));// 抄送收件人
			msg.setRecipient(Message.RecipientType.BCC, new InternetAddress(
					"youknowceo@gmail.com"));// 暗抄收件人

			// 设置回复地址
			InternetAddress reply = new InternetAddress(REPLY);
			msg.setReplyTo(new InternetAddress[] { reply });

			// 设置邮件内容和主题
			msg.setSubject(subject);// 主题
			msg.setText(content);// 内容
			msg.setSentDate(new Date());// 发信时间
			msg.saveChanges();

			// 准备发送
			// Transport trans = session.getTransport("smtp");
			// trans.connect((String) pps.get("mail.smtp.host"), EMAIL,
			// password);// 连接服务器
			// trans.sendMessage(msg, msg.getAllRecipients());// 发送
			// trans.close();
			Transport.send(msg);
			for (Address a : msg.getAllRecipients()) {
				InternetAddress ia = (InternetAddress) a;
				System.out.println("邮件已发至 >> " + ia.getAddress());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}