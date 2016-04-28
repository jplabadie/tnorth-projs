
package xmlbinds;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
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
@XmlType(name = "ExternalApplicationsType", propOrder = {
    "index",
    "matrixGenerator",
    "picard",
    "samtools",
    "dupFinder",
    "assemblyImporter",
    "aligner",
    "snpCaller"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class ExternalApplicationsType {

    @XmlElement(name = "Index", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected IndexType index;
    @XmlElement(name = "MatrixGenerator", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected MatrixGeneratorType matrixGenerator;
    @XmlElement(name = "Picard", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected PicardType picard;
    @XmlElement(name = "Samtools", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected SamtoolsType samtools;
    @XmlElement(name = "DupFinder", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected DupFinderType dupFinder;
    @XmlElement(name = "AssemblyImporter", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected AssemblyImporterType assemblyImporter;
    @XmlElement(name = "Aligner")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<AlignerType> aligner;
    @XmlElement(name = "SNPCaller")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<SNPCallerType> snpCaller;

    /**
     * Gets the value of the index property.
     * 
     * @return
     *     possible object is
     *     {@link IndexType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public IndexType getIndex() {
        return index;
    }

    /**
     * Sets the value of the index property.
     * 
     * @param value
     *     allowed object is
     *     {@link IndexType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setIndex(IndexType value) {
        this.index = value;
    }

    /**
     * Gets the value of the matrixGenerator property.
     * 
     * @return
     *     possible object is
     *     {@link MatrixGeneratorType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public MatrixGeneratorType getMatrixGenerator() {
        return matrixGenerator;
    }

    /**
     * Sets the value of the matrixGenerator property.
     * 
     * @param value
     *     allowed object is
     *     {@link MatrixGeneratorType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setMatrixGenerator(MatrixGeneratorType value) {
        this.matrixGenerator = value;
    }

    /**
     * Gets the value of the picard property.
     * 
     * @return
     *     possible object is
     *     {@link PicardType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public PicardType getPicard() {
        return picard;
    }

    /**
     * Sets the value of the picard property.
     * 
     * @param value
     *     allowed object is
     *     {@link PicardType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setPicard(PicardType value) {
        this.picard = value;
    }

    /**
     * Gets the value of the samtools property.
     * 
     * @return
     *     possible object is
     *     {@link SamtoolsType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public SamtoolsType getSamtools() {
        return samtools;
    }

    /**
     * Sets the value of the samtools property.
     * 
     * @param value
     *     allowed object is
     *     {@link SamtoolsType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setSamtools(SamtoolsType value) {
        this.samtools = value;
    }

    /**
     * Gets the value of the dupFinder property.
     * 
     * @return
     *     possible object is
     *     {@link DupFinderType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public DupFinderType getDupFinder() {
        return dupFinder;
    }

    /**
     * Sets the value of the dupFinder property.
     * 
     * @param value
     *     allowed object is
     *     {@link DupFinderType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setDupFinder(DupFinderType value) {
        this.dupFinder = value;
    }

    /**
     * Gets the value of the assemblyImporter property.
     * 
     * @return
     *     possible object is
     *     {@link AssemblyImporterType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public AssemblyImporterType getAssemblyImporter() {
        return assemblyImporter;
    }

    /**
     * Sets the value of the assemblyImporter property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssemblyImporterType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setAssemblyImporter(AssemblyImporterType value) {
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
     * {@link AlignerType }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<AlignerType> getAligner() {
        if (aligner == null) {
            aligner = new ArrayList<AlignerType>();
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
     * {@link SNPCallerType }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-27T09:28:09-07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<SNPCallerType> getSNPCaller() {
        if (snpCaller == null) {
            snpCaller = new ArrayList<SNPCallerType>();
        }
        return this.snpCaller;
    }

}
