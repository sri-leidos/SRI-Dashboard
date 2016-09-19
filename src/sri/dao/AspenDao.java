package sri.dao;

import sri.data.Inspection;
import sri.data.Vehicle;

public interface AspenDao {

	
	public Inspection getInspectionForReportNumber(String reportNumber);

	public Inspection getInspectionForUsDotNumber(String usDotNumber);

	public Vehicle getVehicleForReportNumber(String reportNumber);

	public void insertInspection(Inspection inspection);

	public void insertVehicle(Vehicle vehicle);

	
}
