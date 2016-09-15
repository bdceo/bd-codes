package com.bdsoft.y2013.m10;

import org.apache.log4j.Logger;

import com.bdsoft.y2013.m10.log.Action;
import com.bdsoft.y2013.m10.log.SubLog;

/**
 * log4j日志输出复习总结
 * 
 * 依赖jar：log4j-1.2.17.jar
 * 
 * 参考：http://www.blogjava.net/hwpok/archive/2008/01/16/175711.html
 * 
 * http://www.blogjava.net/hwpok/archive/2008/08/23/223891.html
 * 
 * 
 * @author bdceo
 * 
 */
public class Log4jTest {

	private static Logger log = Logger.getLogger(Log4jTest.class.getName());

	/**
	 * log4j输出，关键配置文件：log4j.properties
	 * 
	 * 涉及三部分配置：
	 * 
	 * 1，指定日志输出级别，及输出目的地（Logger）
	 * 
	 * 2，指定输出目的地的详细配置(Appender)
	 * 
	 * 3，指定输出日志格式（Layout）
	 * 
	 * 
	 * %r 时间 0
	 * 
	 * %t 线程，方法名 main
	 * 
	 * %p 优先级 DEBUG/INFO/ERROR
	 * 
	 * %c 所属类的全名(包括包名)
	 * 
	 * %l 输出日志事件的发生位置
	 * 
	 * %m 输出代码中指定的信息
	 * 
	 * %n 输出一个换行
	 * 
	 * %F 输出日志消息产生时所在的文件名称
	 * 
	 * %L 输出代码中的行号
	 * 
	 */
	static {
		// PropertyConfigurator.configure("src/log4j.properties");
	}

	public static void main(String[] args) {
		System.out.println("system out msg");

		log.debug("debug msg");
		log.info("info msg");
		log.warn("warn msg");
		log.error("error msg");
		log.fatal("fatal msg");

		test1();

		new Action();

		new SubLog().sub();
	}

	public static void test1() {
		log.info("test1() info msg");
		new ThreadLog("Thd").start();
	}

	public static class ThreadLog extends Thread {

		public ThreadLog(String name) {
			setName(name);
		}

		public void run() {
			log.debug("run() debug msg");
			try {
				Thread.sleep(1000);
				log.warn("run() warn msg");
				int r = 1 / 0;
				log.debug("1/0 = " + r);
			} catch (Exception e) {
				log.error(e);
			}
		}

	}

}
