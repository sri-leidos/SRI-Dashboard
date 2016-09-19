package sri.data;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class UserGroup extends SriPojo {

	private String userId;
	private String groupId;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

}
