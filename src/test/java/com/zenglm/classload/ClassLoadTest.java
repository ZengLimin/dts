package com.zenglm.classload;

import java.lang.reflect.Method;

import org.junit.Test;

public class ClassLoadTest {

	@Test
	public void test() {
		ClassLoader loader = ClassLoadTest.class.getClassLoader();
		while (loader != null) {
			System.out.println(loader.toString());
			loader = loader.getParent();
		}
	}

	@Test
	public void testThread() throws Exception {
		MyClassload mc = new MyClassload("E:/classLoad");
		Thread.currentThread().setContextClassLoader(mc);
		Class<?> s = mc.loadClass("com.zenglm.dts.domain.Sample");
		Object o = s.newInstance();
		Method m = s.getMethod("test");
		m.invoke(o);
		System.out.println(o.getClass().getClassLoader());
	}

}