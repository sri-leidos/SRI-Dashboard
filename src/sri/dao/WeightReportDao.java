package sri.dao;

import java.util.List;

import sri.data.LicensePlate;
import sri.data.TruckFeedWimTimes;
import sri.data.WeightReport;

public interface WeightReportDao {
	

	public List<WeightReport> getAllWeightReports();
	public WeightReport getWeightReport(Integer sequenceNumber);
	public void insertNewWeightReport(WeightReport wr);
	public List<WeightReport> listAllWimReports();
	public List<WeightReport> listAllStaticScaleReports();
	public WeightReport getWeight(String timestamp);
	public WeightReport getStaticWeight(String timestamp, Integer sequenceNumber, Integer siteId);
	public WeightReport getWimWeight(String timestamp, Integer sequenceNumber, Integer siteId);
	public WeightReport getWimWeightForStationAndTime(String time, Integer siteId);
	public WeightReport getWeight(TruckFeedWimTimes truckFeedWimTimes);
	public List<WeightReport> getAllWeightReportsBySiteId(String siteId);
	public WeightReport getLatestWeightReport(LicensePlate _lp);

}
