/**
 * 
 */
package com.zenglm.cxf;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.zenglm.dts.utils.EncryptUtil;

/**
 * 
 * @author 曾利民
 * @version 1.0.0
 * @since 2015年3月9日 上午9:32:21
 */
public class SendSmsInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	private static final String NAMESPACEURI = "http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1";

	private static final String SP_ID_NAME = "spId";

	private static final String SP_ID_VALUE = "23168182";

	private static final String SP_PWD_NAME = "spPassword";
	private static final String SP_PWD_VALUE = "tianque123";

	private static final String TIMESTAMP_NAME = "timeStamp";

	private static final String PRODUCT_ID_NAME = "productId";
	private static final String PRODUCT_ID_VALUE = "123831001200880030799";

	/**
	 * @param phase
	 */
	public SendSmsInterceptor() {
		super(Phase.WRITE);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {

		QName qname = new QName("RequestSOAPHeader");

		Document doc = DOMUtils.createDocument();
		//自定义节点
		Element spId = doc.createElement(SP_ID_NAME);
		spId.setTextContent(SP_ID_VALUE);

		SimpleDateFormat sf = new SimpleDateFormat("MMddHHmmss");
		String timeStampStr = sf.format(new Date());
		Element timeStamp = doc.createElement(TIMESTAMP_NAME);
		timeStamp.setTextContent(timeStampStr);

		Element spPass = doc.createElement(SP_PWD_NAME);
		spPass.setTextContent(EncryptUtil.md5Encrypt(
				SP_ID_VALUE + SP_PWD_VALUE + timeStampStr).toUpperCase());

		Element product = doc.createElement(PRODUCT_ID_NAME);
		product.setTextContent(PRODUCT_ID_VALUE);

		Element oa = doc.createElement("OA");
		oa.setTextContent("tel:+8613429676180");

		Element multi = doc.createElement("multicastMessaging");
		multi.setTextContent("true");

		Element amount = doc.createElement("amount");
		amount.setTextContent("true");

		Element root = doc.createElementNS(NAMESPACEURI, "RequestSOAPHeader");

		root.appendChild(spId);
		root.appendChild(spPass);
		root.appendChild(timeStamp);
		root.appendChild(product);
		//root.appendChild(multi);
		//root.appendChild(amount);
		root.appendChild(oa);

		SoapHeader head = new SoapHeader(qname, root);

		List<Header> headers = message.getHeaders();

		headers.add(head);
	}

}
