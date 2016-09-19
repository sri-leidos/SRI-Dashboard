package sri.dao;

import java.util.List;

import sri.data.USDOT;

public interface USDOTDao {

	public List<USDOT> listAllUSDOTNumbers();
	public List<USDOT> listAllUSDOTNumbersByUser(String userId);
	public List<USDOT> listAllUSDOTNumbersEnteredManually();
	public USDOT getUSDOTNumberObject(String sequenceNumber);
	public void updateUSDOTNumber(USDOT usdot);
	public void insertUSDOT(USDOT usdot);
	public void deleteUSDOT(String sequenceNumber);
	public USDOT getUSDOTNumberObjectBySequenceAndSiteId(USDOT _usdot);
	
}
