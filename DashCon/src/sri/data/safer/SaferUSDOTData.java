package sri.data.safer;

import javax.xml.bind.annotation.XmlRootElement;

import sri.data.SriPojo;

@XmlRootElement
public class SaferUSDOTData extends SriPojo {

	private CompanyData companyData;
	private InspectionData usInspectionData;
	private CrashData usCrashData;
	private InspectionData canadaInspectionData;
	private CrashData canadaCrashData;
	private SafetyRating safetyRating;
	
	
	public CompanyData getCompanyData() {
		return companyData;
	}
	public void setCompanyData(CompanyData companyData) {
		this.companyData = companyData;
	}
	public InspectionData getUsInspectionData() {
		return usInspectionData;
	}
	public void setUsInspectionData(InspectionData usInspectionData) {
		this.usInspectionData = usInspectionData;
	}
	public CrashData getUsCrashData() {
		return usCrashData;
	}
	public void setUsCrashData(CrashData usCrashData) {
		this.usCrashData = usCrashData;
	}
	public InspectionData getCanadaInspectionData() {
		return canadaInspectionData;
	}
	public void setCanadaInspectionData(InspectionData canadaInspectionData) {
		this.canadaInspectionData = canadaInspectionData;
	}
	public CrashData getCanadaCrashData() {
		return canadaCrashData;
	}
	public void setCanadaCrashData(CrashData canadaCrashData) {
		this.canadaCrashData = canadaCrashData;
	}
	public SafetyRating getSafetyRating() {
		return safetyRating;
	}
	public void setSafetyRating(SafetyRating safetyRating) {
		this.safetyRating = safetyRating;
	}
	
	
	
	
	
	
}
