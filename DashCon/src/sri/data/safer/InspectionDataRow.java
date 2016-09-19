package sri.data.safer;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import sri.data.SriPojo;

@XmlRootElement
public class InspectionDataRow extends SriPojo {
	
	private String usDOTNumber;
	private String country;
	private String type;
	private String vehicle;
	private String driver;
	private String hazmat;
	private String iep;
	
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
	@XmlTransient
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getHazmat() {
		return hazmat;
	}
	public void setHazmat(String hazmat) {
		this.hazmat = hazmat;
	}
	public String getIep() {
		return iep;
	}
	public void setIep(String iep) {
		this.iep = iep;
	}
}
