package com.bdsoft.gkh.flyweight;

import java.util.HashMap;

public class Flywieght1 {

	/**
	 * 享元(共享小模块)模式： 避免拥有大量相同内容的小类开销，使程序共享一个类对象
	 * 
	 * 享元多而小，并且会在以后经常使用，但是无法估计使用哪一个
	 * 
	 * 提高内存使用率，优化程序
	 */
	public static void main(String[] args) {
		Word w1 = WordPool.getWord("liudehua", "1");
		Word w2 = WordPool.getWord("zhouhuajian", "2");
		Word w3 = WordPool.getWord("liudehua", "1");
		Word w4 = WordPool.getWord("liudehua", "1");
	}

}

// 享元模式类
class Word {
	public String content;
	public String key;

	public Word(String c, String k) {
		this.content = c;
		this.key = k;
		System.out.println("构造函数-word");
	}
}

// 享元模式管理类，池子的概念
class WordPool {

	private static HashMap<String, Word> pool = new HashMap<String, Word>();

	public static Word getWord(String key, String content) {
		Word word = pool.get(key);
		if (word == null) {
			word = new Word(content, key);
			pool.put(key, word);
		}
		return word;
	}

}