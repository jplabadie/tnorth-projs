
package xmlbinds;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for AlignerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AlignerType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AdditionalArgs">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value=""/>
 *               &lt;enumeration value="-r all"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="JobParameters" type="{}JobParametersType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Aligner", propOrder = {
    "additionalArgs",
    "jobParameters"
})
@XmlRootElement(name = "Aligner")
public class Aligner {

    @XmlElement(name = "AdditionalArgs", required = true)
    protected String additionalArgs;
    @XmlElement(name = "JobParameters", required = true)
    protected JobParameters jobParameters;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "path")
    protected String path;

    /**
     * Gets the value of the additionalArgs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalArgs() {
        return additionalArgs;
    }

    /**
     * Sets the value of the additionalArgs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalArgs(String value) {
        this.additionalArgs = value;
    }

    /**
     * Gets the value of the jobParameters property.
     * 
     * @return
     *     possible object is
     *     {@link JobParameters }
     *     
     */
    public JobParameters getJobParameters() {
        return jobParameters;
    }

    /**
     * Sets the value of the jobParameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link JobParameters }
     *     
     */
    public void setJobParameters(JobParameters value) {
        this.jobParameters = value;
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

    /**
     * Gets the value of the path property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the value of the path property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPath(String value) {
        this.path = value;
    }

}
