package sri.ws;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import sri.dao.AspenDao;
import sri.dao.AspenDaoImpl;
import sri.data.Inspection;
import sri.data.Vehicle;

@Path("sec/aspen")
public class AspenSecResource {

	@Context
	protected UriInfo uriInfo;
	@Context
	protected HttpServletRequest req;
	@Context
	protected HttpServletResponse resp;

	private AspenDao aspenDao = new AspenDaoImpl();

	@GET @Path("{rptnum}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Inspection getInspectionByRptnum(@PathParam("rptnum") String rptnum) {
		Inspection inspection = aspenDao.getInspectionForReportNumber(rptnum);
		return inspection;
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createInspection(Inspection inspection) {
		aspenDao.insertInspection(inspection);

		return Response.status(200).entity(inspection.toString()).build();
	}
	
	@POST
	@Path("vehicle")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createVehicle(Vehicle vehicle) {
		aspenDao.insertVehicle(vehicle);

		return Response.status(200).entity(vehicle.toString()).build();
	}
	
	@GET @Path("vehicle/{rptnum}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Vehicle getVehicleByRptnum(@PathParam("rptnum") String rptnum) {
		Vehicle vehicle = aspenDao.getVehicleForReportNumber(rptnum);
		return vehicle;
	}
	
	@GET @Path("vehicle/iyeTek/{rptnum}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.TEXT_PLAIN})
	public String getVehicleIyeTekByRptnum(@PathParam("rptnum") String rptnum) {
		Vehicle vehicle = aspenDao.getVehicleForReportNumber(rptnum);
		return vehicle.toIyeTekXml();
	}
	
	@GET @Path("iyeTek/{rptnum}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.TEXT_PLAIN})
	public String getInspIyeTekByRptnum(@PathParam("rptnum") String rptnum) {
		Inspection inspection = aspenDao.getInspectionForReportNumber(rptnum);
		return inspection.toIyeTekXml();
	}
	
}
