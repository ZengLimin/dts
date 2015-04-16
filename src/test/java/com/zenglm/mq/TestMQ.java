package com.zenglm.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class TestMQ {
	@Autowired
	private JmsTemplate jmsTemplate;

	@Test
	public void test() throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			jmsTemplate.convertAndSend("message..." + i);
			Thread.sleep(1000);
		}
	}

}
