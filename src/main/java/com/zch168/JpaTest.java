package com.zch168;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * 泛型测试
 * 
 * @author bdceo 
 * @date 2016-8-17 下午12:16:09
 * @version V1.0
 */
public class JpaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new UserDao();
	}

}

class UserDao extends CommonJpa<JpaTest> {
	public UserDao() {
		super();
	}
}

class CommonJpa<T> implements ICommonJpa<T> {

	Class<?> target;

	public CommonJpa() {
		Class clz = this.getClass();
		System.out.println(clz);

		Type type = this.getClass().getGenericSuperclass();
		System.out.println(type);

		Type[] pts = ((ParameterizedType) type).getActualTypeArguments();
		for (Type t : pts) {
			System.out.println(t);
		}
		target = (Class<?>) pts[0];
	}
}

interface ICommonJpa<T> {

}
