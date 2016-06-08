
package xmlbinds;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AssemblyFolderType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssemblyFolderType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Assembly" type="{}AssemblyType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="path" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssemblyFolder", propOrder = {
    "assembly"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class AssemblyFolder {

    @XmlElement(name = "Assembly", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Assembly assembly;
    @XmlAttribute(name = "path")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String path;

    /**
     * Gets the value of the assembly property.
     * 
     * @return
     *     possible object is
     *     {@link Assembly }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Assembly getAssembly() {
        return assembly;
    }

    /**
     * Sets the value of the assembly property.
     * 
     * @param value
     *     allowed object is
     *     {@link Assembly }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setAssembly(Assembly value) {
        this.assembly = value;
    }

    /**
     * Gets the value of the path property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setPath(String value) {
        this.path = value;
    }

}
