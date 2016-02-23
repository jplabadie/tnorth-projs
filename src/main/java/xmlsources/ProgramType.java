
package xmlsources;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProgramType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ProgramType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Aligner"/>
 *     &lt;enumeration value="SNPCaller"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ProgramType", namespace = "http://www.tgen.org/NaspInputSchema")
@XmlEnum
public enum ProgramType {

    @XmlEnumValue("Aligner")
    ALIGNER("Aligner"),
    @XmlEnumValue("SNPCaller")
    SNP_CALLER("SNPCaller");
    private final String value;

    ProgramType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ProgramType fromValue(String v) {
        for (ProgramType c: ProgramType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
