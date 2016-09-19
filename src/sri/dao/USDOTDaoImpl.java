package sri.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import sri.data.USDOT;

public class USDOTDaoImpl implements USDOTDao,Wipeable {
	
	SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
	SqlSession session = null;

	@Override
	public List<USDOT> listAllUSDOTNumbers() {
		List<USDOT> list = null;
		session = sqlMapper.openSession(true);
		try {
			list = session.selectList("sri.mapper.USDOT.listAllUSDOTNumbers");
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<USDOT> listAllUSDOTNumbersByUser(String userId) {
		List<USDOT> list = null;
		session = sqlMapper.openSession(true);
		try {
			list = session.selectList("sri.mapper.USDOT.listUSDOTNumbersByUser", userId);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<USDOT> listAllUSDOTNumbersEnteredManually() {
		List<USDOT> list = null;
		session = sqlMapper.openSession(true);
		try {
			list = session.selectList("sri.mapper.USDOT.listUSDOTNumbersEnteredManually");
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public USDOT getUSDOTNumberObject(String sequenceNumber) {
		USDOT usdotNumber = null;
		session = sqlMapper.openSession(true);
		try {
			usdotNumber = session.selectOne("sri.mapper.USDOT.getUSDOTNumber", sequenceNumber);
		} finally {
			session.close();
		}
		return usdotNumber;
	}

	@Override
	public USDOT getUSDOTNumberObjectBySequenceAndSiteId(USDOT _usdot) {
		USDOT usdotNumber = null;
		session = sqlMapper.openSession(true);
		try {
			usdotNumber = session.selectOne("sri.mapper.USDOT.getUSDOTNumberBySequenceAndSiteId", _usdot);
		} finally {
			session.close();
		}
		return usdotNumber;
	}

	@Override
	public void updateUSDOTNumber(USDOT usdot) {
		session = sqlMapper.openSession(true);
		try {
			session.update("sri.mapper.USDOT.updateUSDOTNumber", usdot);
		} finally {
			session.close();
		}
	}
	
	@Override
	public void insertUSDOT( USDOT usdot ) {
		session = sqlMapper.openSession(true);
		try {
			session.insert("sri.mapper.USDOT.insertUSDOTNumber", usdot);
		} finally {
			session.close();
		}
		
	}

	@Override
	public void deleteUSDOT(String sequenceNumber) {
		session = sqlMapper.openSession(true);
		try {
			session.delete("sri.mapper.USDOT.deleteUSDOT", sequenceNumber);
		} finally {
			session.close();
		}
	}

	@Override
	public void wipeTables() {
		session = sqlMapper.openSession(true);
		try {
			session.delete("sri.mapper.USDOT.wipeUSDOT");
		} finally {
			session.close();
		}
	}

}
