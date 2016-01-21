package prototypes;


import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.InputMismatchException;

/**
 * @author Jean-Paul Labadie
 */
public class OutputParser {

    private DocumentBuilderFactory docFactory;
    private DocumentBuilder docBuilder;
    private Document output;

    public OutputParser(){
        docFactory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        try {
            output = docBuilder.parse(ClassLoader.getSystemResourceAsStream("output.xml"));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addElement(Attr attribute, Element element) throws InputMismatchException{


    }
}
