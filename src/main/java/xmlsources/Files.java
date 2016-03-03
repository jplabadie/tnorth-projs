
package xmlsources;

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
 *         &lt;element name="AlignmentFolder" type="{}AlignmentFolderType"/>
 *         &lt;element name="VCFFolder" type="{}VCFFolderType"/>
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
    "assemblyFolder",
    "alignmentFolder",
    "vcfFolder"
})
public class Files {

    @XmlElement(name = "ReadFolder", required = true)
    protected ReadFolder readFolder;
    @XmlElement(name = "AssemblyFolder", required = true)
    protected AssemblyFolder assemblyFolder;
    @XmlElement(name = "AlignmentFolder", required = true)
    protected AlignmentFolder alignmentFolder;
    @XmlElement(name = "VCFFolder", required = true)
    protected VCFFolder vcfFolder;

    /**
     * Gets the value of the readFolder property.
     * 
     * @return
     *     possible object is
     *     {@link ReadFolder }
     *     
     */
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
    public void setAssemblyFolder(AssemblyFolder value) {
        this.assemblyFolder = value;
    }

    /**
     * Gets the value of the alignmentFolder property.
     * 
     * @return
     *     possible object is
     *     {@link AlignmentFolder }
     *     
     */
    public AlignmentFolder getAlignmentFolder() {
        return alignmentFolder;
    }

    /**
     * Sets the value of the alignmentFolder property.
     * 
     * @param value
     *     allowed object is
     *     {@link AlignmentFolder }
     *     
     */
    public void setAlignmentFolder(AlignmentFolder value) {
        this.alignmentFolder = value;
    }

    /**
     * Gets the value of the vcfFolder property.
     * 
     * @return
     *     possible object is
     *     {@link VCFFolder }
     *     
     */
    public VCFFolder getVCFFolder() {
        return vcfFolder;
    }

    /**
     * Sets the value of the vcfFolder property.
     * 
     * @param value
     *     allowed object is
     *     {@link VCFFolder }
     *     
     */
    public void setVCFFolder(VCFFolder value) {
        this.vcfFolder = value;
    }

}
