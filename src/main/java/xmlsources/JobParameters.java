
package xmlsources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for JobParameters complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="JobParameters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="MemRequested" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NumCPUs" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Walltime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JobParameters", namespace = "http://www.tgen.org/NaspInputSchema", propOrder = {

})
public class JobParameters {

    @XmlElement(name = "MemRequested", namespace = "http://www.tgen.org/NaspInputSchema", required = true)
    protected String memRequested;
    @XmlElement(name = "NumCPUs", namespace = "http://www.tgen.org/NaspInputSchema", required = true)
    protected String numCPUs;
    @XmlElement(name = "Walltime", namespace = "http://www.tgen.org/NaspInputSchema", required = true)
    protected String walltime;
    @XmlAttribute(name = "name")
    protected String name;

    /**
     * Gets the value of the memRequested property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemRequested() {
        return memRequested;
    }

    /**
     * Sets the value of the memRequested property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemRequested(String value) {
        this.memRequested = value;
    }

    /**
     * Gets the value of the numCPUs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumCPUs() {
        return numCPUs;
    }

    /**
     * Sets the value of the numCPUs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumCPUs(String value) {
        this.numCPUs = value;
    }

    /**
     * Gets the value of the walltime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWalltime() {
        return walltime;
    }

    /**
     * Sets the value of the walltime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWalltime(String value) {
        this.walltime = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
