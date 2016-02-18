
package xmlsources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReadPair complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReadPair">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Read1Filename" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Read2Filename" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="sample" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReadPair", namespace = "http://www.tgen.org/NaspInputSchema", propOrder = {
    "read1Filename",
    "read2Filename"
})
public class ReadPair {

    @XmlElement(name = "Read1Filename", namespace = "http://www.tgen.org/NaspInputSchema", required = true)
    protected String read1Filename;
    @XmlElement(name = "Read2Filename", namespace = "http://www.tgen.org/NaspInputSchema", required = true)
    protected String read2Filename;
    @XmlAttribute(name = "sample")
    protected String sample;

    /**
     * Gets the value of the read1Filename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRead1Filename() {
        return read1Filename;
    }

    /**
     * Sets the value of the read1Filename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRead1Filename(String value) {
        this.read1Filename = value;
    }

    /**
     * Gets the value of the read2Filename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRead2Filename() {
        return read2Filename;
    }

    /**
     * Sets the value of the read2Filename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRead2Filename(String value) {
        this.read2Filename = value;
    }

    /**
     * Gets the value of the sample property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSample() {
        return sample;
    }

    /**
     * Sets the value of the sample property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSample(String value) {
        this.sample = value;
    }

}
