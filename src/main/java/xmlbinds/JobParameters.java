
package xmlbinds;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for JobParametersType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="JobParametersType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MemRequested">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="2"/>
 *               &lt;enumeration value="45"/>
 *               &lt;enumeration value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="NumCPUs">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="1"/>
 *               &lt;enumeration value="12"/>
 *               &lt;enumeration value="4"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Walltime">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="4"/>
 *               &lt;enumeration value="48"/>
 *               &lt;enumeration value="1"/>
 *               &lt;enumeration value="36"/>
 *               &lt;enumeration value="24"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Queue" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value=""/>
 *               &lt;enumeration value="hmem"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="JobSubmitterArgs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JobParameters", propOrder = {
    "memRequested",
    "numCPUs",
    "walltime",
    "queue",
    "jobSubmitterArgs"
})
public class JobParameters {

    @XmlElement(name = "MemRequested", required = true)
    protected String memRequested;
    @XmlElement(name = "NumCPUs", required = true)
    protected String numCPUs;
    @XmlElement(name = "Walltime", required = true)
    protected String walltime;
    @XmlElement(name = "Queue")
    protected String queue;
    @XmlElement(name = "JobSubmitterArgs")
    protected String jobSubmitterArgs;
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
     * Gets the value of the queue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueue() {
        return queue;
    }

    /**
     * Sets the value of the queue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueue(String value) {
        this.queue = value;
    }

    /**
     * Gets the value of the jobSubmitterArgs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJobSubmitterArgs() {
        return jobSubmitterArgs;
    }

    /**
     * Sets the value of the jobSubmitterArgs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJobSubmitterArgs(String value) {
        this.jobSubmitterArgs = value;
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
