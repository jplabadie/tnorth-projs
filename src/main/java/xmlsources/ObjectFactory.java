
package xmlsources;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the xmlsources package.
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: xmlsources
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NaspInputData }
     * 
     */
    public NaspInputData createNaspInputDataType() {
        return new NaspInputData();
    }

    /**
     * Create an instance of {@link Aligner }
     * 
     */
    public Aligner createAlignerType() {
        return new Aligner();
    }

    /**
     * Create an instance of {@link VCFFolder }
     * 
     */
    public VCFFolder createVCFFolderType() {
        return new VCFFolder();
    }

    /**
     * Create an instance of {@link Picard }
     * 
     */
    public Picard createPicardType() {
        return new Picard();
    }

    /**
     * Create an instance of {@link ReadFolder }
     * 
     */
    public ReadFolder createReadFolderType() {
        return new ReadFolder();
    }

    /**
     * Create an instance of {@link MatrixGenerator }
     * 
     */
    public MatrixGenerator createMatrixGeneratorType() {
        return new MatrixGenerator();
    }

    /**
     * Create an instance of {@link AssemblyFolder }
     * 
     */
    public AssemblyFolder createAssemblyFolderType() {
        return new AssemblyFolder();
    }

    /**
     * Create an instance of {@link AssemblyImporter }
     * 
     */
    public AssemblyImporter createAssemblyImporterType() {
        return new AssemblyImporter();
    }

    /**
     * Create an instance of {@link Reference }
     * 
     */
    public Reference createReferenceType() {
        return new Reference();
    }

    /**
     * Create an instance of {@link Options }
     * 
     */
    public Options createOptionsType() {
        return new Options();
    }

    /**
     * Create an instance of {@link Samtools }
     * 
     */
    public Samtools createSamtoolsType() {
        return new Samtools();
    }

    /**
     * Create an instance of {@link Assembly }
     * 
     */
    public Assembly createAssemblyType() {
        return new Assembly();
    }

    /**
     * Create an instance of {@link AlignmentFolder }
     * 
     */
    public AlignmentFolder createAlignmentFolderType() {
        return new AlignmentFolder();
    }

    /**
     * Create an instance of {@link BamIndex }
     * 
     */
    public BamIndex createBamIndexType() {
        return new BamIndex();
    }

    /**
     * Create an instance of {@link DupFinder }
     * 
     */
    public DupFinder createDupFinderType() {
        return new DupFinder();
    }

    /**
     * Create an instance of {@link JobParameters }
     * 
     */
    public JobParameters createJobParametersType() {
        return new JobParameters();
    }

    /**
     * Create an instance of {@link ExternalApplications }
     * 
     */
    public ExternalApplications createExternalApplicationsType() {
        return new ExternalApplications();
    }

    /**
     * Create an instance of {@link Files }
     * 
     */
    public Files createFilesType() {
        return new Files();
    }

    /**
     * Create an instance of {@link SNPCaller }
     * 
     */
    public SNPCaller createSNPCallerType() {
        return new SNPCaller();
    }

    /**
     * Create an instance of {@link Filters }
     * 
     */
    public Filters createFiltersType() {
        return new Filters();
    }

    /**
     * Create an instance of {@link Index }
     * 
     */
    public Index createIndexType() {
        return new Index();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NaspInputData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "NaspInputData")
    public JAXBElement<NaspInputData> createNaspInputData(NaspInputData value) {
        return new JAXBElement<NaspInputData>(_NaspInputData_QNAME, NaspInputData.class, null, value);
    }

}
