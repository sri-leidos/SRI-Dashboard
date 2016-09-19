package sri.dao;

import java.sql.Timestamp;
import java.util.List;

import sri.data.TruckFeed;
import sri.data.TruckFeedWimTimes;

public interface TruckFeedDao {
	
	public List<TruckFeed> listAllTrucks();
	public TruckFeed getTruckFeed(Timestamp timestamp);
	public TruckFeed getTruckFeedBySequenceNumberAndSiteId(String sequenceNumber, Integer siteId);
	public TruckFeed getTruckFeed(String timestamp, String sequenceNumber, Integer siteId);
	public TruckFeed insertTruckFeed(TruckFeed truckfeed);
	public void updateTruckFeedWimEntered(TruckFeed truckfeed);
	public void updateTruckFeedWimLeft(TruckFeed truckfeed);
	public TruckFeedWimTimes getTruckFeedWimTimes(Integer id);

}
