
package xmlbinds;

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
 *         &lt;element name="CoverageFilter" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ProportionFilter" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "coverageFilter",
    "proportionFilter"
})
public class Filters {

    @XmlElement(name = "CoverageFilter", required = true)
    protected String coverageFilter;
    @XmlElement(name = "ProportionFilter", required = true)
    protected String proportionFilter;

    /**
     * Gets the value of the coverageFilter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
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
    public void setCoverageFilter(String value) {
        this.coverageFilter = value;
    }

    /**
     * Gets the value of the proportionFilter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
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
    public void setProportionFilter(String value) {
        this.proportionFilter = value;
    }

}
