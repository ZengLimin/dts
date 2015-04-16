package com.zenglm.dts.aop;

import org.apache.cxf.message.Message;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class ExceptionHandler {
	/**
	 * Owner 参数解释 Method method 执行的方法 Object[] args 方法参数 Object target 代理的目标对象
	 * Throwable throwable 产生的异常 Jan 18, 2010 3:21:46 PM
	 */
	//@Before("execution(*org.apache.cxf.interceptor.ClientFaultConverter.handleMessage(..))&& args(message)")
	//@Before("com.zenglm.cxf.SendSmsInterceptor.handleMessage(Message) && args(message)")
	//@Before("execution(* com.zenglm.cxf.SendSmsInterceptor.handleMessage(..))&& args(message)")
	@Before("execution(* org.apache.cxf.interceptor.ClientFaultConverter.handleMessage(..))&& args(message)")
	public void before(Message message) {
		System.out.println(111);
	}
}
