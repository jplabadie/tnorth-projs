
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

    private final static QName _SetupParameters_QNAME = new QName("http://www.tgen.org/NaspInputSchema", "SetupParameters");
    private final static QName _ReferenceGenome_QNAME = new QName("http://www.tgen.org/NaspInputSchema", "ReferenceGenome");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: xmlsources
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReferenceGenome }
     * 
     */
    public ReferenceGenome createReferenceGenome() {
        return new ReferenceGenome();
    }

    /**
     * Create an instance of {@link ReadPair }
     * 
     */
    public ReadPair createReadPair() {
        return new ReadPair();
    }

    /**
     * Create an instance of {@link ExternalGenome }
     * 
     */
    public ExternalGenome createExternalGenome() {
        return new ExternalGenome();
    }

    /**
     * Create an instance of {@link UtilityProgram }
     * 
     */
    public UtilityProgram createUtilityProgram() {
        return new UtilityProgram();
    }

    /**
     * Create an instance of {@link JobParameters }
     * 
     */
    public JobParameters createJobParameters() {
        return new JobParameters();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tgen.org/NaspInputSchema", name = "SetupParameters")
    public JAXBElement<String> createSetupParameters(String value) {
        return new JAXBElement<String>(_SetupParameters_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReferenceGenome }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tgen.org/NaspInputSchema", name = "ReferenceGenome")
    public JAXBElement<ReferenceGenome> createReferenceGenome(ReferenceGenome value) {
        return new JAXBElement<ReferenceGenome>(_ReferenceGenome_QNAME, ReferenceGenome.class, null, value);
    }

}
