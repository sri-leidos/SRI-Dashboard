package sri.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import sri.data.LicensePlate;


public class LicensePlateDaoImpl implements LicensePlateDao, Wipeable {

	SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
	SqlSession session = null;
	
	@Override
	public List<LicensePlate> getFiftyLicensePlates() {
		session = sqlMapper.openSession(true);
		List<LicensePlate> licensePlates = null;
		
		try {
			licensePlates = session.selectList("sri.mapper.LicensePlate.listFiftyLicensePlates");
		} finally {
			session.close();
		}
		
		return licensePlates;
	}

	@Override
	public List<LicensePlate> getAllSiteLicensePlates(Integer _siteId) {
		session = sqlMapper.openSession(true);
		List<LicensePlate> licensePlates = null;
		
		try {
			licensePlates = session.selectList("sri.mapper.LicensePlate.listAllSiteLicensePlates", _siteId);
		} finally {
			session.close();
		}
		
		return licensePlates;
	}

	@Override
	public LicensePlate getLprBySequenceNumberAndSiteId(LicensePlate _licensePlate) {
		session = sqlMapper.openSession(true);
		LicensePlate licensePlate = null;
		
		try {
			licensePlate = session.selectOne("sri.mapper.LicensePlate.selectLprBySequenceNumberAndSiteId", _licensePlate);
		} finally {
			session.close();
		}
		
		return licensePlate;
	}

	@Override
	public LicensePlate insertLicensePlate(LicensePlate lpr) {
		session = sqlMapper.openSession(true);
		try {
			session.insert("sri.mapper.LicensePlate.insertLicensePlate", lpr);
		} finally {
			session.close();
		}
		return lpr;
	}

	@Override
	public void wipeTables() {
		session = sqlMapper.openSession(true);
		
		try {
			session.delete("sri.mapper.LicensePlate.wipeLPRTable");
		} finally {
			session.close();
		}
		
	}

}
