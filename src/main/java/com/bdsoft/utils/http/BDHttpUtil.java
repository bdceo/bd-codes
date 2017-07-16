package com.bdsoft.utils.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.poi.util.IOUtils;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * http调用封装
 */
public final class BDHttpUtil {

	private static Logger log = LoggerFactory.getLogger(BDHttpUtil.class);

	// https信任证书管理
	public final static X509TrustManager X509_TM = new X509TrustManager() {

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		@Override
		public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		}

		@Override
		public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		}
	};

	/**
	 * 是否是https请求
	 * 
	 * @param url
	 *            请求地址
	 * @return
	 */
	public static boolean isHttps(String url) {
		return StringUtils.isEmpty(url) ? false : url.startsWith("https");
	}

	/**
	 * GET请求
	 * 
	 * @param url
	 *            请求地址
	 */
	public static String sendGet(String url) {
		return sendGet(url, BDHttpParam.init());
	}

	/**
	 * 带参数的GET请求
	 * 
	 * @param url
	 *            请求地址
	 * @param pm
	 *            参数
	 * @return
	 */
	public static String sendGet(String url, BDHttpParam pm) {
		// 设置通用参数
		StringBuilder params = new StringBuilder();
		if (pm.hasCommon()) {
			params.append(url).append("?");
			for (Entry<String, String> apm : pm.getCommonParams().entrySet()) {
				params.append(apm.getKey()).append("=").append(apm.getValue()).append("&");
			}
			url = params.substring(0, params.lastIndexOf("&"));
		}

		// 修改适应https请求 @20160804
		// HttpClient client = HttpClientBuilder.create().build();
		HttpClient client = isHttps(url) ? new DefaultHttpClient() : HttpClientBuilder.create().build();
		try {
			if (isHttps(url)) {
				SSLContext sslCtx = SSLContext.getInstance("SSL");
				sslCtx.init(null, new TrustManager[] { X509_TM }, null);
				SSLSocketFactory sslSf = new SSLSocketFactory(sslCtx);
				ClientConnectionManager ccm = client.getConnectionManager();
				SchemeRegistry sr = ccm.getSchemeRegistry();
				sr.register(new Scheme("https", 443, sslSf));
			}

			HttpGet get = new HttpGet(url);
			log.debug("get url {}", url);

			// 设置Cookie
			if (pm.hasCookie()) {
				StringBuilder cookies = new StringBuilder();
				for (Entry<String, String> acm : pm.getCookieParams().entrySet()) {
					cookies.append(acm.getKey()).append("=").append(acm.getValue()).append(";");
				}
				get.setHeader("Cookie", cookies.toString());
				log.debug("\twith cookie {}", cookies.toString());
			}
			// 设置指定http-头
			if (pm.hasHeader()) {
				for (Entry<String, String> ahm : pm.getHeaderParams().entrySet()) {
					get.setHeader(ahm.getKey(), ahm.getValue());
					log.debug("\twith header {}={}", ahm.getKey(), ahm.getValue());
				}
			}
			HttpResponse res = client.execute(get);
			int code = res.getStatusLine().getStatusCode();
			if (code == 200) {
				// 判断是否gzip响应 @20160804
				Header hd = res.getLastHeader("Content-Encoding");
				if (hd != null && hd.getValue().equals("gzip")) {
					res.setEntity(new GzipDecompressingEntity(res.getEntity()));
				}
				return EntityUtils.toString(res.getEntity(), pm.getCharset()).trim();
			}
//			NetMonitor.log(code, url);
		} catch (Exception e) {
//			NetMonitor.log(e, url);
		} finally {
			HttpClientUtils.closeQuietly(client);
		}
		return "";
	}

	/**
	 * 带参数的GET请求，返回完整http响应对象response
	 * 
	 * @param url
	 *            请求地址
	 * @param pm
	 *            参数
	 */
	public static HttpResponse sendGetWithBack(String url, BDHttpParam pm) {
		// 设置通用参数
		StringBuilder params = new StringBuilder();
		if (pm.hasCommon()) {
			params.append(url).append("?");
			for (Entry<String, String> apm : pm.getCommonParams().entrySet()) {
				params.append(apm.getKey()).append("=").append(apm.getValue()).append("&");
			}
			url = params.substring(0, params.lastIndexOf("&"));
		}
		HttpClient client = HttpClientBuilder.create().build();
		try {
			HttpGet get = new HttpGet(url);
			log.debug("get url {}", url);

			// 设置Cookie内容
			if (pm.hasCookie()) {
				StringBuilder cookies = new StringBuilder();
				for (Entry<String, String> acm : pm.getCookieParams().entrySet()) {
					cookies.append(acm.getKey()).append("=").append(acm.getValue()).append(";");
				}
				get.setHeader("Cookie", cookies.toString());
				log.debug("\twith cookie {}", cookies.toString());
			}
			// 设置指定http-头
			if (pm.hasHeader()) {
				for (Entry<String, String> ahm : pm.getHeaderParams().entrySet()) {
					get.setHeader(ahm.getKey(), ahm.getValue());
					log.debug("\twith header {}={}", ahm.getKey(), ahm.getValue());
				}
			}
			// 发送请求并返回
			return client.execute(get);
		} catch (Exception e) {
			log.error("请求失败 {},{}", url, e.getMessage());
			return null;
		} finally {
			HttpClientUtils.closeQuietly(client);
		}
	}

	/**
	 * POST请求
	 * 
	 * @param url
	 *            地址
	 * @param pm
	 *            参数
	 */
	public static String sendPost(String url, BDHttpParam pm) {
		HttpClient client = HttpClientBuilder.create().build();
		try {
			HttpPost post = new HttpPost(url);
			log.debug("post url {}", url);

			// 设置Cookie内容
			if (pm.hasCookie()) {
				StringBuilder cookies = new StringBuilder();
				for (Entry<String, String> acm : pm.getCookieParams().entrySet()) {
					cookies.append(acm.getKey()).append("=").append(acm.getValue()).append(";");
				}
				post.setHeader("Cookie", cookies.toString());
				log.debug("\twith cookie {}", cookies.toString());
			}
			// 设置指定http-头
			if (pm.hasHeader()) {
				for (Entry<String, String> ahm : pm.getHeaderParams().entrySet()) {
					post.setHeader(ahm.getKey(), ahm.getValue());
					log.debug("\twith header {}={}", ahm.getKey(), ahm.getValue());
				}
			}
			// 设置通用参数
			List<NameValuePair> paramList = new ArrayList<NameValuePair>();
			if (pm.hasCommon()) {
				for (Entry<String, String> apm : pm.getCommonParams().entrySet()) {
					paramList.add(new BasicNameValuePair(apm.getKey(), apm.getValue()));
					log.debug("\twith param {}={}", apm.getKey(), apm.getValue());
				}
			}
			post.setEntity(new UrlEncodedFormEntity(paramList, pm.getCharset()));

			HttpResponse res = client.execute(post);
			int code = res.getStatusLine().getStatusCode();
			if (code == 200) {
				Header hd = res.getLastHeader("Content-Encoding");
				if (hd != null && hd.getValue().equals("gzip")) {
					res.setEntity(new GzipDecompressingEntity(res.getEntity()));
				}
				return EntityUtils.toString(res.getEntity(), pm.getCharset()).trim();
			}
//			NetMonitor.log(code, url);
		} catch (Exception e) {
//			NetMonitor.log(e, url);
		} finally {
			HttpClientUtils.closeQuietly(client);
		}
		return "";
	}

	/**
	 * 计算网页大小，单位:K
	 * 
	 * @param doc
	 *            Jsoup文档
	 * @return 字节
	 */
	public static int getPageSize(Document doc) {
		return doc.html().getBytes().length / 1024;
	}

	/**
	 * 网络资源是否正常
	 * 
	 * @param url
	 *            网络资源地址
	 * @return
	 */
	public static boolean is200(final String url) {
		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) new URL(url).openConnection();
			return (con.getResponseCode() == 200);
		} catch (Exception e) {
			return false;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}
}
