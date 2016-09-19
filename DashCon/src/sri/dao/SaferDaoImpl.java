package sri.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import sri.data.safer.CompanyData;
import sri.data.safer.CrashData;
import sri.data.safer.InspectionData;
import sri.data.safer.InspectionDataRow;
import sri.data.safer.SaferUSDOTData;
import sri.data.safer.SafetyRating;

public class SaferDaoImpl implements SaferDao, Wipeable{

	SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
	SqlSession session = null;

	@Override
	public void insertSaferData(SaferUSDOTData sud) {
		session = sqlMapper.openSession(true);
		
		try {
			session.insert("sri.mapper.Safer.insertSaferData", sud);
		} catch (Exception e ) {
			System.out.println(e);
			System.out.println(e.getStackTrace());
		} finally {
			session.close();
		}			
	}
	
	@Override
	public SaferUSDOTData getSaferData(String usdotNumber) {
		session = sqlMapper.openSession(true);

		SaferUSDOTData ret = new SaferUSDOTData();
		try {
			CompanyData cd = (CompanyData)session.selectOne("sri.mapper.Safer.getCompanyData", usdotNumber);
			SafetyRating sr = (SafetyRating)session.selectOne("sri.mapper.Safer.getSafetyRatingData", usdotNumber);
			CrashData ccd = (CrashData)session.selectOne("sri.mapper.Safer.getCanadaCrashData", usdotNumber);
			CrashData ucd = (CrashData)session.selectOne("sri.mapper.Safer.getUsCrashData", usdotNumber);

			ret.setCompanyData(cd);
			ret.setSafetyRating(sr);
			ret.setCanadaCrashData(ccd);
			ret.setUsCrashData(ucd);
			
			Map<Integer, InspectionDataRow> usIdColl = session.selectMap("sri.mapper.Safer.getUsInspectionData", usdotNumber, "type");
			if (usIdColl.size() > 0) {
				InspectionData usInspectionData = new InspectionData();
				usInspectionData.setInspections(usIdColl.get("1"));
				usInspectionData.setNatAveragePercent(usIdColl.get("2"));
				usInspectionData.setOutOfService(usIdColl.get("3"));
				usInspectionData.setOutOfServicePercent(usIdColl.get("4"));
				ret.setUsInspectionData(usInspectionData);
			}
			
			Map<Integer, InspectionDataRow> canadaIdColl = session.selectMap("sri.mapper.Safer.getCanadaInspectionData", usdotNumber, "type");
			if (canadaIdColl.size() > 0) {
				InspectionData canadaInspectionData = new InspectionData();
				canadaInspectionData.setInspections(canadaIdColl.get("1"));
				canadaInspectionData.setOutOfService(canadaIdColl.get("3"));
				canadaInspectionData.setOutOfServicePercent(canadaIdColl.get("4"));
				ret.setCanadaInspectionData(canadaInspectionData);
			}
		} finally {
			session.close();
		}

		return ret;
	}

	@Override
	public void wipeTables() {

		session = sqlMapper.openSession(true);
		try {
			session.delete("sri.mapper.Safer.deleteAllSaferData");
		} finally {
			session.close();
		}
		
		
	}
}
