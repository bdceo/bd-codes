package com.bdsoft.y2014;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.bdsoft.utils.NetTool;

/**
 * Xpath 解析网页工具，复习总结
 * 
 * 依赖jar：xercesImpl-2.9.1.jar，nekohtml.jar
 * 
 * @author bdceo
 * 
 */
public class XpathTest {

	public static void main(String[] args) throws Exception {
		// baseTest();

		String url = "http://list.jd.com/670-671-672-0-0-0-0-0-0-0-1-1-1-1-1-72-4137-0.html";
		String encoding = "gbk";
		test(url, encoding);
	}

	public static void test(String url, String encoding) throws Exception {
		// 1，抓取网页，提供输入流
		InputStream in = NetTool.getContentStreamByPost(url, encoding);

		// 2，实例化Doc对象，供xpath解析
		Document doc = getDoc(in, encoding);

		// 3，准备xpath，解析，返回Node或NodeList
		String xpath = "//H3/text()";
		NodeList nodes = getNodes(doc, xpath);
		for (int i = 0; i < nodes.getLength(); i++) {
			System.out.println(nodes.item(i).getNodeValue());
		}

	}

	public static Node getNode(Document doc, String xpath) throws Exception {
		XPathFactory xfac = XPathFactory.newInstance();
		XPath xp = xfac.newXPath();
		XPathExpression exp = xp.compile(xpath);
		Object result = exp.evaluate(doc, XPathConstants.NODE);
		Node node = (Node) result;
		return node;
	}

	public static NodeList getNodes(Document doc, String xpath)
			throws Exception {
		XPathFactory xfac = XPathFactory.newInstance();
		XPath xp = xfac.newXPath();
		XPathExpression exp = xp.compile(xpath);
		Object result = exp.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		return nodes;
	}

	public static Document getDoc(InputStream in) throws Exception {
		DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
		fac.setNamespaceAware(true);
		DocumentBuilder docb = fac.newDocumentBuilder();
		Document doc = docb.parse(in);
		return doc;
	}

	public static Document getDoc(InputStream in, String encoding)
			throws Exception {
		DOMParser parser = new DOMParser();
		parser.setProperty(
				"http://cyberneko.org/html/properties/default-encoding",
				encoding);
		parser.setFeature("http://xml.org/sax/features/namespaces", false);
		BufferedReader br = new BufferedReader(new InputStreamReader(in,
				encoding));
		parser.parse(new InputSource(br));
		return parser.getDocument();

	}

	public static void baseTest() throws Exception {
		// 指定资源引用
		String p = "src/tmp/spring.xml";
		File f = new File(p);
		FileInputStream fis = new FileInputStream(f);

		DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
		fac.setNamespaceAware(true);
		DocumentBuilder docb = fac.newDocumentBuilder();

		// 初始化DOM结构
		// Document doc = docb.parse(f);
		// Document doc = docb.parse(new InputSource(fis));
		Document doc = docb.parse(fis);
		// Document doc = docb.parse(p);

		System.out.println(doc == null);

		// 准备xpath解析
		String xpath = "//bean/@class";
		XPathFactory xfac = XPathFactory.newInstance();
		XPath xp = xfac.newXPath();
		XPathExpression exp = xp.compile(xpath);

		// 执行解析
		Object result = exp.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		for (int i = 0; i < nodes.getLength(); i++) {
			System.out.println(nodes.item(i).getNodeValue());
		}

	}

	public static class BDNamespaceContext implements NamespaceContext {

		public String getNamespaceURI(String prefix) {
			return "";// XMLConstants.NULL_NS_URI;
		}

		public String getPrefix(String namespaceURI) {
			return null;
		}

		public Iterator getPrefixes(String namespaceURI) {
			return null;
		}

	}

}
