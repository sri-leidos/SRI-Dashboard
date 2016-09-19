package sri.ws;

import java.io.IOException;

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

import sri.dao.SaferDao;
import sri.dao.SaferDaoImpl;
import sri.data.safer.SaferUSDOTData;
import sri.parser.SaferParser;

@Path("/safer")
public class SaferResource {

	@Context
	protected UriInfo uriInfo;
	@Context
	protected HttpServletRequest req;
	@Context
	protected HttpServletResponse resp;
	
	private SaferDao saferDao = new SaferDaoImpl();

	@GET
	@Path("/{usDotNumber}")
	@Produces({MediaType.APPLICATION_JSON})
	public SaferUSDOTData getSaferData(@PathParam("usDotNumber") String usDotNumber) {
		SaferUSDOTData safer = saferDao.getSaferData(usDotNumber);
		
		if (safer.getCompanyData() != null) {
			return safer;
		}

		SaferParser saferParser = new SaferParser();
		try {
			safer = saferParser.getSaferDataForUSDOT(usDotNumber);
			if (safer != null) {
				saferDao.insertSaferData(safer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return safer;
	}

	@GET
	@Path("/loc/{usDotNumber}")
	@Produces({MediaType.APPLICATION_JSON})
	public SaferUSDOTData getSaferDataLocal(@PathParam("usDotNumber") String usDotNumber) {
		SaferUSDOTData safer = saferDao.getSaferData(usDotNumber);
		return safer;
	}

	@POST
	@Path("/loc/{usDotNumber}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response postSaferDataLocal(SaferUSDOTData saferUSODTData) {
		saferDao.insertSaferData(saferUSODTData);
		return Response.status(200).entity(saferUSODTData).build();
	}
}
