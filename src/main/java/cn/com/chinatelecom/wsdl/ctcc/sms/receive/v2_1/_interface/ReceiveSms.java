package cn.com.chinatelecom.wsdl.ctcc.sms.receive.v2_1._interface;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.0.4
 * 2015-03-09T20:00:01.699+08:00
 * Generated source version: 3.0.4
 * 
 */
@WebService(targetNamespace = "http://www.chinatelecom.com.cn/wsdl/ctcc/sms/receive/v2_1/interface", name = "ReceiveSms")
@XmlSeeAlso({cn.com.chinatelecom.schema.ctcc.sms.v2_1.ObjectFactory.class, cn.com.chinatelecom.schema.ctcc.common.v2_1.ObjectFactory.class, cn.com.chinatelecom.schema.ctcc.sms.receive.v2_1.local.ObjectFactory.class})
public interface ReceiveSms {

    @WebResult(name = "result", targetNamespace = "http://www.chinatelecom.com.cn/schema/ctcc/sms/receive/v2_1/local")
    @RequestWrapper(localName = "getReceivedSms", targetNamespace = "http://www.chinatelecom.com.cn/schema/ctcc/sms/receive/v2_1/local", className = "cn.com.chinatelecom.schema.ctcc.sms.receive.v2_1.local.GetReceivedSms")
    @WebMethod
    @ResponseWrapper(localName = "getReceivedSmsResponse", targetNamespace = "http://www.chinatelecom.com.cn/schema/ctcc/sms/receive/v2_1/local", className = "cn.com.chinatelecom.schema.ctcc.sms.receive.v2_1.local.GetReceivedSmsResponse")
    public java.util.List<cn.com.chinatelecom.schema.ctcc.sms.v2_1.SmsMessage> getReceivedSms(
        @WebParam(name = "registrationIdentifier", targetNamespace = "http://www.chinatelecom.com.cn/schema/ctcc/sms/receive/v2_1/local")
        java.lang.String registrationIdentifier
    ) throws cn.com.chinatelecom.wsdl.ctcc.common.v2_0.faults.PolicyException, cn.com.chinatelecom.wsdl.ctcc.common.v2_0.faults.ServiceException;
}