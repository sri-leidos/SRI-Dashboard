package sri.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Role extends SriPojo {
	private Integer roleId; 
	private String roleName;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	

	
}
