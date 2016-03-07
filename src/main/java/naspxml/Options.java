
package naspxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OptionsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OptionsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OutputFolder" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RunName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Reference" type="{}ReferenceType"/>
 *         &lt;element name="Filters" type="{}FiltersType"/>
 *         &lt;element name="JobSubmitter" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Options", propOrder = {
    "outputFolder",
    "runName",
    "reference",
    "filters",
    "jobSubmitter"
})
public class Options {

    @XmlElement(name = "OutputFolder", required = true)
    protected String outputFolder;
    @XmlElement(name = "RunName", required = true)
    protected String runName;
    @XmlElement(name = "Reference", required = true)
    protected Reference reference;
    @XmlElement(name = "Filters", required = true)
    protected Filters filters;
    @XmlElement(name = "JobSubmitter", required = true)
    protected String jobSubmitter;

    /**
     * Gets the value of the outputFolder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputFolder() {
        return outputFolder;
    }

    /**
     * Sets the value of the outputFolder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputFolder(String value) {
        this.outputFolder = value;
    }

    /**
     * Gets the value of the runName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRunName() {
        return runName;
    }

    /**
     * Sets the value of the runName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRunName(String value) {
        this.runName = value;
    }

    /**
     * Gets the value of the reference property.
     * 
     * @return
     *     possible object is
     *     {@link Reference }
     *     
     */
    public Reference getReference() {
        return reference;
    }

    /**
     * Sets the value of the reference property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reference }
     *     
     */
    public void setReference(Reference value) {
        this.reference = value;
    }

    /**
     * Gets the value of the filters property.
     * 
     * @return
     *     possible object is
     *     {@link Filters }
     *     
     */
    public Filters getFilters() {
        return filters;
    }

    /**
     * Sets the value of the filters property.
     * 
     * @param value
     *     allowed object is
     *     {@link Filters }
     *     
     */
    public void setFilters(Filters value) {
        this.filters = value;
    }

    /**
     * Gets the value of the jobSubmitter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJobSubmitter() {
        return jobSubmitter;
    }

    /**
     * Sets the value of the jobSubmitter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJobSubmitter(String value) {
        this.jobSubmitter = value;
    }

}
