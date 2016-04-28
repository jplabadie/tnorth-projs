
package xmlbinds;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FilesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FilesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReadFolder" type="{}ReadFolderType"/>
 *         &lt;element name="AssemblyFolder" type="{}AssemblyFolderType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FilesType", propOrder = {
    "readFolder",
    "assemblyFolder"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class FilesType {

    @XmlElement(name = "ReadFolder", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected ReadFolderType readFolder;
    @XmlElement(name = "AssemblyFolder", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected AssemblyFolderType assemblyFolder;

    /**
     * Gets the value of the readFolder property.
     * 
     * @return
     *     possible object is
     *     {@link ReadFolderType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public ReadFolderType getReadFolder() {
        return readFolder;
    }

    /**
     * Sets the value of the readFolder property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReadFolderType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setReadFolder(ReadFolderType value) {
        this.readFolder = value;
    }

    /**
     * Gets the value of the assemblyFolder property.
     * 
     * @return
     *     possible object is
     *     {@link AssemblyFolderType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public AssemblyFolderType getAssemblyFolder() {
        return assemblyFolder;
    }

    /**
     * Sets the value of the assemblyFolder property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssemblyFolderType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setAssemblyFolder(AssemblyFolderType value) {
        this.assemblyFolder = value;
    }

}
