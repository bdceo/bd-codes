package com.bdsoft.bdceo.jvm.bc;


public class DynamicDispatch {

	static abstract class Human {
		protected abstract void sayHello();
	}

	static class Man extends Human {
		protected void sayHello(){
			System.out.println("man say hello");
		}
	}

	static class Woman extends Human {
		protected void sayHello(){
			System.out.println("woman say hello");
		}
	}
	
	// 动态分派演示：方法重写的本质，在运行期根据实际类型，确定分派的目标方法
	public static void main(String[] args) {
		Human man= new Man();
		Human wom = new Woman();
		man.sayHello();
		wom.sayHello();
		
		man = new Woman();
		man.sayHello();
	}

}
