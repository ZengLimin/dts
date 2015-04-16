package com.zenglm.cxf;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class SendMsmHandler implements SOAPHandler<SOAPMessageContext> {

	private static final String namespaceURI = "http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1";

	@Override
	public void close(MessageContext arg0) {

	}

	@Override
	public boolean handleFault(SOAPMessageContext arg0) {
		return false;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		Boolean isRequest = (Boolean) context
				.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY);

		if (isRequest) {
			System.out.println("request...");
			SOAPMessage soapMsg = context.getMessage();
			SOAPEnvelope soapEnv;
			try {
				soapEnv = soapMsg.getSOAPPart().getEnvelope();
				SOAPHeader soapHeader = soapEnv.getHeader();
				if (soapHeader == null) {
					soapHeader = soapEnv.addHeader();
				}

				String mac = "test";

				//SP ID
				QName spId = new QName(namespaceURI, "spID");
				SOAPHeaderElement spIdSoapHeaderElement = soapHeader
						.addHeaderElement(spId);
				spIdSoapHeaderElement.addTextNode(mac);

				//SP密码（MD5加密） MD5加密算法如下： SP密码＝SPID＋密钥＋时间戳 密钥部分由ISMP分配，
				//时间戳由发端设备生成，格式是：MMDDHHMMSS，月日时分秒。
				QName spPwd = new QName(namespaceURI, "spPassword");
				SOAPHeaderElement spPwdSoapHeaderElement = soapHeader
						.addHeaderElement(spPwd);
				spPwdSoapHeaderElement.addTextNode(mac);

				//时间戳 格式为：MMDDHHMMSS（月日时分秒）
				QName timeStamp = new QName(namespaceURI, "timeStamp");
				SOAPHeaderElement timeStampPwdSoapHeaderElement = soapHeader
						.addHeaderElement(timeStamp);
				timeStampPwdSoapHeaderElement.addTextNode("0228121212");

				//产品ID
				QName productId = new QName(namespaceURI, "productId");
				SOAPHeaderElement productIdPwdSoapHeaderElement = soapHeader
						.addHeaderElement(productId);
				productIdPwdSoapHeaderElement.addTextNode("ABC123");

				soapMsg.saveChanges();

				soapMsg.writeTo(System.out);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("response...");
		}
		return true;
	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}

	private String getMACAddress() throws Exception {
		InetAddress ip;
		StringBuilder sb = new StringBuilder();
		ip = InetAddress.getLocalHost();
		System.out.println("Current IP address:" + ip.getHostAddress());
		NetworkInterface network = NetworkInterface.getByInetAddress(ip);
		byte[] mac = network.getHardwareAddress();
		System.out.println("Current MAC address:");
		for (int i = 0; i < mac.length; i++) {
			sb.append(String.format("%02X%s", mac[i],
					(i < mac.length - 1) ? "-" : ""));
		}

		System.out.println(sb.toString());
		return sb.toString();
	}
}
