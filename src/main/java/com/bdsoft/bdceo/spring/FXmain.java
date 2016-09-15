package com.bdsoft.bdceo.spring;

import java.lang.reflect.Proxy;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.cglib.proxy.Enhancer;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.DelegatePerTargetObjectIntroductionInterceptor;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.aop.support.Pointcuts;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.beans.factory.config.PropertyOverrideConfigurer;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.bdsoft.bdceo.spring.aop.ClassLevelAnnotation;
import com.bdsoft.bdceo.spring.aop.Developer;
import com.bdsoft.bdceo.spring.aop.Executeable;
import com.bdsoft.bdceo.spring.aop.GeneracTargetObject;
import com.bdsoft.bdceo.spring.aop.ICounter;
import com.bdsoft.bdceo.spring.aop.IDeveloper;
import com.bdsoft.bdceo.spring.aop.ITask;
import com.bdsoft.bdceo.spring.aop.ITester;
import com.bdsoft.bdceo.spring.aop.MethodLevelAnnotation;
import com.bdsoft.bdceo.spring.aop.MockTask;
import com.bdsoft.bdceo.spring.aop.PerformanceMethodInterceptor;
import com.bdsoft.bdceo.spring.aop.ResourceSetupBeforeAdvice;
import com.bdsoft.bdceo.spring.aop.TargetCaller;
import com.bdsoft.bdceo.spring.aop.TestFeatureIntroductionInterceptor;
import com.bdsoft.bdceo.spring.aop.Tester;
import com.bdsoft.bdceo.spring.aop.aspectj.Foo;
import com.bdsoft.bdceo.spring.aop.aspectj.PerformanceTraceAspect;
import com.bdsoft.bdceo.spring.aop.demo.AOPDemo;
import com.bdsoft.bdceo.spring.appfx.BarImpl;
import com.bdsoft.bdceo.spring.appfx.DatePropertyEditor;
import com.bdsoft.bdceo.spring.appfx.DowJonesNewsListener;
import com.bdsoft.bdceo.spring.appfx.DowJonesNewsPersister;
import com.bdsoft.bdceo.spring.appfx.FXNewsProvider;
import com.bdsoft.bdceo.spring.appfx.IBar;
import com.bdsoft.bdceo.spring.appfx.IFXNewsPersister;
import com.bdsoft.bdceo.spring.appfx.MethodEventPubllish;
import com.bdsoft.bdceo.spring.appfx.MockNewsPersister;
import com.bdsoft.bdceo.spring.appfx.NextDayDateFactoryBean;
import com.bdsoft.bdceo.spring.appfx.PasswordDecodePostProcessor;
import com.bdsoft.bdceo.spring.appfx.TestNewsPersister;
import com.bdsoft.bdceo.spring.appfx.cglibproxy.RequestCallback;
import com.bdsoft.bdceo.spring.appfx.cglibproxy.Requestable;
import com.bdsoft.bdceo.spring.appfx.jdkproxy.RequestInvocationHandler;
import com.bdsoft.bdceo.spring.appfx.proxy.ISubject;
import com.bdsoft.bdceo.spring.appfx.proxy.SubjectImp;
import com.bdsoft.bdceo.spring.appfx.proxy.SubjectImpProxy;

public class FXmain {

	// 基于BeanFactory的容器初始化
	public static BeanFactory iocInit() {
		return new XmlBeanFactory(new ClassPathResource("spring.xml"));
	}

	// 基于Application的容器初始化
	public static ApplicationContext iocInit(String app) {
		return new ClassPathXmlApplicationContext("spring.xml");
	}

	public static void main(String[] args) {
		// 基于BeanFactory的ioc容器测试
		// iocInitTest();
		// factoryTest();
		// prototypeTest();
		// iocStart();
		// iocDoInstance();

		// 基于ApplicationContext的ioc容器测试
		// iocEvent();

		// Spring-AOP：基本概念测试
		// aopBase();
		// aopPointcut();
		// aopAdvice();
		// aopAspect();
		// aopWeave();
		// aopTarget();

		// Spring-AOP：AspectJ和Schema测试
		// aopAspectJ();
		// aopSchema();
	}

