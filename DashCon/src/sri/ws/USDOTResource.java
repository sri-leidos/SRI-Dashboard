package sri.ws;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import sri.dao.USDOTDao;
import sri.dao.USDOTDaoImpl;
import sri.data.USDOT;


@Path("usdot")
public class USDOTResource {

	@Context
	protected UriInfo uriInfo;
	@Context
	protected HttpServletRequest req;
	@Context
	protected HttpServletResponse resp;
	
	private USDOTDao usdotDao = new USDOTDaoImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<USDOT> getAllUSDOTNumbers() {
		List<USDOT> list = null;
		list = usdotDao.listAllUSDOTNumbers();
		return list;
	}
	
	@GET @Path("{sequenceNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public USDOT getUSDOTNumber( @PathParam("sequenceNumber") String sequenceNumber ) {
		USDOT usdotNumber = null;
		usdotNumber = usdotDao.getUSDOTNumberObject( sequenceNumber );
		return usdotNumber;
	}
	
	@GET 
	@Path("{sequenceNumber}/{siteId}")
	@Produces(MediaType.APPLICATION_JSON)
	public USDOT getUSDOTNumberBySequenceAndSiteId(
			@PathParam("sequenceNumber") String _sequenceNumber, 
			@PathParam("siteId") Integer _siteId ) {
		USDOT usdot = new USDOT();
		usdot.setSequenceNumber(_sequenceNumber);
		usdot.setSiteId(_siteId);
		usdot = usdotDao.getUSDOTNumberObjectBySequenceAndSiteId(usdot);
		return usdot;
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response insertUSDOT( USDOT usdot ) {
		USDOT checkUSDOT = usdotDao.getUSDOTNumberObject(usdot.getSequenceNumber());
		if( checkUSDOT == null ) {
			usdotDao.insertUSDOT( usdot );
			return Response.status(201).build();
		} else {
			usdotDao.updateUSDOTNumber( usdot );
			return Response.status(202).build();
		}
	}
	
	@DELETE @Path("{sequenceNumber}")
	public void deleteUSDOT( @PathParam("sequenceNumber") String sequenceNumber ) {
		usdotDao.deleteUSDOT(sequenceNumber);
	}

}
