package com.zenglm.cxf;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.soap.SOAPFaultException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.chinatelecom.schema.ctcc.sms.v2_1.SmsMessage;
import cn.com.chinatelecom.wsdl.ctcc.common.v2_0.faults.PolicyException;
import cn.com.chinatelecom.wsdl.ctcc.common.v2_0.faults.ServiceException;
import cn.com.chinatelecom.wsdl.ctcc.sms.notification.v2_1._interface.SmsNotification;
import cn.com.chinatelecom.wsdl.ctcc.sms.send.v2_1._interface.SendSms;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-cxf.xml" })
public class TestCxf {
	@Autowired
	private SendSms sendSms;
	@Test
	public void test() {
		//com.sun.xml.bind.unmarshaller.DOMScanner
		List<String> addresses = new ArrayList<String>();
		addresses.add("tel:18000574011");//13362096306,18628151627
		//addresses.add("tel:18628151627");
		try {
			System.out.println(sendSms.sendSms(addresses, "192300", null,
					"广元测试短信【天阙科技】", null));
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (PolicyException e) {
			e.printStackTrace();
		} catch (SOAPFaultException e) {
			Throwable cause = e.getCause();
			if (cause instanceof ServiceException) {
				ServiceException serviceException = (ServiceException) cause;
				cn.com.chinatelecom.schema.ctcc.common.v2_1.ServiceException info = serviceException
						.getFaultInfo();
				System.out.println(info.getMessageId() + " " + info.getText()
						+ " " +
						info.getVariables());

			}
		}
	}

	@Autowired
	private SmsNotification smsNotification;

	@Test
	public void testSmsNotification() {
		SmsMessage msg = new SmsMessage();
		msg.setMessage("test");
		msg.setSenderAddress("18000574011");
		msg.setSmsServiceActivationNumber("192300");
		smsNotification.notifySmsReception("192300054fe80310950c", msg);
	}

}