	// 基于Schema的Spring-aop
	public static void aopSchema() {
		// 参见com.bdsoft.bdceo.spring.aop.schema包和配置文件
		ApplicationContext app = iocInit("");

		// demo测试
		AOPDemo demo = null;
		try {
			Object target = ((Advised) app.getBean("aopDemo"))
					.getTargetSource().getTarget();
			demo = (AOPDemo) target;
			demo.login("bdceo", "");

			int c = ((ICounter) target).getCounter();
			System.out.println("计数：" + c);
		} catch (Exception e) {
			System.out.println("业务层捕获异常：" + e.getMessage());
		}

	}

	// 基于AspectJ的Spring-aop
	public static void aopAspectJ() {
		// 基于编程方式的AspectJ织入
		AspectJProxyFactory weaver = new AspectJProxyFactory();
		weaver.setProxyTargetClass(true);
		weaver.setTarget(new Foo());
		weaver.addAspect(PerformanceTraceAspect.class);
		Object proxy = weaver.getProxy();
		((Foo) proxy).method1();
		((Foo) proxy).method2();

		// 自动代理织入，参见spring-appfx-aop2.xml
		ApplicationContext app = iocInit("");
		proxy = app.getBean("fooTarget");
		((Foo) proxy).method1();
		((Foo) proxy).method2();

		// 基于XSD形式的自动代理，参见配置文件
	}

