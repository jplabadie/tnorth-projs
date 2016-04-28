
package xmlbinds;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the xmlbinds package.
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _NaspInputData_QNAME = new QName("", "NaspInputData");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: xmlbinds
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NaspInputDataType }
     * 
     */
    public NaspInputDataType createNaspInputDataType() {
        return new NaspInputDataType();
    }

    /**
     * Create an instance of {@link AlignerType }
     * 
     */
    public AlignerType createAlignerType() {
        return new AlignerType();
    }

    /**
     * Create an instance of {@link PicardType }
     * 
     */
    public PicardType createPicardType() {
        return new PicardType();
    }

    /**
     * Create an instance of {@link ReadFolderType }
     * 
     */
    public ReadFolderType createReadFolderType() {
        return new ReadFolderType();
    }

    /**
     * Create an instance of {@link MatrixGeneratorType }
     * 
     */
    public MatrixGeneratorType createMatrixGeneratorType() {
        return new MatrixGeneratorType();
    }

    /**
     * Create an instance of {@link AssemblyFolderType }
     * 
     */
    public AssemblyFolderType createAssemblyFolderType() {
        return new AssemblyFolderType();
    }

    /**
     * Create an instance of {@link ReadPairType }
     * 
     */
    public ReadPairType createReadPairType() {
        return new ReadPairType();
    }

    /**
     * Create an instance of {@link AssemblyImporterType }
     * 
     */
    public AssemblyImporterType createAssemblyImporterType() {
        return new AssemblyImporterType();
    }

    /**
     * Create an instance of {@link ReferenceType }
     * 
     */
    public ReferenceType createReferenceType() {
        return new ReferenceType();
    }

    /**
     * Create an instance of {@link OptionsType }
     * 
     */
    public OptionsType createOptionsType() {
        return new OptionsType();
    }

    /**
     * Create an instance of {@link SamtoolsType }
     * 
     */
    public SamtoolsType createSamtoolsType() {
        return new SamtoolsType();
    }

    /**
     * Create an instance of {@link AssemblyType }
     * 
     */
    public AssemblyType createAssemblyType() {
        return new AssemblyType();
    }

    /**
     * Create an instance of {@link DupFinderType }
     * 
     */
    public DupFinderType createDupFinderType() {
        return new DupFinderType();
    }

    /**
     * Create an instance of {@link JobParametersType }
     * 
     */
    public JobParametersType createJobParametersType() {
        return new JobParametersType();
    }

    /**
     * Create an instance of {@link ExternalApplicationsType }
     * 
     */
    public ExternalApplicationsType createExternalApplicationsType() {
        return new ExternalApplicationsType();
    }

    /**
     * Create an instance of {@link FilesType }
     * 
     */
    public FilesType createFilesType() {
        return new FilesType();
    }

    /**
     * Create an instance of {@link SNPCallerType }
     * 
     */
    public SNPCallerType createSNPCallerType() {
        return new SNPCallerType();
    }

    /**
     * Create an instance of {@link FiltersType }
     * 
     */
    public FiltersType createFiltersType() {
        return new FiltersType();
    }

    /**
     * Create an instance of {@link IndexType }
     * 
     */
    public IndexType createIndexType() {
        return new IndexType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NaspInputDataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "NaspInputData")
    public JAXBElement<NaspInputDataType> createNaspInputData(NaspInputDataType value) {
        return new JAXBElement<NaspInputDataType>(_NaspInputData_QNAME, NaspInputDataType.class, null, value);
    }

}
