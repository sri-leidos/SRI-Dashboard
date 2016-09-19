package sri.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import sri.data.Inspection;
import sri.data.Vehicle;


public class AspenDaoImpl implements AspenDao {

	@Override
	public Vehicle getVehicleForReportNumber(String reportNumber) {
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		Vehicle ret = null;
		try {
			ret = (Vehicle) session.selectOne("sri.AspenMapper.selectVehicle", reportNumber);
		} finally {
			session.close();
		}

		return ret;
	}

	@Override
	public Inspection getInspectionForReportNumber(String reportNumber) {
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		Inspection ret = null;
		try {
			ret = (Inspection) session.selectOne("sri.AspenMapper.selectInspByReportNumber", reportNumber);
		} finally {
			session.close();
		}

		return ret;
	}

	@Override
	public Inspection getInspectionForUsDotNumber(String usDotNumber) {
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		Inspection ret = null;
		try {
			ret = (Inspection) session.selectOne("sri.AspenMapper.selectInspByUsDotNumber", usDotNumber);
		} finally {
			session.close();
		}

		return ret;
	}

	@Override
	public void insertInspection(Inspection inspection) {
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);

		try {
			session.insert("sri.AspenMapper.insertInspection", inspection);
		} catch (Exception e ) {
			System.out.println(e);
			System.out.println(e.getStackTrace());
		} finally {
			session.close();
		}			
	}

	@Override
	public void insertVehicle(Vehicle vehicle) {
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);

		try {
			session.insert("sri.AspenMapper.insertVehicle", vehicle);
		} catch (Exception e ) {
			System.out.println(e);
			System.out.println(e.getStackTrace());
		} finally {
			session.close();
		}			
	}

}