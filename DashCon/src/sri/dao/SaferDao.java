package sri.dao;

import sri.data.safer.SaferUSDOTData;

public interface SaferDao {
	
	public void insertSaferData(SaferUSDOTData sud);

	public SaferUSDOTData getSaferData(String usdotNumber);
	
}
