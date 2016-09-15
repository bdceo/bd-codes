package com.bdsoft.y2012.m09;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class EncodeTest {

	public static void main(String[] args) {
		try {
			String f = URLDecoder.decode("%252B", "UTF-8");
			f = URLDecoder.decode(f,"UTF-8");
			System.out.println("["+f+"]");
			
			System.out.println();
			
			String keys = "招聘 mail";
//			keys = keys.replaceAll(" ", "+");
			System.out.println(keys);
			String tmp = URLEncoder.encode(keys, "UTF-8");
			System.out.println(tmp);
			tmp = tmp.replaceAll("\\+", "%20");
			System.out.println(URLEncoder.encode(tmp, "UTF-8"));
			System.out.println(URLEncoder.encode("1This string has spaces",
					"UTF-8"));
			System.out.println(URLEncoder.encode("2This*string*has*asterisks",
					"UTF-8"));
			System.out.println(URLEncoder.encode(
					"3This%string%has%percent%signs", "UTF-8"));
			System.out.println(URLEncoder.encode("4This+string+has+pluses",
					"UTF-8"));
			System.out.println(URLEncoder.encode("5This/string/has/slashes",
					"UTF-8"));
			System.out.println(URLEncoder.encode(
					"6Thiss/tring/has/quote/marks", "UTF-8"));
			System.out.println(URLEncoder.encode("7This:string:has:colons",
					"UTF-8"));
			System.out.println(URLEncoder.encode("8This~string~has~tildes",
					"UTF-8"));
			System.out.println(URLEncoder.encode(
					"9This(string)has(parentheses)", "UTF-8"));
			System.out.println(URLEncoder.encode("10This.string.has.periods",
					"UTF-8"));
			System.out.println(URLEncoder.encode(
					"11This=string=has=equals=signs", "UTF-8"));
			System.out.println(URLEncoder.encode("12This&string&has&ersands",
					"UTF-8"));
			System.out.println(URLEncoder.encode(
					"13Thiséstringéhasé non-ASCII characters", "UTF-8"));
			System.out.println(URLEncoder.encode("this中华人民共和国", "UTF-8"));
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
	}
}
