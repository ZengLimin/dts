
package cn.com.chinatelecom.schema.ctcc.common.v2_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>TimeMetric complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="TimeMetric"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="metric" type="{http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1}TimeMetrics"/&gt;
 *         &lt;element name="units" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeMetric", propOrder = {
    "metric",
    "units"
})
public class TimeMetric {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected TimeMetrics metric;
    protected int units;

    /**
     * 获取metric属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TimeMetrics }
     *     
     */
    public TimeMetrics getMetric() {
        return metric;
    }

    /**
     * 设置metric属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TimeMetrics }
     *     
     */
    public void setMetric(TimeMetrics value) {
        this.metric = value;
    }

    /**
     * 获取units属性的值。
     * 
     */
    public int getUnits() {
        return units;
    }

    /**
     * 设置units属性的值。
     * 
     */
    public void setUnits(int value) {
        this.units = value;
    }

}
