package sri.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import sri.data.LicensePlate;
import sri.data.TruckFeedWimTimes;
import sri.data.WeightReport;

public class WeightReportDaoImpl implements WeightReportDao, Wipeable {
	
	SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
	SqlSession session = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public List<WeightReport> getAllWeightReports() {
		session = sqlMapper.openSession(true);
		List<WeightReport> wr = null;
		try {
			wr = session.selectList("sri.mapper.WeightReport.listAllWeightReports");
		} finally {
			session.close();
		}
		return wr;
	}
	
	@Override
	public List<WeightReport> getAllWeightReportsBySiteId( String siteId ) {
		session = sqlMapper.openSession(true);
		List<WeightReport> wr = null;
		try {
			wr = session.selectList("sri.mapper.WeightReport.listAllWeightReportsBySiteId", siteId);
		} finally {
			session.close();
		}
		return wr;
	}

	@Override
	public WeightReport getWeightReport(Integer sequenceNumber) {
		session = sqlMapper.openSession(true);
		WeightReport wr = null;
		try {
			wr = session.selectOne("sri.mapper.WeightReport.getWeightReport", sequenceNumber);
		} finally {
			session.close();
		}
		return wr;
	}

	@Override
	public List<WeightReport> listAllWimReports() {
		session = sqlMapper.openSession(true);
		List<WeightReport> wr = null;
		try {
			wr = session.selectList("sri.mapper.WeightReport.listAllWimReports");
		} finally {
			session.close();
		}
		return wr;
	}

	@Override
	public List<WeightReport> listAllStaticScaleReports() {
		session = sqlMapper.openSession(true);
		List<WeightReport> wr = null;
		try {
			wr = session.selectList("sri.mapper.WeightReport.listAllStaticScaleReports");
		} finally {
			session.close();
		}
		return wr;
	}

	@Override
	public void insertNewWeightReport(WeightReport wr) {
		session = sqlMapper.openSession(true);
		try {
			session.insert("sri.mapper.WeightReport.insertWeightReport", wr);
		} finally {
			session.close();
		}
	}

	@Override
	public WeightReport getWeight(String timestamp) {
		session = sqlMapper.openSession(true);
		WeightReport wr = null;
		try {
			wr = session.selectOne("sri.mapper.WeightReport.getWeight", timestamp);
		} finally {
			session.close();
		}
		return wr;
	}

	@Override
	public WeightReport getStaticWeight(String timestamp, Integer sequenceNumber, Integer siteId) {
		session = sqlMapper.openSession(true);
		WeightReport wr = null;
		try {
			WeightReport twr = new WeightReport();
			twr.setSiteId(siteId);
			twr.setTimestamp(timestamp);
			twr.setSequenceNumber(sequenceNumber);
			wr = session.selectOne("sri.mapper.WeightReport.getStaticWeight", twr);
		} finally {
			session.close();
		}
		return wr;
	}

	@Override
	public WeightReport getWimWeight(String timestamp, Integer sequenceNumber, Integer siteId) {
		session = sqlMapper.openSession(true);
		WeightReport wr = null;
		try {
			WeightReport twr = new WeightReport();
			twr.setSiteId(siteId);
			twr.setTimestamp(timestamp);
			twr.setSequenceNumber(sequenceNumber);
			wr = session.selectOne("sri.mapper.WeightReport.getWimWeight", twr);
		} finally {
			session.close();
		}
		return wr;
	}

	@Override
	public WeightReport getWimWeightForStationAndTime(String time, Integer siteId) {
		session = sqlMapper.openSession(true);
		WeightReport wr = null;
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("siteId",siteId+"");
			String minusFive = getDateStringWithSecondOffset(time, -5);
			String plusFive = getDateStringWithSecondOffset(time, 5);
			map.put("startTime", minusFive);
			map.put("endTime", plusFive);
			
			wr = session.selectOne("sri.mapper.WeightReport.getWeightBetweenTimesForStation", map);
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		
		return wr;
	}
	
	@Override
	public WeightReport getWeight(TruckFeedWimTimes tfWimTimes) {
		session = sqlMapper.openSession(true);
		WeightReport wr = null;
		
		try {
			wr = session.selectOne("sri.mapper.WeightReport.getWeightByWimTimes", tfWimTimes);
		} finally {
			session.close();
		}
		
		return wr;
	}
	
	

	private String getDateStringWithSecondOffset(String dateTime, int offset) throws ParseException{
		String ret = null;
		Date d = sdf.parse(dateTime);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, offset);
		Date retDate = cal.getTime();
		ret = sdf.format(retDate);
		return ret;
	}

	@Override
	public WeightReport getLatestWeightReport(LicensePlate _lp) {
		session = sqlMapper.openSession(true);
		WeightReport wr = null;
		
		try {
			wr = session.selectOne("sri.mapper.WeightReport.getLatestWeightReport", _lp);
		} finally {
			session.close();
		}
		
		return wr;
	}

	@Override
	public void wipeTables() {
		session = sqlMapper.openSession(true);
		
		try {
			session.delete("sri.mapper.WeightReport.wipeWeightReport");
		} finally {
			session.close();
		}
		
	}
}
