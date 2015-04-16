
package cn.com.chinatelecom.schema.ctcc.sms.v2_1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SmsFormat的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="SmsFormat"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Ems"/&gt;
 *     &lt;enumeration value="SmartMessaging"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "SmsFormat")
@XmlEnum
public enum SmsFormat {

    @XmlEnumValue("Ems")
    EMS("Ems"),
    @XmlEnumValue("SmartMessaging")
    SMART_MESSAGING("SmartMessaging");
    private final String value;

    SmsFormat(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SmsFormat fromValue(String v) {
        for (SmsFormat c: SmsFormat.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
