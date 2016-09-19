package sri.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import sri.data.Role;

public class RoleDaoImpl implements RoleDao{

	@Override
	public List<Role> getAllRole() {
		return null;
	}

	@Override
	public List<Role> getAllRoles() {
		
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		List<Role> roles = null;
		try {
			roles = session.selectList("sri.UserMapper.getAllRoles");
		} finally {
			session.close();
		}
		
		return roles;
	}

	@Override
	public Role getRoleById(String roleId) {
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		Role role = null;
		
		try {
			role = session.selectOne("sri.UserMapper.getRoleById", roleId);
		} finally {
			session.close();
		}
		
		return role;
	}
	
	

}
