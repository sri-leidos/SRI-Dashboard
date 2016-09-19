package sri.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SiteType extends SriPojo {
	
	private Integer id; 
	private String description;
	

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	

}
