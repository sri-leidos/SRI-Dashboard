package sri.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import sri.data.Inspection;

public class InspectionMarshal {

	public static String marshal(Inspection inspection) throws JAXBException {
		final Marshaller m = JAXBContext.newInstance(Inspection.class)
				.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		final StringWriter w = new StringWriter();
		m.marshal(inspection, w);
		return w.toString();
	}

	public static Inspection unmarshal(final String xml) throws JAXBException {
		return (Inspection) JAXBContext.newInstance(Inspection.class).createUnmarshaller()
				.unmarshal(new StringReader(xml));
	}

}
