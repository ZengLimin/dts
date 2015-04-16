package com.zenglm.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zenglm.dts.model.UserService;

public class TestSpring {
	@Test
	public void testContext() {
		ApplicationContext context = 
	             new ClassPathXmlApplicationContext("spring.xml");
		UserService sf = context.getBean(UserService.class);
		System.out.println(sf);
	}
}
