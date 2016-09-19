package sri.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import sri.dao.MyBatisConnectionFactory;
import sri.data.User;
import sri.data.UserGroup;
import sri.util.ShaHasher;


public class UserDaoImpl implements UserDao{

	@Override
	public List<User> getAllUser() {

		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		List<User> users = null;
		try {
			users = session.selectList("sri.UserMapper.getAllUsers");
		} finally {
			session.close();
		}
		return users;
	}
	
	@Override
	public User getUserByUserId( String userId ) {
		
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		User user = null;
		
		try {
			user = session.selectOne("sri.UserMapper.getUserByUserId", userId);
		} finally {
			session.close();
		}
		
		return user;
		
	}
	
	@Override
	public String getUserPasswordbyUserId( String userId ) {
		
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		String password;
		
		try {
			password = session.selectOne("sri.UserMapper.getUserPasswordByUserId", userId);
		} finally {
			session.close();
		}
		
		return password;
		
	}

	@Override
	public void insertNewUser(User user) {
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);

		//Hash the Password
		user.setUserPassword(ShaHasher.hash(user.getUserPassword()));

		try {
			session.insert("sri.UserMapper.insertNewUser", user);
		} finally {
			session.close();
		}			
	}

	@Override
	public void insertUserGroup(UserGroup userGroup) {
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);

		try {
			session.insert("sri.UserMapper.insertUserGroup", userGroup);
		} finally {
			session.close();
		}	
	}

	
	
	@Override
	public void insertNewUserRole(String userId) {
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);

		try {
			session.insert("sri.UserMapper.insertNewUserRole", userId);
		} finally {
			session.close();
		}	
	}

	@Override
	public User getUserByEmail(String email) {
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		User userId = null;
		
		try {
			userId = session.selectOne("sri.UserMapper.getUserByEmail", email);
		} finally {
			session.close();
		}
		
		return userId;
	}

	@Override
	public List<User> getUsersByStateId(String stateId) {
		
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		
		List <User> users = null;
		
		try {
			users = session.selectList("sri.UserMapper.getUsersByStateId", stateId);
		} finally {
			session.close();
		}
		return users;
	}

	@Override
	public List<User> getUsersBySiteId(String siteId) {
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		List<User> users = null;
		
		try {
			users = session.selectList("sri.UserMapper.getAllUsersBySiteId", siteId);
		} finally {
			session.close();
		}
		return users;
	}

	@Override
	public void deleteUserByUserId(String userId) {
		
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		
		try {
			session.delete("sri.UserMapper.deleteUserByUserId", userId);
		} finally {
			session.close();
		}
		
	}
	
	@Override
	public void deleteUserGroupByUserId(String userId) {

		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		
		try {
			session.delete("sri.UserMapper.deleteUserGroupByUserId", userId);
		} finally {
			session.close();
		}
		
	}

	@Override
	public User updateUser(User user) {
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		
		try {
			session.selectOne("sri.UserMapper.updateUser", user);
		} finally {
			session.close();
		}
		
		return user;
		
	}

	@Override
	public User updatePassword(User user) {
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		
		try {
			session.selectOne("sri.UserMapper.updateUserPassword", user);
		} finally {
			session.close();
		}
		
		return user;
		
	}

	@Override
	public User updateRole(User userId) {
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		
		try {
			session.selectOne("sri.UserMapper.updateRole", userId);
		} finally {
			session.close();
		}
		return userId;
		
	}

	@Override
	public User getUserRoleByUserId(String userId) {
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		
		User user= null;
		try {
			 user = session.selectOne("sri.UserMapper.getUserRoleByUserId", userId);
		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public void updateUserEmail( User user ) {
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		
		try {
			 user = session.selectOne("sri.UserMapper.updateUserEmail", user);
		} finally {
			session.close();
		}
	}


}
