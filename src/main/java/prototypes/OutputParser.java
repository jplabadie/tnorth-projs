package prototypes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import xmlsources.NaspInputData;
import xmlsources.ObjectFactory;

import javax.xml.bind.*;
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

    /**\
     *
      * @param xml_path the absolute path to the xml file we will use to create Java objects
     * @return a populated NaspInputData object with references to related classes
     */
    public NaspInputData jaxbXMLToObject(File xml_path) {
        try {
            JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            NaspInputData naspData = ((JAXBElement<NaspInputData>) unmarshaller.unmarshal(xml_path)).getValue();
            return naspData;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  Writes the current XML DOM to the disk using the filename given
     *  in the default output directory. This finished XML represents
     *  the entirety of the NASP tool job request, and can be sent to
     *  a remote service running NASP to begin a new job.
     *
     * @param input_for_conversion NaspInputData object which will be converted to XML for output to NASP
     * @param output_path the absolute path desired for the output XML
     */
    public void jaxbObjectToXML(NaspInputData input_for_conversion, String output_path) {
        try {
            JAXBContext context = JAXBContext.newInstance(NaspInputData.class);
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);


            // Write to File
            m.marshal(input_for_conversion, new File(output_path));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
