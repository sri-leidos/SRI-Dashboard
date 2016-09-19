package sri.data.safer;

import javax.xml.bind.annotation.XmlRootElement;

import sri.data.SriPojo;

@XmlRootElement
public class CrashData extends SriPojo {

	
	private String usDOTNumber;
	private String country;
	private String fatal;
	private String injury;
	private String tow;
	private String total;
	
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
	public String getFatal() {
		return fatal;
	}
	public void setFatal(String fatal) {
		this.fatal = fatal;
	}
	public String getInjury() {
		return injury;
	}
	public void setInjury(String injury) {
		this.injury = injury;
	}
	public String getTow() {
		return tow;
	}
	public void setTow(String tow) {
		this.tow = tow;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
	
}
