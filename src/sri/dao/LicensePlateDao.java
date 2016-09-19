package sri.dao;

import java.util.List;

import sri.data.LicensePlate;


public interface LicensePlateDao {
	
	public List<LicensePlate> getFiftyLicensePlates();
	public List<LicensePlate> getAllSiteLicensePlates(Integer siteId);
	public LicensePlate getLprBySequenceNumberAndSiteId(LicensePlate lpr);
	public LicensePlate insertLicensePlate(LicensePlate lpr);

}
