/**
 * 
 */
package com.zenglm.cxf;


import org.apache.cxf.binding.soap.interceptor.Soap11FaultInInterceptor;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.AbstractInDatabindingInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import cn.com.chinatelecom.wsdl.ctcc.common.v2_0.faults.ServiceException;

/**
 * 
 * @author 曾利民
 * @version 1.0.0
 * @since 2015年3月9日 上午9:32:21
 */
public class ExceptionInterceptor extends AbstractInDatabindingInterceptor {

	/**
	 * @param phase
	 */
	public ExceptionInterceptor() {
		super(Phase.UNMARSHAL);
		addAfter(Soap11FaultInInterceptor.class.getName());
		//addAfter(ClientFaultConverter.class.getName());
	}

	@Override
	public void handleMessage(Message message) {
		Fault fault = (Fault) message.getContent(Exception.class);
		Element exDetail = (Element) DOMUtils.getChild(fault.getDetail(),
				Node.ELEMENT_NODE);
		if (exDetail == null) {
			return;
		}
		if ("ServiceException".equals(exDetail.getLocalName())
				|| "PolicyException".equals(exDetail.getLocalName())) {
			cn.com.chinatelecom.schema.ctcc.common.v2_1.ServiceException serviceException = new cn.com.chinatelecom.schema.ctcc.common.v2_1.ServiceException();
			Node node = exDetail.getFirstChild();
			if (node == null || node.getFirstChild() == null)
				return;
			serviceException.setMessageId(node.getFirstChild().getNodeValue());
			node = node.getNextSibling();
			if (node == null || node.getFirstChild() == null)
				return;
			serviceException.setText(node.getFirstChild().getNodeValue());
			node = node.getNextSibling();
			if (node == null || node.getFirstChild() == null)
				return;
			serviceException.getVariables().add(
					node.getFirstChild().getNodeValue());
			ServiceException ex = new ServiceException(exDetail.getNodeValue(),
					serviceException);
			message.setContent(Exception.class,
					new Fault(ex, fault.getFaultCode()));
		}

	}



}
