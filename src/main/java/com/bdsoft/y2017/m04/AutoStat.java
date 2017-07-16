package com.bdsoft.y2017.m04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 自动统计
 */
public class AutoStat {

	static Runtime rt = Runtime.getRuntime();
	static String pyCmd = "python d:/download/doDB_query.py {0} {1} {2}";

	public static void main(String[] args) {
		Timer timer = new Timer(false);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("\n\n" + new Date().toLocaleString());
				System.out.println("***************执行python统计***********");
				String type = "designer";
				String start = "1493200800";
				String end = "1493287000";
				runPython(type, start, end);

				System.out.println("***************执行统计*****************");

				System.out.println("***************发邮件******************");
			}
		}, 0, 1000 * 3);
	}

	/**
	 * 执行python脚本
	 */
	static void runPython(String type, String start, String end) {
		MessageFormat formart = new MessageFormat(pyCmd, Locale.getDefault());
		String cmd = formart.format(new Object[] { type, start, end });
		System.out.println("执行\t" + cmd);
		try {
			Process pro = rt.exec(cmd);
			if (pro != null) {
				BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream(), "GBK"));
				BufferedReader errIn = new BufferedReader(new InputStreamReader(pro.getErrorStream(), "GBK"));

				String line = null;
				while ((line = errIn.readLine()) != null) {
					System.out.println("出错\t" + line);
				}

				while ((line = in.readLine()) != null) {
					System.out.println("正常\t" + line);
				}
				System.out.println("结束\t" + cmd);
			}
		} catch (Exception e) {
			System.err.println("执行失败\t" + e.getMessage());
		}
	}

}
