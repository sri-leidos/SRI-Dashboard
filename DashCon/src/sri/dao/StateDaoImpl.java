package sri.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import sri.dao.MyBatisConnectionFactory;
import sri.data.State;

public class StateDaoImpl implements StateDao{

	@Override
	public List<State> getAllState() {
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		List<State> state = null;
		try {
			state = session.selectList("sri.UserMapper.getAllState");
		} finally {
			session.close();
		}
		return state;
	}

	@Override
	public State getStateById(String stateId) {
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		State state = null;
		
		try {
			state = session.selectOne("sri.UserMapper.getStateById", stateId);
		} finally {
			session.close();
		}
		
		return state;
		
	}

}
