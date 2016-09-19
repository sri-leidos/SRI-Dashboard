package sri.data.safer;

import javax.xml.bind.annotation.XmlRootElement;

import sri.data.SriPojo;

@XmlRootElement
public class SafetyRating extends SriPojo {

	
	private String usDOTNumber;
	private String ratingDate;
	private String reviewDate;
	private String rating;
	private String type;
	
	
	public String getUsDOTNumber() {
		return usDOTNumber;
	}
	public void setUsDOTNumber(String usDOTNumber) {
		this.usDOTNumber = usDOTNumber;
	}
	public String getRatingDate() {
		return ratingDate;
	}
	public void setRatingDate(String ratingDate) {
		this.ratingDate = ratingDate;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
