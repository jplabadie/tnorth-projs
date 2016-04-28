
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
@XmlType(name = "Files", propOrder = {
    "readFolder",
    "assemblyFolder"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Files {

    @XmlElement(name = "ReadFolder", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected ReadFolder readFolder;
    @XmlElement(name = "AssemblyFolder", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected AssemblyFolder assemblyFolder;

    /**
     * Gets the value of the readFolder property.
     * 
     * @return
     *     possible object is
     *     {@link ReadFolder }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public ReadFolder getReadFolder() {
        return readFolder;
    }

    /**
     * Sets the value of the readFolder property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReadFolder }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setReadFolder(ReadFolder value) {
        this.readFolder = value;
    }

    /**
     * Gets the value of the assemblyFolder property.
     * 
     * @return
     *     possible object is
     *     {@link AssemblyFolder }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public AssemblyFolder getAssemblyFolder() {
        return assemblyFolder;
    }

    /**
     * Sets the value of the assemblyFolder property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssemblyFolder }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setAssemblyFolder(AssemblyFolder value) {
        this.assemblyFolder = value;
    }

}
