package sri.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Vendor extends SriPojo {

	private String vendorName;
	private String vendorId;
	
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	
}
