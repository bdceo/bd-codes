package com.bdsoft.bdceo.ibatis;

import java.io.InputStream;
import java.util.HashMap;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.bdsoft.bdceo.ibatis.bean.User;
import com.bdsoft.bdceo.ibatis.dao.UserMapper;

class MyBatisUtil {

	private static final String MYBATIS_CFG = "mybatis.xml";
	private static SqlSessionFactory sqlSessionFactory = null;

	static {
		try {
			InputStream ins = Resources.getResourceAsStream(MYBATIS_CFG);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(ins);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getFactory() {
		return sqlSessionFactory;
	}

}

public class Main {

	public static void main(String[] args) {
		baseTest();

	}

	public static void baseTest() {
		SqlSession session = MyBatisUtil.getFactory().openSession();
		try {
			// 推荐方式
			UserMapper um = session.getMapper(UserMapper.class);
			User user = um.loadById(14);
			System.out.println("推荐方式" + user);

			// 不推荐方式，不安全
			user = (User) session.selectOne(
					"com.bdsoft.bdceo.ibatis.dao.UserMapper.loadById",
					new Integer(15));
			System.out.println(" 不推荐方式，不安全" + user);

			// 基于注解的sql映射
			user = um.getUser(17);
			System.out.println("基于注解的sql映射" + user);

			// 返回hashmap
			HashMap<String, Object> umap = um.load(16);
			System.out.println("返回hashmap" + umap);

			// 增
			user = new User("bdcto", "jjjjjj");
			um.save(user);

			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
