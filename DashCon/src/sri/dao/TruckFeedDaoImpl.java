package sri.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import sri.data.TruckFeed;
import sri.data.TruckFeedWimTimes;

public class TruckFeedDaoImpl implements TruckFeedDao, Wipeable {
	
	SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
	SqlSession session = null;
	
	@Override
	public List<TruckFeed> listAllTrucks() {
		session = sqlMapper.openSession(true);
		List<TruckFeed> truckFeed = null;
		
		try {
			truckFeed = session.selectList("sri.mapper.TruckFeed.listAllTruckFeed");
		} finally {
			session.close();
		}
		
		return truckFeed;
	}

	@Override
	public TruckFeed getTruckFeed(Timestamp timestamp) {
		session = sqlMapper.openSession(true);
		TruckFeed truck = null;
		try {
			truck = session.selectOne("sri.mapper.TruckFeed.getTruckFeed", timestamp);
		} finally {
			session.close();
		}
		return truck;
	}

	@Override
	public TruckFeed getTruckFeedBySequenceNumberAndSiteId(String sequenceNumber, Integer siteId) {
		session = sqlMapper.openSession(true);
		TruckFeed truck;
		try {
			TruckFeed truckFeed = new TruckFeed();
			truckFeed.setSiteId(siteId);
			truckFeed.setSequenceNumber(sequenceNumber);
			truck = session.selectOne("sri.mapper.TruckFeed.getTruckFeedBySequenceNumberAndSiteId", truckFeed);
		} finally {
			session.close();
		}
		return truck;
	}

	@Override
	public TruckFeed getTruckFeed(String timestamp, String sequenceNumber, Integer siteId) {
		session = sqlMapper.openSession(true);
		TruckFeed truck = null;
		try {
			TruckFeed ttf = new TruckFeed();
			ttf.setTimestamp(timestamp);
			ttf.setSiteId(siteId);
			ttf.setSequenceNumber(sequenceNumber);
			truck = session.selectOne("sri.mapper.TruckFeed.getTruckFeed", timestamp);
		} finally {
			session.close();
		}
		return truck;
	}

	@Override
	public TruckFeed insertTruckFeed(TruckFeed truckfeed) {
		session = sqlMapper.openSession(true);
		try {
			session.insert("sri.mapper.TruckFeed.insertTruckFeed", truckfeed);
			System.out.println("Truck ID -> " + truckfeed.getId());
		} finally {
			session.close();
		}
		return truckfeed;
	}

	@Override
	public void updateTruckFeedWimEntered(TruckFeed truckfeed) {
		session = sqlMapper.openSession(true);
		try {
			session.update("sri.mapper.TruckFeed.updateTruckFeedWimEntered", truckfeed);
		} finally {
			session.close();
		}
	}

	@Override
	public void updateTruckFeedWimLeft(TruckFeed truckfeed) {
		session = sqlMapper.openSession(true);
		try {
			session.update("sri.mapper.TruckFeed.updateTruckFeedWimLeft", truckfeed);
		} finally {
			session.close();
		}
	}

	@Override
	public TruckFeedWimTimes getTruckFeedWimTimes(Integer id) {
		session = sqlMapper.openSession(true);
		TruckFeedWimTimes truck = null;
		try {
			truck = session.selectOne("sri.mapper.TruckFeed.getTruckFeedWimTimes", id);
		} finally {
			session.close();
		}
		return truck;
	}

	@Override
	public void wipeTables() {
		session = sqlMapper.openSession(true);
		try {
			session.selectOne("sri.mapper.TruckFeed.wipeTruckFeed");
		} finally {
			session.close();
		}
	}

}
