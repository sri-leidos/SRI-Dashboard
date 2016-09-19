package sri.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class State extends SriPojo {
	
	private String stateId;
	private String stateName;
	
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}
