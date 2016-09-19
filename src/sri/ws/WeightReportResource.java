package sri.ws;

import java.util.List;

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

import sri.dao.UserDao;
import sri.dao.UserDaoImpl;
import sri.dao.WeightReportDao;
import sri.dao.WeightReportDaoImpl;
import sri.data.User;
import sri.data.WeightReport;

/*
 * @author 	rothr
 * @desc	This will be the base resource for Weight Reports
 * 			delivering tests for Metler-Toledo's resources.
 */
@Path("weight")
public class WeightReportResource {

	@Context
	protected UriInfo uriInfo;
	@Context
	protected HttpServletRequest req;
	@Context
	protected HttpServletResponse resp;
	
	private WeightReportDao reportsDao = new WeightReportDaoImpl();
	
	/*
	 * @author	rothr
	 * @desc	Lists all Weight Reports filtered by the current
	 * 			user's Site ID
	 */
	@GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<WeightReport> listAllWeightReportsBySiteId() {
		String userId = req.getUserPrincipal().getName();
		UserDao userDao = new UserDaoImpl();
		User user = userDao.getUserByUserId(userId);
		List<WeightReport> weightReports = reportsDao.getAllWeightReportsBySiteId( user.getSiteId() );
    	return weightReports;
    }
	
	/*
	 * @author	rothr
	 * @desc	Lists all Weight Reports within the database
	 * 			regardless of Scale Type and Status
	 */
	@GET @Path("all")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<WeightReport> listAllWeightReports() {
		List<WeightReport> weightReports = reportsDao.getAllWeightReports();
    	return weightReports;
    }
	
	/*
	 * @author	rothr
	 * @desc	Get a Wim Weight Report based on a timestamp.
	 */		
	@GET 
	@Path("wim/{timestamp}/{sequenceNumber}/{siteId}")
	@Produces({MediaType.APPLICATION_JSON})
	public WeightReport getWimWeight(
			@PathParam("timestamp") String timestamp, 
			@PathParam("sequenceNumber") Integer sequenceNumber,
			@PathParam("siteId") Integer siteId) {

		WeightReport report = reportsDao.getWimWeight(timestamp, sequenceNumber, siteId);
		return report;
	}
	
	/*
	 * @author	blackch
	 * @desc	Get a Static Weight Report based on a timestamp and sequence number.
	 */		
	@GET
	@Path("static/{timestamp}/{sequenceNumber}/{siteId}")
    @Produces({MediaType.APPLICATION_JSON})
	public WeightReport getStaticWeight(
			@PathParam("timestamp") String timestamp, 
			@PathParam("sequenceNumber") Integer sequenceNumber,
			@PathParam("siteId") Integer siteId) {
	
		WeightReport report = reportsDao.getStaticWeight(timestamp, sequenceNumber, siteId);
		return report;

	}
	
	/*
	 * @author rothr
	 * @desc	List all Weight Report entries with Wim as
	 * 			their Scale Type.
	 */
	@GET @Path("all/wim")
	public List<WeightReport> listAllWimReports() {
		List<WeightReport> wr = reportsDao.listAllWimReports();
		return wr;
	}
	
	/*
	 * @author rothr
	 * @desc	List all Weight Report entries with Static Scale as
	 * 			their Scale Type.
	 */
	@GET @Path("all/scale")
	public List<WeightReport> listAllStaticScaleReports() {
		List<WeightReport> wr = reportsDao.listAllStaticScaleReports();
		return wr;
	}
	
	/*
	 * @author	rothr
	 * @desc	Insert a new Weight Report into our database
	 * 			with the report given in the request.
	 * @params	"report" will be the object given in the POST request.
	 */
	@POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response insertNewWeight(WeightReport report) {
		reportsDao.insertNewWeightReport(report);
		return Response.status(200).entity(report.toString()).build();
	}
	
}
