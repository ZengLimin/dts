
package cn.com.chinatelecom.schema.ctcc.sms.receive.v2_1.local;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.com.chinatelecom.schema.ctcc.sms.receive.v2_1.local package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetReceivedSms_QNAME = new QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/receive/v2_1/local", "getReceivedSms");
    private final static QName _GetReceivedSmsResponse_QNAME = new QName("http://www.chinatelecom.com.cn/schema/ctcc/sms/receive/v2_1/local", "getReceivedSmsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.com.chinatelecom.schema.ctcc.sms.receive.v2_1.local
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetReceivedSms }
     * 
     */
    public GetReceivedSms createGetReceivedSms() {
        return new GetReceivedSms();
    }

    /**
     * Create an instance of {@link GetReceivedSmsResponse }
     * 
     */
    public GetReceivedSmsResponse createGetReceivedSmsResponse() {
        return new GetReceivedSmsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetReceivedSms }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.chinatelecom.com.cn/schema/ctcc/sms/receive/v2_1/local", name = "getReceivedSms")
    public JAXBElement<GetReceivedSms> createGetReceivedSms(GetReceivedSms value) {
        return new JAXBElement<GetReceivedSms>(_GetReceivedSms_QNAME, GetReceivedSms.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetReceivedSmsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.chinatelecom.com.cn/schema/ctcc/sms/receive/v2_1/local", name = "getReceivedSmsResponse")
    public JAXBElement<GetReceivedSmsResponse> createGetReceivedSmsResponse(GetReceivedSmsResponse value) {
        return new JAXBElement<GetReceivedSmsResponse>(_GetReceivedSmsResponse_QNAME, GetReceivedSmsResponse.class, null, value);
    }

}
