
package xmlsources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReferenceGenome complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReferenceGenome">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.tgen.org/NaspInputSchema}ExternalGenome">
 *       &lt;attribute name="FindDuplicates" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferenceGenome", namespace = "http://www.tgen.org/NaspInputSchema")
public class ReferenceGenome
    extends ExternalGenome
{

    @XmlAttribute(name = "FindDuplicates")
    protected Boolean findDuplicates;

    /**
     * Gets the value of the findDuplicates property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFindDuplicates() {
        return findDuplicates;
    }

    /**
     * Sets the value of the findDuplicates property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFindDuplicates(Boolean value) {
        this.findDuplicates = value;
    }

}
