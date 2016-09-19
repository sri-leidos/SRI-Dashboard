package sri.dao;

import java.util.List;

import sri.data.Role;

public interface RoleDao {
	
	public List<Role> getAllRole();

	public List<Role> getAllRoles();

	public Role getRoleById(String roleId);

}
