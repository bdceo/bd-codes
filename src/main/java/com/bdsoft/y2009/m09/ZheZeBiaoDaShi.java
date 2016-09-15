package com.bdsoft.y2009.m09;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZheZeBiaoDaShi {

	public static void main(String[] args) {
		// 1,��ѯ��
		String str = "abc efg ABC"; // ����ַ�
		String regEx = "aBc"; // �����������ʾ�Ƿ��b��f
		String regEx2 = "a|e|AB";
		Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);// ���Դ�Сд
		Pattern p2 = Pattern.compile(regEx2);
		Matcher m = p.matcher(str);
		System.out.println(m.matches());
		Matcher m2 = p2.matcher(str);
		System.out.println(m2.matches());

		boolean rs = m.find();
		boolean rs2 = m2.find();
		System.out.println("��ѯ���" + rs2 + "\t���Դ�Сд��ѯ��" + rs);
		// ���str����regEx����ôrsΪtrue������Ϊflase��������ڲ���ʱ���Դ�Сд��
		// �����д��Pattern p=Pattern.compile(regEx,Pattern.CASE_INSENSITIVE);

		// 2����ȡ��
		String str2 = "c:\\dir1\\dir2\\name.txt";
		String regEx3 = ".+\\\\(.+)";

		Pattern p3 = Pattern.compile(regEx3);
		Matcher m3 = p3.matcher(str2);
		boolean rs3 = m3.find();
		System.out.print("��ȡ���" + rs3 + "\t��ȡ���ַ�Ϊ��");
		for (int i = 1; i <= m3.groupCount(); i++) {
			System.out.println(m3.group(i));
		}

		// 3���ָ
		// ������ʽд����
		String regEx4 = "::";
		Pattern p4 = Pattern.compile(regEx4);
		String[] r1 = p4.split("xd::abc::edf");
		for (int i = 0; i < r1.length; i++) {
			System.out.print(r1[i] + "\t");
		}
		System.out.println("");
		// �ָ��ַ�String��ķ�����
		String str4 = "xd::abc::edf,dd.ddd";
		String[] r2 = str4.split("::");
		for (int i = 0; i < r2.length; i++) {
			System.out.print(r2[i] + "\t");
		}

		// 4���滻ɾ��
		String regEx5 = "a+";// ��ʾһ�����a
		Pattern p5 = Pattern.compile(regEx5);
		Matcher m5 = p5.matcher("aaabbced a ccdeaa");
		String str5 = m5.replaceAll("A");
		// ���д�ɿ��ַ��ȿɴﵽɾ��Ĺ���
		String str6 = m5.replaceAll("");
		System.out.println("\nԭ�ַ�Ϊ��aaabbced a ccdeaa");
		System.out.println("�滻���" + str5);
		System.out.println("ɾ����" + str6);

		/*
		 * �ܽ᣺ 1��Pattern.compile()�в�����������ʽ 2��Pattern �����matcher()�в���ΪҪ������ַ�
		 * 3��Matcher �����find()���������ж�������ʽ 4��Matcher �����groupCount���ԡ��� 5��Matcher
		 * �����group()��������
		 */
		/*
		 * ���� \d ���� [0-9] ���� \D ���� [^0-9] ������ \s ���� [ \t\n\x0B\f\r] �հ���Ԫ \S ���� [^
		 * \t\n\x0B\f\r] �ǿհ���Ԫ \w ���� [a-zA-Z_0-9] ���ֻ���Ӣ���� \W ���� [^a-zA-Z_0-9]
		 * ��������Ӣ���� ^ ��ʾÿ�еĿ�ͷ $ ��ʾÿ�еĽ�β
		 */
	}
}
