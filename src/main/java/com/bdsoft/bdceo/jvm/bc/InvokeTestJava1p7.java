package com.bdsoft.bdceo.jvm.bc;

public class InvokeTestJava1p7 {
	
}
//import java.lang.invoke.MethodHandles;
//import java.lang.invoke.MethodType;
//
//public class InvokeTestJava1p7 {
//
//	class A {
//		void sayHello() {
//			System.out.println("A say hello");
//		}
//	}
//
//	class B extends A {
//		void sayHello() {
//			System.out.println("B say hello");
//		}
//	}
//
//	// 子类中动态实现调用父类，祖父类中的方法
//	class C extends B {
//		void sayHello() {
//			try {
//				MethodType mt = MethodType.methodType(void.class);
//
//				MethodHandles.lookup().findVirtual(A.class, "sayHello", mt)
//						.invoke(new A());
//
//				MethodHandles.lookup().findVirtual(B.class, "sayHello", mt)
//						.invoke(new B());
//			} catch (Throwable e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public static void main(String[] args) {
//		(new InvokeTestJava1p7().new C()).sayHello();
//	}
//
//}
