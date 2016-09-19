package sri.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TruckFeedWimTimes {


	private Integer id;
	private Integer siteId;
	private String wimEntered;
	private String wimLeft;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSiteId() {
		return siteId;
	}
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getWimEntered() {
		if (wimEntered != null && wimEntered.indexOf('.') != -1) {
			return wimEntered.substring(0, wimEntered.indexOf('.'));
		}
		return wimEntered;
	}
	
	public void setWimEntered(String wimEntered) {
		this.wimEntered = wimEntered;
	}

	
	public String getWimLeft() {
		if (wimLeft != null && wimLeft.indexOf('.') != -1) {
			return wimLeft.substring(0, wimLeft.indexOf('.'));
		}
		return wimLeft;
	}
	public void setWimLeft(String wimLeft) {
		this.wimLeft = wimLeft;
	}
}
