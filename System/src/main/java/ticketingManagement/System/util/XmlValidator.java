package ticketingManagement.System.util;

import org.springframework.stereotype.Component;
import org.springframework.core.io.ClassPathResource;

import javax.xml.XMLConstants;
import javax.xml.validation.*;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

@Component
public class XmlValidator {

	    public void validate(String xml) throws Exception {
	        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	        Schema schema = factory.newSchema(new StreamSource(new ClassPathResource("ticket.xsd").getFile()) );
	        Validator validator = schema.newValidator();
	        validator.validate(new StreamSource(new StringReader(xml)));
	    }

}
