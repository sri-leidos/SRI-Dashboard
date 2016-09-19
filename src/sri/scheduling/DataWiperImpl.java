package sri.scheduling;

import sri.dao.Wipeable;

public class DataWiperImpl implements DataWiper{

	private Wipeable wiperDao;
	

	@Override
	public void wipeData() {
		System.out.println("Wiping tables");
		wiperDao.wipeTables();
	}

	
	
	public Wipeable getWiperDao() {
		return wiperDao;
	}

	public void setWiperDao(Wipeable wiperDao) {
		this.wiperDao = wiperDao;
	}
	
}
