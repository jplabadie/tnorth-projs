
package xmlbinds;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FiltersType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FiltersType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProportionFilter" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CoverageFilter" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Filters", propOrder = {
    "proportionFilter",
    "coverageFilter"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Filters {

    @XmlElement(name = "ProportionFilter", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String proportionFilter;
    @XmlElement(name = "CoverageFilter", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String coverageFilter;

    /**
     * Gets the value of the proportionFilter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getProportionFilter() {
        return proportionFilter;
    }

    /**
     * Sets the value of the proportionFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setProportionFilter(String value) {
        this.proportionFilter = value;
    }

    /**
     * Gets the value of the coverageFilter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getCoverageFilter() {
        return coverageFilter;
    }

    /**
     * Sets the value of the coverageFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setCoverageFilter(String value) {
        this.coverageFilter = value;
    }

}
