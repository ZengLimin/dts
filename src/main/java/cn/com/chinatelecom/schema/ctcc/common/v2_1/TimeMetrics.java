
package cn.com.chinatelecom.schema.ctcc.common.v2_1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>TimeMetrics的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="TimeMetrics"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Millisecond"/&gt;
 *     &lt;enumeration value="Second"/&gt;
 *     &lt;enumeration value="Minute"/&gt;
 *     &lt;enumeration value="Hour"/&gt;
 *     &lt;enumeration value="Day"/&gt;
 *     &lt;enumeration value="Week"/&gt;
 *     &lt;enumeration value="Month"/&gt;
 *     &lt;enumeration value="Year"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TimeMetrics")
@XmlEnum
public enum TimeMetrics {

    @XmlEnumValue("Millisecond")
    MILLISECOND("Millisecond"),
    @XmlEnumValue("Second")
    SECOND("Second"),
    @XmlEnumValue("Minute")
    MINUTE("Minute"),
    @XmlEnumValue("Hour")
    HOUR("Hour"),
    @XmlEnumValue("Day")
    DAY("Day"),
    @XmlEnumValue("Week")
    WEEK("Week"),
    @XmlEnumValue("Month")
    MONTH("Month"),
    @XmlEnumValue("Year")
    YEAR("Year");
    private final String value;

    TimeMetrics(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TimeMetrics fromValue(String v) {
        for (TimeMetrics c: TimeMetrics.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
