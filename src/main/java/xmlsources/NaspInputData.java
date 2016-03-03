
package xmlsources;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for NaspInputDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NaspInputDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Options" type="{}OptionsType"/>
 *         &lt;element name="Files" type="{}FilesType"/>
 *         &lt;element name="ExternalApplications" type="{}ExternalApplicationsType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NaspInputData", propOrder = {
    "options",
    "files",
    "externalApplications"
})
@XmlRootElement(name = "NaspInputData")
public class NaspInputData {

    @XmlElement(name = "Options", required = true)
    protected Options options;
    @XmlElement(name = "Files", required = true)
    protected Files files;
    @XmlElement(name = "ExternalApplications", required = true)
    protected ExternalApplications externalApplications;

    /**
     * Gets the value of the options property.
     * 
     * @return
     *     possible object is
     *     {@link Options }
     *     
     */
    public Options getOptions() {
        return options;
    }

    /**
     * Sets the value of the options property.
     * 
     * @param value
     *     allowed object is
     *     {@link Options }
     *     
     */
    public void setOptions(Options value) {
        this.options = value;
    }

    /**
     * Gets the value of the files property.
     * 
     * @return
     *     possible object is
     *     {@link Files }
     *     
     */
    public Files getFiles() {
        return files;
    }

    /**
     * Sets the value of the files property.
     * 
     * @param value
     *     allowed object is
     *     {@link Files }
     *     
     */
    public void setFiles(Files value) {
        this.files = value;
    }

    /**
     * Gets the value of the externalApplications property.
     * 
     * @return
     *     possible object is
     *     {@link ExternalApplications }
     *     
     */
    public ExternalApplications getExternalApplications() {
        return externalApplications;
    }

    /**
     * Sets the value of the externalApplications property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExternalApplications }
     *     
     */
    public void setExternalApplications(ExternalApplications value) {
        this.externalApplications = value;
    }

}
