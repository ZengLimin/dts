package com.zenglm.dts.mq;

import javax.jms.JMSException;



public class MsgListener {

	public void onMessage(Object message) throws JMSException {
		System.out.println(Thread.currentThread() + " " + message);
		throw new JMSException(null);
	}
	

}
