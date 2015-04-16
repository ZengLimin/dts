
package cn.com.chinatelecom.schema.ctcc.common.v2_1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DeliveryConfirmStatus的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="DeliveryConfirmStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Success"/&gt;
 *     &lt;enumeration value="Fail"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "DeliveryConfirmStatus")
@XmlEnum
public enum DeliveryConfirmStatus {

    @XmlEnumValue("Success")
    SUCCESS("Success"),
    @XmlEnumValue("Fail")
    FAIL("Fail");
    private final String value;

    DeliveryConfirmStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DeliveryConfirmStatus fromValue(String v) {
        for (DeliveryConfirmStatus c: DeliveryConfirmStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
