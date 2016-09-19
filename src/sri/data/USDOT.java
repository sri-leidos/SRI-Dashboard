package sri.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class USDOT {
	
	private String usdotNumber;
	private String sequenceNumber;
	private Integer manualEntered;
	private String userId;
	private Integer siteId;
	
	
	public String getUsdotNumber() {
		return usdotNumber;
	}
	public void setUsdotNumber(String usdotNumber) {
		this.usdotNumber = usdotNumber;
	}
	public Integer getManualEntered() {
		return manualEntered;
	}
	public void setManualEntered(Integer manualEntered) {
		this.manualEntered = manualEntered;
	}
	public String getUserName() {
		return userId;
	}
	public void setUserName(String userId) {
		this.userId = userId;
	}
	public String getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public Integer getSiteId() {
		return siteId;
	}
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	
}
