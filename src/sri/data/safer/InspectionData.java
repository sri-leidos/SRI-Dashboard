package sri.data.safer;

import javax.xml.bind.annotation.XmlRootElement;

import sri.data.SriPojo;

@XmlRootElement
public class InspectionData extends SriPojo {

	private String usDOTNumber;
	private String country;
	private InspectionDataRow inspections;
	private InspectionDataRow outOfService;
	private InspectionDataRow outOfServicePercent;
	private InspectionDataRow natAveragePercent;
	
	public String getUsDOTNumber() {
		return usDOTNumber;
	}
	public void setUsDOTNumber(String usDOTNumber) {
		this.usDOTNumber = usDOTNumber;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public InspectionDataRow getInspections() {
		return inspections;
	}
	public void setInspections(InspectionDataRow inspections) {
		this.inspections = inspections;
	}
	public InspectionDataRow getOutOfService() {
		return outOfService;
	}
	public void setOutOfService(InspectionDataRow outOfService) {
		this.outOfService = outOfService;
	}
	public InspectionDataRow getOutOfServicePercent() {
		return outOfServicePercent;
	}
	public void setOutOfServicePercent(InspectionDataRow outOfServicePercent) {
		this.outOfServicePercent = outOfServicePercent;
	}
	public InspectionDataRow getNatAveragePercent() {
		return natAveragePercent;
	}
	public void setNatAveragePercent(InspectionDataRow natAveragePercent) {
		this.natAveragePercent = natAveragePercent;
	}
	
	
	
}
