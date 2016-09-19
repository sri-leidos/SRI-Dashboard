package sri.data;

public class WIM extends SriPojo {
	
	private String axleCount = "4";
	private String declaredWeight = "8.0 k";
	private String grossWeight = "70.2 k";
	private String status = "passed"; 
	private String reason = "place Holder";
	
	
	public String getAxleCount() {
		return axleCount;
	}
	public void setAxleCount(String axleCount) {
		this.axleCount = axleCount;
	}
	public String getDeclaredWeight() {
		return declaredWeight;
	}
	public void setDeclaredWeight(String declaredWeight) {
		this.declaredWeight = declaredWeight;
	}
	public String getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(String grossWeight) {
		this.grossWeight = grossWeight;
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
	
	
	
}
