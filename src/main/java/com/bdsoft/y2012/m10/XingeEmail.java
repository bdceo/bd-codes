package com.bdsoft.y2012.m10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.bdsoft.utils.FileUtil;
import com.bdsoft.utils.StringUtil;

public class XingeEmail {

	public static void main(String[] args) {
		allTosmtp();// 全部转换成smtp
	}

	public static void allTosmtp() {
		try {
			File f = new File("d:/smtp_all.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				// System.out.println(line);
				XEO xeo = new XEO(line);

				xeo.setS1("True");// 全部置为有效
				String smtp = SMTPServer.getSmtp(xeo.getDomain());
				if (!"".equals(smtp)) {
					xeo.setSender(xeo.getName() + "@" + xeo.getDomain());
					xeo.setDomain(smtp);
					xeo.setType("2");
				}
				if(StringUtil.isEmpty(xeo.getSender())){
					xeo.setType("0");
				}
				sb.append(xeo);
				sb.append("\n");
			}
			// System.out.println(sb.toString());
			String file = "d:/smtp.txt";
			FileUtil.writeFile(file, sb.toString(), true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
