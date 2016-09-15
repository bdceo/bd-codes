package com.bdsoft.bdceo.jvm.bc;

public class InvokeDynamicTest {
	
}
//import java.lang.invoke.CallSite;
//import java.lang.invoke.ConstantCallSite;
//import java.lang.invoke.MethodHandle;
//import java.lang.invoke.MethodHandles;
//import java.lang.invoke.MethodType;
//
//public class InvokeDynamicTest {
//
//	public static void main(String[] args) throws Throwable {
//		INDY_BootstrapMethod().invokeExact("hahahhhh");
//	}
//
//	private static MethodHandle INDY_BootstrapMethod() throws Throwable {
//		CallSite cs = (CallSite) MH_BootstramMethod().invokeWithArguments(
//				MethodHandles.lookup(),
//				"testMethod",
//				MethodType.fromMethodDescriptorString("(Ljava/lang/String;)V",
//						null));
//		return cs.dynamicInvoker();
//	}
//
//	private static MethodHandle MH_BootstramMethod() throws Throwable {
//		// 实现对 到 BootstrapMethod 方法的动态调用
//		return MethodHandles.lookup().findStatic(InvokeDynamicTest.class,
//				"BootstrapMethod", MT_BootstramMethod());
//	}
//
//	private static MethodType MT_BootstramMethod() throws Throwable {
//		// 定义 BootstrapMethod 方法的参数列表及返回类型
//		return MethodType
//				.fromMethodDescriptorString(
//						"(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;",
//						null);
//	}
//
//	public static CallSite BootstrapMethod(MethodHandles.Lookup lookup,
//			String name, MethodType mt) throws Throwable {
//		// 通用 动态方法调用
//		return new ConstantCallSite(lookup.findStatic(InvokeDynamicTest.class,
//				name, mt));
//	}
//
//	public static void testMethod(String s) {
//		System.out.println("hello string-" + s);
//	}
//
//}
