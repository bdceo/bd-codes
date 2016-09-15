package com.bdsoft.bdceo.j2se.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		p("adf".matches("..."));// true
		p("a2232d233dw".replaceAll("\\d", "-"));// a----d---dw

		Pattern p = Pattern.compile("[a-z]{4}");
		Matcher m = p.matcher("adfg");
		p(m.matches());// true
		p("asd".matches("[a-z]{3}"));// true

		p("a".matches("a"));// true
		p("a".matches("."));// true
		p("aaaa".matches("a*"));// true
		p("".matches("a*"));// true [zero or many]
		p("aaaa".matches("a+"));// true
		p("aaaa".matches("a?"));// false [one or zero]
		p("".matches("a?"));// true [one or zero]

		p("0123456789".matches("\\d{1,10}"));// true
		p("134".matches("\\d{3}"));// true
		p("13472338051".matches("\\d{11,}"));// true
		p("192.186.0.aaa".matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}.\\d{1,3}"));// false
		p("134".matches("[0-1][0-3][0-9]"));// true

		p("a".matches("[saac]"));// true
		p("a".matches("[^ads]"));// false
		p("A".matches("[a-zA-Z]"));// true
		p("A".matches("[a-z[A-Z]]"));// true
		p("A".matches("[a-z]|[A-Z]"));// true
		p("R".matches("[A-Z&&[RFG]]"));// true

		p("\t\r \n".matches("\\s{4}"));// true
		p(" ".matches("\\S"));// false [whitespace is not empty char]
		p("a_8_".matches("\\w{4}"));// true
		p("abc888&^%".matches("[a-z]{1,3}\\d+[&^#%]+"));// true
		p("\\".matches("\\\\"));// true

		p("s".matches("\\p{Lower}"));// true
		p("S".matches("\\p{Upper}"));// true
		p("5".matches("\\p{Digit}"));// true
		p("hello sir".matches("^h.*"));// true
		p("hello sir".matches(".*ir$"));// true
		p("hello sir".matches("^h[a-z]{1,3}o\\b.*"));// true [\b a word border]
		p("hellosir".matches("^h[a-z]{1,3}.*r\\b"));// true

		p(" \n".matches("^[\\s&&[^\\n]].*\\n$"));// true
		p("aaaa 8888c".matches(".*\\d{4}.*"));// true
		p("aaaa 8888c".matches(".*\\b\\d{4}.*"));// true
		p("aaaa8888c".matches(".*\\d{4}."));// true
		p("aaaa8888c".matches(".*\\b\\d{4}."));// false

		p("youknowceo@gmail.com".matches("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+"));// true

		p = Pattern.compile("\\d[3,5]");// x3或x5数字组合
		String s = "233-32134-432-32";
		m = p.matcher(s);
		p(m.matches());// false

		m.reset();
		p(m.find());// true
		p(m.start() + "--" + m.end());// 0--2
		p(m.find());// true
		p(m.start() + "--" + m.end());// 6--8
		p(m.find());// true
		p(m.start() + "--" + m.end());// 10--12
		p(m.find());// false
		p(m.lookingAt());// true
		System.out.println();
		p = Pattern.compile("java", Pattern.CASE_INSENSITIVE);
		m = p.matcher("java Java JaVa JAVA IloveJAVA you hateJava fdfs");
		while (m.find()) {
			p(m.group());/*
						 * java Java JaVa JAVA JAVA Java
						 */
		}
		p(m.replaceAll("JAVA"));// JAVA JAVA JAVA JAVA IloveJAVA you hateJAVA
								// fdfs

		StringBuffer buf = new StringBuffer();
		int i = 0;
		while (m.find()) {
			i++;
			if (i % 2 == 0) {
				m.appendReplacement(buf, "JAVA");
			} else {
				m.appendReplacement(buf, "java");
			}
		}
		m.appendTail(buf);
		p(buf);

		p = Pattern.compile("(\\d{3,5})([a-z]{2})");
		m = p.matcher("234se-12223de-321de-42dd");
		while (m.find()) {
			p(m.group(1));/*
						 * 234 12223 321
						 */
		}

		p = Pattern.compile("(.{3,10}+)[0-9]");
		s = "aaaa5bbbb60";
		m = p.matcher(s);
		if (m.find()) {
			p(m.start() + "--" + m.end());// 0--11
		} else {
			p("not match!");// print
		}

		p = Pattern.compile(".{2}(?=a)");
		s = "22a33s";
		m = p.matcher(s);
		while (m.find()) {
			p(m.group());// 22
		}

	}

	public static void p(Object obj) {
		System.out.println(obj);
	}
}
