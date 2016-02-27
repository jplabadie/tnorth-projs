package prototypes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import xmlsources.JobParameters;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Creates a new XML file used for running a NASP job.
 *
 * @author Jean-Paul Labadie
 */
public class OutputParser {

    private DocumentBuilderFactory docFactory;
    private DocumentBuilder docBuilder;
    private Document output;
    private Element mainRootElement;

    public OutputParser(){}

        public OutputParser jaxbXMLToObject(File input) {
            try {
                JAXBContext context = JAXBContext.newInstance(NASPJob.class);
                Unmarshaller un = context.createUnmarshaller();
                OutputParser job = (OutputParser) un.unmarshal(input);
                return job;
            } catch (JAXBException e) {
                e.printStackTrace();
            }
            return null;
        }


    /*
    Writes the current XML DOM to the disk using the filename given
    in the default output directory. This finished XML represents
    the entirety of the NASP tool job request, and can be sent to
    a remote service running NASP to begin a new job.
    */
    public void jaxbObjectToXML(JobParameters job, String name) {
        try {
            JAXBContext context = JAXBContext.newInstance(JobParameters.class);
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);


            // Write to System.out for debugging
            // m.marshal(emp, System.out);

            // Write to File
            m.marshal(job, new File(name));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
