package com.zenglm.dts.mq;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

public class MsgConverter implements MessageConverter {

	@Override
	public Message toMessage(Object object, Session session)
			throws JMSException, MessageConversionException {
		ObjectMessage omsg = session.createObjectMessage();
		omsg.setObject((Serializable) object);
		return omsg;
	}

	@Override
	public Object fromMessage(Message message) throws JMSException,
			MessageConversionException {
		if (message instanceof ObjectMessage) {
			return ((ObjectMessage) message).getObject();
		}
		return null;
	}

}
