package com.bdsoft.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
 
public class SMS {

	private final static String URL = "http://112.91.147.37:7902/MWGate/wmgw.asmx/";

	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy年MM月dd日 HH点mm分ss秒SSS");
	// 黑名单通道使用接入模式
	private final static String USERID = "J20080";
	private final static String PWD = "332005";

	// 10657120385683
	public static void main(String[] args) throws Exception {
		String num = "13426479431";
		String msg = "明天周一早会，别忘了早起哦！";
		// 发短信
		String res = sendSMS(num, msg);
		// System.out.println("结果=" + res + ",描述=" + code2info(res));
		System.out.println(code2info(res));
	}

	/**
	 * 单一用户或群发短信
	 * 
	 * @param num
	 *            手机号
	 * @param msg
	 *            短信内容
	 * @return
	 * @throws Exception
	 */
	public static String sendSMS(String num, String msg) throws Exception {
		msg += " (" + sdf.format(new Date()) + ")";
		// 短信内容编码
		msg = new String(msg.getBytes("UTF-8"));
		// 组拼参数
		Map<String, String> param = new LinkedHashMap<String, String>();
		param.put("userId", USERID);
		param.put("password", PWD);
		param.put("pszMobis", num);
		param.put("pszMsg", msg);
		// param.put("iMobiCount", "1");
		param.put("iMobiCount", "" + num.split(",").length);
		param.put("pszSubPort", "*");

		byte[] buf = null;
		try {
			buf = sendPostRequest(URL + "MongateCsSpSendSmsNew", param, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return "-999";
		}
		// System.out.println("返回=" + new String(buf, "UTF-8"));

		Document doc = DocumentHelper.parseText(new String(buf, "UTF-8"));
		Element root = doc.getRootElement();
		return root.getText();
	}

	public static String formVerifyPhoneSms(String code) {
		return "验证码：" + code;
	}

	public static byte[] sendPostRequest(String urlPath,
			Map<String, String> params, String encoding) throws Exception {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sb.append(entry.getKey()).append("=")
					.append(URLEncoder.encode(entry.getValue(), encoding));
			sb.append("&");
		}
		sb.deleteCharAt(sb.length() - 1);
		// System.out.println("参数=" + sb.toString());
		byte[] data = sb.toString().getBytes();
		URL url = new URL(urlPath);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setConnectTimeout(5 * 1000);
		http.setRequestMethod("POST");
		http.setDoOutput(true);// 发送POST请求必须设置允许输出
		http.setUseCaches(false);// 不适用cache
		http.setRequestProperty("Connection", "Keep-Alive");
		http.setRequestProperty("Charset", encoding);
		http.setRequestProperty("Content-Length", String.valueOf(data.length));
		http.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		DataOutputStream out = new DataOutputStream(http.getOutputStream());
		out.write(data);
		out.flush();

		if (http.getResponseCode() == 200) {
			return readStream(http.getInputStream());
		}
		out.close();
		return null;
	}

	// 从流中读取信息
	private static byte[] readStream(InputStream ins) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = ins.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		outStream.close();
		ins.close();
		return outStream.toByteArray();
	}

	/**
	 * 解析返回码
	 * 
	 * @param code
	 *            返回标识
	 * @return 中文提示
	 */
	public static String code2info(String code) {
		if (code == null) {
			return "无法解析";
		} else if ("-1".equals(code)) {
			return "参数为空";
		} else if ("-2".equals(code)) {
			return "电话号码个数超过100";
		} else if ("-10".equals(code)) {
			return "申请缓存空间失败";
		} else if ("-11".equals(code)) {
			return "电话号码中有非数字字符";
		} else if ("-12".equals(code)) {
			return "有异常电话号码";
		} else if ("-13".equals(code)) {
			return "电话号码个数与实际个数不相等";
		} else if ("-14".equals(code)) {
			return "实际号码个数超过100";
		} else if ("-101".equals(code)) {
			return "发送消息等待超时";
		} else if ("-102".equals(code)) {
			return "发送或接收消息失败";
		} else if ("-103".equals(code)) {
			return "接收消息超时";
		} else if ("-200".equals(code)) {
			return "其他错误";
		} else if ("-999".equals(code)) {
			return "服务器内部错误";
		} else if ("-10001".equals(code)) {
			return "用户登陆不成功";
		} else if ("-10002".equals(code)) {
			return "提交格式不正确";
		} else if ("-10003".equals(code)) {
			return "用户余额不足";
		} else if ("-10004".equals(code)) {
			return "手机号码不正确";
		} else if ("-10005".equals(code)) {
			return "计费用户帐号错误";
		} else if ("-10006".equals(code)) {
			return "计费用户密码错";
		} else if ("-10007".equals(code)) {
			return "账号已经被停用";
		} else if ("-10008".equals(code)) {
			return "账号类型不支持该功能";
		} else if ("-10009".equals(code)) {
			return "其它错误";
		} else if ("-10010".equals(code)) {
			return "企业代码不正确";
		} else if ("-10011".equals(code)) {
			return "信息内容超长";
		} else if ("-10012".equals(code)) {
			return "不能发送联通号码";
		} else if ("-10013".equals(code)) {
			return "操作员权限不够";
		} else if ("-10014".equals(code)) {
			return "费率代码不正确";
		} else if ("-10015".equals(code)) {
			return "服务器繁忙";
		} else if ("-10016".equals(code)) {
			return "企业权限不够";
		} else if ("-10017".equals(code)) {
			return "此时间段不允许发送";
		} else if ("-10018".equals(code)) {
			return "经销商用户名或密码错";
		} else if ("-10019".equals(code)) {
			return "手机列表或规则错误";
		} else if ("-10021".equals(code)) {
			return "没有开停户权限";
		} else if ("-10022".equals(code)) {
			return "没有转换用户类型的权限";
		} else if ("-10023".equals(code)) {
			return "没有修改用户所属经销商的权限";
		} else if ("-10024".equals(code)) {
			return "经销商用户名或密码错";
		} else if ("-10025".equals(code)) {
			return "操作员登陆名或密码错误";
		} else if ("-10026".equals(code)) {
			return "操作员所充值的用户不存在";
		} else if ("-10027".equals(code)) {
			return "操作员没有充值商务版的权限";
		} else if ("-10028".equals(code)) {
			return "该用户没有转正不能充值";
		} else if ("-10029".equals(code)) {
			return "此用户没有权限从此通道发送信息";
		} else if ("-10030".equals(code)) {
			return "不能发送移动号码";
		} else if ("-10031".equals(code)) {
			return "手机号码(段)非法";
		} else if ("-10032".equals(code)) {
			return "用户使用的费率代码错误";
		} else if ("-10033".equals(code)) {
			return "非法关键词";
		}
		return "发送成功";
	}
}
