package com.bdsoft.utils;

import java.io.IOException;
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static void main(String[] es) {
		String str = "null";
		System.out.println(isEmpty(str));
	}

	public static String subStr(String str, int len) {
		if (isEmpty(str)) {
			return "";
		}
		if (str.length() >= len) {
			return str.substring(0, len) + "..";
		} else {
			return str;
		}
	}

	/**
	 * 比较两个字符串（大小写敏感）。
	 * 
	 * <pre>
	 * StringUtil.equals(null, null)   = true
	 * StringUtil.equals(null, "abc")  = false
	 * StringUtil.equals("abc", null)  = false
	 * StringUtil.equals("abc", "abc") = true
	 * StringUtil.equals("abc", "ABC") = false
	 * </pre>
	 * 
	 * @param str1
	 *            要比较的字符串1
	 * @param str2
	 *            要比较的字符串2
	 * 
	 * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
	 */
	public static boolean equals(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}

		return str1.equals(str2);
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param src
	 * @return 空-true，非空-false
	 */
	public static boolean isEmpty(String src) {
		if (src == null || src.trim().equals("null") || src.trim().equals(""))
			return true;
		return false;
	}

	public static boolean isNotEmpty(String src) {
		return !isEmpty(src);
	}

	public static long[] convert(String[] idArray) {
		long[] desArray = new long[idArray.length];
		for (int i = 0; i < desArray.length; i++) {
			desArray[i] = Long.parseLong(idArray[i]);
		}
		return desArray;
	}

	/**
	 * convert idArray to []. 1,2,3,4 --> [1,2,3,4] catch exception add by fwl
	 * 
	 * @param idArray
	 * @return
	 */
	public static int[] convertIntfwl(String[] idArray) {
		int[] desArray = new int[idArray.length];
		for (int i = 0; i < desArray.length; i++) {
			try {
				desArray[i] = Integer.parseInt(idArray[i]);
			} catch (NumberFormatException e) {
				desArray[i] = 0;
			}
		}
		return desArray;
	}

	public static int[] convertInt(String[] idArray) {
		int[] desArray = new int[idArray.length];
		for (int i = 0; i < desArray.length; i++) {
			desArray[i] = Integer.parseInt(idArray[i]);
		}
		return desArray;
	}

	public static String convertArrayToString(String[] idArray) {
		StringBuffer sb = new StringBuffer();
		for (Object obj : idArray) {
			sb.append(obj);
			sb.append(",");
		}
		sb.delete(sb.length() - 1, sb.length());
		return sb.toString();
	}

	// 数字格式化
	static public DecimalFormat nf1 = new DecimalFormat("###,##0.00");

	/**
	 * 将空串变化为""
	 * 
	 * @param str
	 * @return
	 */

	public static String nullToEmpty(String str) {
		return (nullToEmpty(str, ""));
	}

	public static String nullToEmpty(String str, String defaultStr) {
		if (str == null || str == "")
			return defaultStr;
		else
			return str;
	}

	/**
	 * 将null对象变化为 "" 如果不为空则返回 Object.toString();
	 * 
	 * @param obj
	 * @return obj.toString();
	 */
	public static String nullToEmpty(Object obj) {
		if (obj == null)
			return "";
		else
			return obj.toString();
	}

	/**
	 * 根据默认的除数20,获得表索引值,
	 * 
	 * @param dividend
	 * @return
	 */
	public static long getTablename(long dividend) {
		return getTablename(20, dividend);
	}

	/**
	 * 根据制定整数,和制定除数取余数
	 * 
	 * @param divisor除数
	 * @param dividend被除数
	 * @return
	 */
	public static long getTablename(int divisor, long dividend) {
		long i = dividend % divisor;
		return i;
	}

	/**
	 * 字符串转换编码
	 * 
	 * ISO-8859-1 可能是 form 提交可能的原始编码
	 * 
	 * @param src
	 * @param srcCharset
	 *            原始编码 if null is ISO-8859-1
	 * @param objCharset
	 *            目标编码 if null is UTF-8
	 * @return if src is null return is ""
	 */
	public static String getEncoding(String src, String srcCharset,
			String objCharset) {
		try {
			if (src == null) {
				return "";
			}
			if (srcCharset == null) {
				srcCharset = "ISO-8859-1";
			}
			if (objCharset == null) {
				objCharset = "UTF-8";
			}
			return new String(src.getBytes(srcCharset), objCharset);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
	}

	public static String getUtf8Encoding(String scr) {
		return getEncoding(scr, null, null);
	}

	/**
	 * 比较两字符串是否一样.(不分大小写) 如果相同返回 equalsStr 反之返加 noEqualsStr 所有参数如果为null or ""
	 * ,当前参数值视为 "" 处理.
	 * 
	 * @param str
	 * @param objStr
	 * @param equalsStr
	 * @param noEqualsStr
	 * @return
	 */
	public static String equalsIgnoreCase(String str, String objStr,
			String equalsStr, String noEqualsStr) {
		if (noEqualsStr == null) {
			noEqualsStr = "";
		}
		if (equalsStr == null) {
			equalsStr = "";
		}
		if (str == null) {
			str = "";
		}
		if (objStr == null) {
			objStr = "";
		}
		if (str.equalsIgnoreCase(objStr)) {
			return equalsStr;
		} else {
			return noEqualsStr;
		}
	}

	public static String CheckString(String strings) {
		return "";
	}

	public static boolean CheckContent(String content, String findcontent) {
		if (content.indexOf(findcontent) > 0)
			return true;
		else
			return false;
	}

	public static boolean CheckContent(String content, String[] findcontent) {
		for (int i = 0; i < findcontent.length; i++) {
			if (content.indexOf(findcontent[i]) > 0)
				return true;
		}
		return false;
	}

	// / <summary>
	// / 获得字符串的长度，一个Unicode计算为2字符
	// / </summary>
	// / <param name="word">输入字符串</param>
	// / <returns></returns>
	public static int GetCnLength(String word) {
		int len = 0;
		for (int i = 0; i < word.length(); i++) {
			if ((int) word.charAt(i) > 256)
				len += 2;
			else
				len++;
		}
		return (len);
	}

	public static boolean isExpression(String reg, String text) {
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(text);
		while (m.find()) {
			return true;
		}
		return false;
	}

	/** The size of blocking to use */
	private static final int BLKSIZ = 2048;

	/**
	 * 将一个Reader里的内容转为一个字符串
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static String readerToString(Reader is) throws IOException {
		if (is == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		char[] b = new char[BLKSIZ];
		int n;

		// Read a block. If it gets any chars, append them.
		while ((n = is.read(b)) > 0) {
			sb.append(b, 0, n);
		}

		// Only construct the String object once, here.
		return sb.toString();
	}

	// / <summary>
	// / 对传入的字符串取前多少位的字符
	// / </summary>
	// / <param name="Word">传入字符创</param>
	// / <param name="Size">截取长度</param>
	// / <param name="Omit">是否使用省略符</param>
	// / <param name="Sign">省略符</param>
	// / <returns></returns>
	public static String StringLeft(String word, int size, boolean omit,
			String sign) {
		StringBuffer sb = new StringBuffer();
		int len = 0;
		for (int i = 0; i < word.length(); i++) {
			if ((int) word.charAt(i) > 256) {
				len += 2;
			} else {
				len++;
			}

			if (len < size) {
				sb.append(word.charAt(i));
			} else {
				if (omit) {
					sb.append(sign);
					break;
				} else {
					sb.append(word.charAt(i));
					break;
				}
			}
		}
		return sb.toString();
	}

	// / <summary>
	// / 对传入的字符串取前多少位的字符
	// / </summary>
	// / <param name="Word">传入字符创</param>
	// / <param name="Size">截取长度</param>
	// / <param name="Omit">是否使用省略符</param>
	// / <returns></returns>
	public static String StringLeft(String word, int size, boolean omit) {
		return (StringLeft(word, size, omit, "."));
	}

	// / <summary>
	// / 对传入的字符串取前多少位的字符
	// / </summary>
	// / <param name="Word">传入字符创</param>
	// / <param name="Size">截取长度</param>
	// / <returns></returns>
	public static String StringLeft(String word, int size) {
		return (StringLeft(word, size, true, "."));
	}

	/**
	 * Author:zhaojinpo Date:2005-8-30 9:19:35 Description:html格式化
	 * 
	 * @param strInput
	 * @return String
	 * 
	 */
	public static String HtmlEncode(String strInput) {
		String input = strInput;
		input = nullToEmpty(input);
		if ("".equals(input)) {
			return "&nbsp;";
		} else {
			input = Replace(input, "\"", "&quot;");
			input = Replace(input, "<", "&lt;");
			input = Replace(input, ">", "&gt;");
			input = Replace(input, " ", "&nbsp;");
			input = Replace(input, "\r\n", "<br>");
			input = Replace(input, "\r", "<br>");
			input = Replace(input, "\n", "<br>");

			return input;
		}
	}

	public static String HtmlReverse(String strInput) {
		String input = strInput;
		input = nullToEmpty(input);
		if ("".equals(input)) {
			return "";
		} else {
			input = Replace(input, "&quot;", "\"");
			input = Replace(input, "&lt;", "<");
			input = Replace(input, "&gt;", ">");
			input = Replace(input, "&nbsp;", " ");
			input = Replace(input, "<br>", "");
			input = Replace(input, "<br>", "");
			input = Replace(input, "<br>", "");
			input = Replace(input, "&amp;", "");
			input = Replace(input, "&reg;", "®");
			input = Replace(input, "&copy;", "©");
			input = Replace(input, "&trade;", "™");
			input = Replace(input, "&ensp;", " ");
			input = Replace(input, "&emsp;", " ");
			input = Replace(input, "&nbsp;", " ");
			return input;
		}
	}

	public static String Replace(String str, String substr, String substitute) {
		str = nullToEmpty(str);
		String retstr = str;
		substr = nullToEmpty(substr);
		substitute = nullToEmpty(substitute);
		int substrlen = substr.length();
		for (int pos = retstr.indexOf(substr); pos != -1;) {
			String temp = retstr.substring(0, pos);
			temp = nullToEmpty(temp);
			temp = String.valueOf(temp) + String.valueOf(substitute);
			pos += substrlen;
			String half = retstr.substring(pos);
			half = nullToEmpty(half);
			if ("".equals(half)) {
				retstr = temp;
				break;
			}
			if (half.indexOf(substr) == -1)
				pos = -1;
			else
				pos = half.indexOf(substr) + temp.length();
			retstr = String.valueOf(temp) + String.valueOf(half);
		}

		return retstr;
	}

	/**
	 * 获取文件名称包括后缀，如c:/test/ljh.cs 则返回ljh gongyousan 创建时间:2007-10-9 10:34:44
	 * 
	 * @param filepath
	 * @return
	 */
	public static String GetFileNameWithOutExt(String filepath) {
		String returnstr = "*.*";
		int length = filepath.trim().length();
		filepath = filepath.replace("\\", "/");
		if (length > 0) {
			returnstr = GetFileNameWithOutExt(filepath);
			if (returnstr.length() > 0) {
				returnstr = returnstr.substring(0, returnstr.indexOf("."));
			}
		}
		return returnstr;
	}

	/**
	 * 半角转全角
	 * 
	 * @param QJstr
	 * @return
	 */
	public static final String BQchange(String QJstr) {
		String outStr = "";
		String Tstr = "";
		byte[] b = null;

		for (int i = 0; i < QJstr.length(); i++) {
			try {
				Tstr = QJstr.substring(i, i + 1);
				b = Tstr.getBytes("unicode");
			} catch (java.io.UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			if (b[3] != -1) {
				b[2] = (byte) (b[2] - 32);
				b[3] = -1;
				try {
					outStr = outStr + new String(b, "unicode");
				} catch (java.io.UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else
				outStr = outStr + Tstr;
		}

		return outStr;
	}

	/**
	 * 全角转半角
	 * 
	 * @param QJstr
	 * @return
	 */
	public static final String QBchange(String QJstr) {
		String outStr = "";
		String Tstr = "";
		byte[] b = null;

		for (int i = 0; i < QJstr.length(); i++) {
			try {
				Tstr = QJstr.substring(i, i + 1);
				b = Tstr.getBytes("unicode");
			} catch (java.io.UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			if (b[3] == -1) {
				b[2] = (byte) (b[2] + 32);
				b[3] = 0;
				try {
					outStr = outStr + new String(b, "unicode");
				} catch (java.io.UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else
				outStr = outStr + Tstr;
		}
		return outStr;
	}

}
