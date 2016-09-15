package com.bdsoft.bdceo.spring.aop;

//import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class TestFeatureIntroductionInterceptor //extends DelegatingIntroductionInterceptor 
implements ITester {

	private boolean busyAsTester;

//	@Override
//	public Object invoke(MethodInvocation mi) throws Throwable {
//		if (isBusyAsTester() && mi.getMethod().getName().equals("developSoftware")) {
//			throw new RuntimeException("i am so busy!");
//		}
//		return super.invoke(mi);
//	}

	@Override
	public void testSoftware() {
		System.out.println("i am a tester");
	}

	@Override
	public boolean isBusyAsTester() {
		return busyAsTester;
	}

	public void setBusyAsTester(boolean busyAsTester) {
		this.busyAsTester = busyAsTester;
	}

}
