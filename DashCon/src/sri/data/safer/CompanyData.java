package sri.data.safer;

import javax.xml.bind.annotation.XmlRootElement;

import sri.data.SriPojo;

@XmlRootElement
public class CompanyData extends SriPojo {

	private String entityType;
	private String operatingStatus;
	private String outOfServiceDate;
	private String legalName;
	private String dbaName;
	private String physicalAddress;
	private String phone;
	private String mailingAddress;
	private String usDOTNumber;
	private String stateCarrierId;
	private String mcmxffNumber;
	private String dunsNumber;
	private String powerUnits;
	private String drivers;
	private String mcs150;
	private String mcs150Mileage;
	
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public String getOperatingStatus() {
		return operatingStatus;
	}
	public void setOperatingStatus(String operatingStatus) {
		this.operatingStatus = operatingStatus;
	}
	public String getOutOfServiceDate() {
		return outOfServiceDate;
	}
	public void setOutOfServiceDate(String outOfServiceDate) {
		this.outOfServiceDate = outOfServiceDate;
	}
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	public String getDbaName() {
		return dbaName;
	}
	public void setDbaName(String dbaName) {
		this.dbaName = dbaName;
	}
	public String getPhysicalAddress() {
		return physicalAddress;
	}
	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMailingAddress() {
		return mailingAddress;
	}
	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	public String getUsDOTNumber() {
		return usDOTNumber;
	}
	public void setUsDOTNumber(String usDOTNumber) {
		this.usDOTNumber = usDOTNumber;
	}
	public String getStateCarrierId() {
		return stateCarrierId;
	}
	public void setStateCarrierId(String stateCarrierId) {
		this.stateCarrierId = stateCarrierId;
	}
	public String getMcmxffNumber() {
		return mcmxffNumber;
	}
	public void setMcmxffNumber(String mcmxffNumber) {
		this.mcmxffNumber = mcmxffNumber;
	}
	public String getDunsNumber() {
		return dunsNumber;
	}
	public void setDunsNumber(String dunsNumber) {
		this.dunsNumber = dunsNumber;
	}
	public String getPowerUnits() {
		return powerUnits;
	}
	public void setPowerUnits(String powerUnits) {
		this.powerUnits = powerUnits;
	}
	public String getDrivers() {
		return drivers;
	}
	public void setDrivers(String drivers) {
		this.drivers = drivers;
	}
	public String getMcs150() {
		return mcs150;
	}
	public void setMcs150(String mcs150) {
		this.mcs150 = mcs150;
	}
	public String getMcs150Mileage() {
		return mcs150Mileage;
	}
	public void setMcs150Mileage(String mcs150Mileage) {
		this.mcs150Mileage = mcs150Mileage;
	}
	
	
	
	
}
