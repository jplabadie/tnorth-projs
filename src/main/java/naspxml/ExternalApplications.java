
package naspxml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExternalApplicationsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExternalApplicationsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Index" type="{}IndexType"/>
 *         &lt;element name="BamIndex" type="{}BamIndexType"/>
 *         &lt;element name="MatrixGenerator" type="{}MatrixGeneratorType"/>
 *         &lt;element name="Picard" type="{}PicardType"/>
 *         &lt;element name="Samtools" type="{}SamtoolsType"/>
 *         &lt;element name="DupFinder" type="{}DupFinderType"/>
 *         &lt;element name="AssemblyImporter" type="{}AssemblyImporterType"/>
 *         &lt;element name="Aligner" type="{}AlignerType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SNPCaller" type="{}SNPCallerType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExternalApplications", propOrder = {
    "index",
    "bamIndex",
    "matrixGenerator",
    "picard",
    "samtools",
    "dupFinder",
    "assemblyImporter",
    "aligner",
    "snpCaller"
})
public class ExternalApplications {

    @XmlElement(name = "Index", required = true)
    protected Index index;
    @XmlElement(name = "BamIndex", required = true)
    protected BamIndex bamIndex;
    @XmlElement(name = "MatrixGenerator", required = true)
    protected MatrixGenerator matrixGenerator;
    @XmlElement(name = "Picard", required = true)
    protected Picard picard;
    @XmlElement(name = "Samtools", required = true)
    protected Samtools samtools;
    @XmlElement(name = "DupFinder", required = true)
    protected DupFinder dupFinder;
    @XmlElement(name = "AssemblyImporter", required = true)
    protected AssemblyImporter assemblyImporter;
    @XmlElement(name = "Aligner")
    protected List<Aligner> aligner;
    @XmlElement(name = "SNPCaller")
    protected List<SNPCaller> snpCaller;

    /**
     * Gets the value of the index property.
     * 
     * @return
     *     possible object is
     *     {@link Index }
     *     
     */
    public Index getIndex() {
        return index;
    }

    /**
     * Sets the value of the index property.
     * 
     * @param value
     *     allowed object is
     *     {@link Index }
     *     
     */
    public void setIndex(Index value) {
        this.index = value;
    }

    /**
     * Gets the value of the bamIndex property.
     * 
     * @return
     *     possible object is
     *     {@link BamIndex }
     *     
     */
    public BamIndex getBamIndex() {
        return bamIndex;
    }

    /**
     * Sets the value of the bamIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link BamIndex }
     *     
     */
    public void setBamIndex(BamIndex value) {
        this.bamIndex = value;
    }

    /**
     * Gets the value of the matrixGenerator property.
     * 
     * @return
     *     possible object is
     *     {@link MatrixGenerator }
     *     
     */
    public MatrixGenerator getMatrixGenerator() {
        return matrixGenerator;
    }

    /**
     * Sets the value of the matrixGenerator property.
     * 
     * @param value
     *     allowed object is
     *     {@link MatrixGenerator }
     *     
     */
    public void setMatrixGenerator(MatrixGenerator value) {
        this.matrixGenerator = value;
    }

    /**
     * Gets the value of the picard property.
     * 
     * @return
     *     possible object is
     *     {@link Picard }
     *     
     */
    public Picard getPicard() {
        return picard;
    }

    /**
     * Sets the value of the picard property.
     * 
     * @param value
     *     allowed object is
     *     {@link Picard }
     *     
     */
    public void setPicard(Picard value) {
        this.picard = value;
    }

    /**
     * Gets the value of the samtools property.
     * 
     * @return
     *     possible object is
     *     {@link Samtools }
     *     
     */
    public Samtools getSamtools() {
        return samtools;
    }

    /**
     * Sets the value of the samtools property.
     * 
     * @param value
     *     allowed object is
     *     {@link Samtools }
     *     
     */
    public void setSamtools(Samtools value) {
        this.samtools = value;
    }

    /**
     * Gets the value of the dupFinder property.
     * 
     * @return
     *     possible object is
     *     {@link DupFinder }
     *     
     */
    public DupFinder getDupFinder() {
        return dupFinder;
    }

    /**
     * Sets the value of the dupFinder property.
     * 
     * @param value
     *     allowed object is
     *     {@link DupFinder }
     *     
     */
    public void setDupFinder(DupFinder value) {
        this.dupFinder = value;
    }

    /**
     * Gets the value of the assemblyImporter property.
     * 
     * @return
     *     possible object is
     *     {@link AssemblyImporter }
     *     
     */
    public AssemblyImporter getAssemblyImporter() {
        return assemblyImporter;
    }

    /**
     * Sets the value of the assemblyImporter property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssemblyImporter }
     *     
     */
    public void setAssemblyImporter(AssemblyImporter value) {
        this.assemblyImporter = value;
    }

    /**
     * Gets the value of the aligner property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aligner property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAligner().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Aligner }
     * 
     * 
     */
    public List<Aligner> getAligner() {
        if (aligner == null) {
            aligner = new ArrayList<Aligner>();
        }
        return this.aligner;
    }

    /**
     * Gets the value of the snpCaller property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the snpCaller property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSNPCaller().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SNPCaller }
     * 
     * 
     */
    public List<SNPCaller> getSNPCaller() {
        if (snpCaller == null) {
            snpCaller = new ArrayList<SNPCaller>();
        }
        return this.snpCaller;
    }

}