	// aop-target
	public static void aopTarget() {
		ApplicationContext app = iocInit("");
		try {
			// SingletonTargetSource
			Object proxy = app.getBean("singleProxy");
			Object obj1 = ((Advised) proxy).getTargetSource().getTarget();
			Object obj2 = ((Advised) proxy).getTargetSource().getTarget();
			System.out.println("SingletonTargetSource:" + (obj1 == obj2));

			// PrototypeTargetSource
			proxy = app.getBean("prototypeProxy");
			obj1 = ((Advised) proxy).getTargetSource().getTarget();
			obj2 = ((Advised) proxy).getTargetSource().getTarget();
			System.out.println("PrototypeTargetSource:" + (obj1 == obj2));

			// HotSwappableTargetSource
			proxy = app.getBean("hotswapProxy");
			obj1 = ((Advised) proxy).getTargetSource().getTarget();
			HotSwappableTargetSource hsts = (HotSwappableTargetSource) app
					.getBean("hotswapTarget");
			Object oldObj = hsts.swap(new ITester() {
				@Override
				public void testSoftware() {
				}

				@Override
				public boolean isBusyAsTester() {
					return false;
				}
			});
			obj2 = ((Advised) proxy).getTargetSource().getTarget();
			System.out.println(oldObj == obj1);
			System.out.println(oldObj == obj2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// aop-weave
	public static void aopWeave() {
		// 织入器：ProxyFactory
		// 基于接口的代理，构造函数，接受目标对象类的实例
		ProxyFactory weaver = new ProxyFactory(new MockTask());
		// 指定代理的接口类型，默认实现了接口的目标类，不设置此属性也可正常执行代理
		weaver.setInterfaces(new Class[] { ITask.class });
		// 强制使用cglib实现代理
		// weaver.setProxyTargetClass(true);
		// 指定advisor
		NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
		advisor.setMappedName("execute");
//		advisor.setAdvice(new PerformanceMethodInterceptor());
		weaver.addAdvisor(advisor);

		Object proxy = weaver.getProxy();
		((ITask) proxy).execute(null);
		System.out.println(proxy.getClass());

		// 基于类的代理
		weaver = new ProxyFactory(new Executeable());
		weaver.addAdvisor(advisor);
		proxy = weaver.getProxy();
		((Executeable) proxy).execute(null);
		System.out.println(proxy.getClass());

		// Introduction的织入
		weaver = new ProxyFactory(new Developer());
		weaver.setInterfaces(new Class[] { IDeveloper.class, ITester.class });
//		TestFeatureIntroductionInterceptor advice = new TestFeatureIntroductionInterceptor();
//		weaver.addAdvice(advice);
		proxy = weaver.getProxy();
		((ITester) proxy).testSoftware();
		((IDeveloper) proxy).developSoftware();
		System.out.println(proxy.getClass());

		// ProxyFactoryBean对Introduction型的织入
		ApplicationContext app = iocInit("");
		Object proxy1 = app.getBean("taskProxy4");
		Object proxy2 = app.getBean("taskProxy4");

		System.out.println(((ICounter) proxy1).getCounter());
		((ICounter) proxy1).resetCounter();
		System.out.println(((ICounter) proxy1).getCounter());
		System.out.println(((ICounter) proxy2).getCounter());
	}

	// aop-aspect
	public static void aopAspect() {
		// 对应spring中的advisor
		NameMatchMethodPointcut pointcut = null;
		ResourceSetupBeforeAdvice advice = null;
//		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut,advice);
		// advisor.setPointcut(pointcut);
		// advisor.setAdvice(advice);

		//NameMatchMethodPointcutAdvisor advisor2 = new NameMatchMethodPointcutAdvisor(		advice);
		//advisor2.setMappedName("methodName");

		//RegexpMethodPointcutAdvisor advisor3 = new RegexpMethodPointcutAdvisor(	advice);
		//advisor3.setPattern(".*doGet.*");
	}

	// aop-advice
	public static void aopAdvice() {
		// 参见：com.bdsoft.bdceo.spring.aop包下的模拟实现类

		// before-advice: ResourceSetupBeforeAdvice
		// exception-advice: ExceptionBarrierThrowsAdvice
		// after-advice: TaskexecutionAfterReturningAdvice
		// around-advice: PerformanceMethodInterceptor

		// 特殊： Introduction类advice演示
		ITester delegate = new Tester();
		DelegatingIntroductionInterceptor dii = new DelegatingIntroductionInterceptor(
				delegate);

		DelegatePerTargetObjectIntroductionInterceptor dptoii = new DelegatePerTargetObjectIntroductionInterceptor(
				Tester.class, ITester.class);
	}

	// aop-pointcut
	public static void aopPointcut() {
		// 只对“方法名”进行匹配
		NameMatchMethodPointcut mmmp = new NameMatchMethodPointcut();
		mmmp.setMappedName("request");
		mmmp.setMappedNames(new String[] { "response", "destroy", "do*" });

		// 对整个“方法签名”进行匹配,所以正则开头一般为.*
		JdkRegexpMethodPointcut jrmp = new JdkRegexpMethodPointcut();
		jrmp.setPattern(".*aop.*");
		jrmp.setPatterns(new String[] { ".*do.*", ".*handler" });

		// 基于注解-类
		AnnotationMatchingPointcut amp = AnnotationMatchingPointcut
				.forClassAnnotation(ClassLevelAnnotation.class);
		// 注解-基于方法
		amp = AnnotationMatchingPointcut
				.forMethodAnnotation(MethodLevelAnnotation.class);
		// 同时限定类和方法
		amp = new AnnotationMatchingPointcut(ClassLevelAnnotation.class,
				MethodLevelAnnotation.class);

		// 对pointcut进行逻辑运算
		ComposablePointcut cp1 = new ComposablePointcut();
		ComposablePointcut cp2 = new ComposablePointcut();
		cp1.union(cp2);
		cp1.intersection(cp2);
		// pointcut工具类
		Pointcuts.union(cp1, cp2);
		Pointcuts.intersection(cp1, cp2);

		// 基于调用流程的控制拦截
		ControlFlowPointcut cfp = new ControlFlowPointcut(TargetCaller.class,
				"callMethod");
		TargetCaller tc = new TargetCaller(new GeneracTargetObject());
		tc.callMethod();
	}

	// aop，代理的几种实现
	public static void aopBase() {
		// 设计模式-代理模式（静态代理）
		System.out.println("设计模式-代理模式");
		ISubject sub = new SubjectImpProxy(new SubjectImp());
		sub.request();

		// JDK-动态代理,通过Proxy生成代理类
		System.out.println("\nJDK-动态代理");
		// Proxy:提供用于创建动态代理类和实例的静态方法，它还是由这些方法创建的所有动态代理类的超类。
		sub = (ISubject) Proxy.newProxyInstance(
		// 指定Classloader
				FXmain.class.getClassLoader(),
				// 指定要生成代理的接口，等同于 ISubject.class.getInterfaces()
				new Class[] { ISubject.class },
				// 初始化动态代理类，实现InvocationHandler接口的实现类
				new RequestInvocationHandler(new SubjectImp()));
		sub.request();

		// cglib-动态字节码生成
		System.out.println("\ncglib-动态字节码生成");
		Enhancer hancer = new Enhancer();
		hancer.setSuperclass(Requestable.class);
		hancer.setCallback(new RequestCallback());
		Requestable req = (Requestable) hancer.create();
		req.request();
	}

	// 基于容器的事件发布
	public static void iocEvent() {
		ApplicationContext app = iocInit("");
		MethodEventPubllish pub = (MethodEventPubllish) app
				.getBean("evtPublish");
		pub.methodToMonitor();
	}

	// ioc实例化辅助支持
	public static void iocDoInstance() {
		// BeanFactory方式启动
		ConfigurableListableBeanFactory beanFactory = new XmlBeanFactory(
				new ClassPathResource("spring.xml"));
		beanFactory.addBeanPostProcessor(new PasswordDecodePostProcessor());

		DowJonesNewsListener newsListener = (DowJonesNewsListener) beanFactory
				.getBean("djNewsListener");
		System.out.println(newsListener.getEncodedPassword());

		// 容器退出之前，销毁singleton的实例
		beanFactory.destroySingletons();
		// 2.0,销毁实现
		// ((AbstractApplicationContext)beanFactory).registerShutdownHook();
	}

	// ioc启动辅助支持
	public static void iocStart() {
		// BeanFactory方式启动
		ConfigurableListableBeanFactory beanFactory = new XmlBeanFactory(
				new ClassPathResource("spring.xml"));

		// 读取外部properties文件属性值填充xml中的bean，property
		PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
		propertyPlaceholderConfigurer.setLocation(new ClassPathResource(
				"config.properties"));
		// 应用postProcessBeanFactory
		propertyPlaceholderConfigurer.postProcessBeanFactory(beanFactory);

		// 读取外部properties文件，覆盖xml中bean指定的property值
		PropertyOverrideConfigurer propertyOverrideConfigurer = new PropertyOverrideConfigurer();
		propertyOverrideConfigurer.setLocation(new ClassPathResource(
				"spring-adjustment.properties"));
		propertyOverrideConfigurer.postProcessBeanFactory(beanFactory);

		// CustomEditorConfigurer，对bean的property值，做类型转换支持
		CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();
		Map customEditors = new HashMap();
		customEditors.put(Date.class, new DatePropertyEditor());
		customEditorConfigurer.setCustomEditors(customEditors);
		customEditorConfigurer.postProcessBeanFactory(beanFactory);

		FXNewsProvider newsProvider = (FXNewsProvider) beanFactory
				.getBean("newsProvider");
		System.out.println(newsProvider.getClientId());
		System.out.println(newsProvider.getBaseUrl());
		System.out.println(newsProvider.getAddDate().toLocaleString());
	}

	// 关于prototype测试
	public static void prototypeTest() {
		BeanFactory ioc = iocInit();
		// 方法注入
		IFXNewsPersister persister = (MockNewsPersister) ioc
				.getBean("mockNewsPersister");
		persister.persistNews();
		persister.persistNews();
		// BeanFactoryAware获取BeanFactory自动注入实现
		persister = (DowJonesNewsPersister) ioc.getBean("djNewsPersister");
		persister.persistNews();
		persister.persistNews();
		// ObjectFactoryCreatingFactoryBean，间接通过容器管理对象实现
		persister = (TestNewsPersister) ioc.getBean("testNewsPersister");
		persister.persistNews();
		persister.persistNews();
		// 方法替换
		FXNewsProvider provider = (FXNewsProvider) ioc.getBean("newsProvider");
		provider.getAndPersistNews();
	}

	// 工厂话题测试
	public static void factoryTest() {
		BeanFactory ioc = iocInit();
		IBar bar = (BarImpl) ioc.getBean("bar");
		bar.say();
		bar = (BarImpl) ioc.getBean("barN");
		bar.say();
		bar = (BarImpl) ioc.getBean("barD");
		bar.say();
		Object obj = ioc.getBean("nextDayDate");
		System.out.println(obj instanceof Date);
		obj = ioc.getBean("&nextDayDate");
		System.out.println(obj instanceof FactoryBean);
		System.out.println(obj instanceof NextDayDateFactoryBean);
	}

	// 几种ioc容器加载方式演示
	public static void iocInitTest() {
		DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();
		BeanFactory container = null;
		// container= bindViaProp(beanRegistry);
		// container = bindViaCode(beanRegistry);
		container = bindViaXml(beanRegistry);
		// bindBiaAno();

		FXNewsProvider newsProvider = (FXNewsProvider) container
				.getBean("newsProvider");
		newsProvider.getAndPersistNews();
	}

	// 通过注解加载
	public static void bindBiaAno() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"classpath:/spring-ano.xml");
		FXNewsProvider newsProvider = (FXNewsProvider) ctx
				.getBean("FXNewsProvider");
		newsProvider.getAndPersistNews();
		System.exit(1);
	}

	// 通过xml文件方式加载
	public static BeanFactory bindViaXml(BeanDefinitionRegistry registry) {
		// XmlBeanDefinitionReader reader = new
		// XmlBeanDefinitionReader(registry);
		// reader.loadBeanDefinitions("classpath:/spring.xml");
		// return (BeanFactory) registry;

		// 以上，等同于下句
		return new XmlBeanFactory(new ClassPathResource("spring.xml"));
	}

	// 通过属性文件方式 加载
	public static BeanFactory bindViaProp(BeanDefinitionRegistry registry) {
		PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(
				registry);
		reader.loadBeanDefinitions("classpath:/spring.properties");
		return (BeanFactory) registry;
	}

	// 通过编码方式实现ioc容器初始化
	public static BeanFactory bindViaCode(BeanDefinitionRegistry registry) {
//		AbstractBeanDefinition newsProvider = new RootBeanDefinition(
//				FXNewsProvider.class, true);
//		AbstractBeanDefinition newsListener = new RootBeanDefinition(
//				DowJonesNewsListener.class, true);
//		AbstractBeanDefinition newsPersister = new RootBeanDefinition(
//				DowJonesNewsPersister.class, true);
//
//		// 将bean定义注册到容器中
//		registry.registerBeanDefinition("newsProvider", newsProvider);
//		registry.registerBeanDefinition("newsListener", newsListener);
//		registry.registerBeanDefinition("newsPersister", newsPersister);

		// 1，通过构造函数注入
		// ConstructorArgumentValues argValues = new
		// ConstructorArgumentValues();
		// argValues.addIndexedArgumentValue(0, newsListener);
		// argValues.addIndexedArgumentValue(1, newsPersister);
		// newsProvider.setConstructorArgumentValues(argValues);

		// 2，通过setter方式注入
//		MutablePropertyValues propertyValues = new MutablePropertyValues();
//		propertyValues.addPropertyValue(new PropertyValue("newsPersister",
//				newsPersister));
//		propertyValues.addPropertyValue(new PropertyValue("newsListener",
//				newsListener));
//		newsProvider.setPropertyValues(propertyValues);

		return (BeanFactory) registry;
	}
}
