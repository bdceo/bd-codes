package com.bdsoft.y2012.m11;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class C26Test {

	public static String[] k1 = new String[] { "急招", "紧急招聘", "招聘", "直招",
			"官方招聘", "诚聘英才", "诚聘", "微招聘", "招人", "招聘启事", "招人启示", "虚位以待", "高薪诚聘",
			"招兵买马" };

	public static String[] k2 = new String[] { "地产", "金融", "投资", "融资", "投融资",
			"银行", "基金", "风险控制", "媒体", "传媒", "文化", "房地产", "物流", "教育", "互联网",
			"电商", "电子商务", "IT", "科技", "网络", "总监", "副总监", "总经理", "经理", "研发工程师",
			"开发工程师", "分析师", "大客户", "系统架构", "架构师", "项目经理", "研发经理", "首席", "主管",
			"资深", "审计", "财务", "会计", "法务", "市场", "销售", "高管", "总裁", "副总裁", "HR",
			"营销", "策划", "顾问", "高级", "硬件", "java", "php", "C#", "C++", "DBA",
			"测试", "运维", "产品经理", "设计", "年以上", "全职" };

	public static String[] k3 = new String[] { "邮箱", "发至邮箱", "mail", "email",
			"sina", "163", "162", "sohu", "qq", "gmail", "com.cn", "com", "cn",
			"投递至", "简历", "个人简历", "投简历" };

	public static void main(String[] args) {
		int s1 = k1.length;
		int s2 = k2.length;
		int s3 = k3.length;
		// int s = s1 + s2 + s3;
		// C cs = new C(s, 3);
		// System.out.println(cs.toString());

		Map<String, Long> keyMap = new LinkedHashMap<String, Long>();
		for (int a = 0; a < s1; a++) {
			for (int b = 0; b < s2; b++) {
				for (int c = 0; c < s3; c++) {
					StringBuffer sb = new StringBuffer();
					sb.append(k1[a]);
					sb.append(" " + k2[b]);
					sb.append(" " + k3[c]);
					keyMap.put(sb.toString(), new Long(0));
				}
			}
		}
		int i = 0;
		System.out.println(keyMap.size());
		for (Entry<String, Long> en : keyMap.entrySet()) {
			i++;
			System.out.println(i + " \t" + en.getKey());
		}

	}
}
