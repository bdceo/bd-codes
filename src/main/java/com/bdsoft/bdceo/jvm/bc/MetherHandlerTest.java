package com.bdsoft.bdceo.jvm.bc;

public class MetherHandlerTest {
	
}
//import java.lang.invoke.MethodHandle;
//import java.lang.invoke.MethodHandles;
//import java.lang.invoke.MethodType;
//
//public class MetherHandlerTest {
//
//	static class ClassA {
//		public void println(String str) {
//			System.out.println("classA-" + str);
//		}
//	}
//
//	public static void main(String[] args) throws Throwable {
//		Object obj = System.currentTimeMillis() % 2 == 0 ? System.out
//				: new ClassA();
//		getPrintlnHandler(obj).invokeExact("bdceo");
//	}
//
//	private static MethodHandle getPrintlnHandler(Object obj) throws Throwable {
//		MethodType mt = MethodType.methodType(void.class, String.class);
//		return MethodHandles.lookup()
//				.findVirtual(obj.getClass(), "println", mt).bindTo(obj);
//	}
//
//}
