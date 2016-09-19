package sri.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WeightReport extends SriPojo {
	
	private Integer siteId;
	private String scaleType;
	private String timestamp;
	private Integer sequenceNumber;
	private Integer axleCount;
	private Integer grossWeight;
	private String massUnit;
	private String status;
	private String reason;
	/*
	 * We get the US DOT Number from a JOIN
	 * with the SRI.USDOT_NUMBER table
	 */
	private USDOT usdotNumber;
	
	
	public Integer getSiteId() {
		return siteId;
	}
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	public String getScaleType() {
		return scaleType;
	}
	public void setScaleType(String scaleType) {
		this.scaleType = scaleType;
	}
	public String getTimestamp() {
		if (timestamp != null && timestamp.indexOf('.') != -1) {
			return timestamp.substring(0, timestamp.indexOf('.'));
		}
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public Integer getAxleCount() {
		return axleCount;
	}
	public void setAxleCount(Integer axleCount) {
		this.axleCount = axleCount;
	}
	public Integer getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(Integer grossWeight) {
		this.grossWeight = grossWeight;
	}
	public String getMassUnit() {
		return massUnit;
	}
	public void setMassUnit(String massUnit) {
		this.massUnit = massUnit;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public USDOT getUsdotNumber() {
		return usdotNumber;
	}
	public void setUsdotNumber(USDOT usdotNumber) {
		this.usdotNumber = usdotNumber;
	}
	
}
