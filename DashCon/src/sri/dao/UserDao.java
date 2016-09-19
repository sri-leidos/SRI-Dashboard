package sri.dao;

import java.util.List;

import sri.data.User;
import sri.data.UserGroup;

public interface UserDao {
	
	public List <User> getAllUser();
	
	public void insertNewUser(User user);
	
	public void insertUserGroup(UserGroup userGroup);

	public void insertNewUserRole(String userId);

	public User getUserByUserId(String userId);
	
	public User getUserByEmail(String email);
	
	public User getUserRoleByUserId(String userId);

	public List<User> getUsersBySiteId(String roleId);
	
	public List<User> getUsersByStateId(String stateId);
	
	public void deleteUserByUserId(String userId);
	
	public void deleteUserGroupByUserId(String userId);

	public User updateUser (User user);
	
	public void updateUserEmail (User user);
	
	public User updateRole (User userid);

	public String getUserPasswordbyUserId(String userId);

	public User updatePassword(User user);

}
