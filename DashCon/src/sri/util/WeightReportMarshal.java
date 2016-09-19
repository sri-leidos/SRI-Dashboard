package sri.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import sri.data.WeightReport;

public class WeightReportMarshal {

	public static String marshal(WeightReport weightReport) throws JAXBException {
		final Marshaller m = JAXBContext.newInstance(WeightReport.class)
				.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		final StringWriter w = new StringWriter();
		m.marshal(weightReport, w);
		return w.toString();
	}

	public static WeightReport unmarshal(final String xml) throws JAXBException {
		return (WeightReport) JAXBContext.newInstance(WeightReport.class).createUnmarshaller()
				.unmarshal(new StringReader(xml));
	}
}
