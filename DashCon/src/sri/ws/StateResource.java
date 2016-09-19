package sri.ws;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import sri.dao.StateDao;
import sri.dao.StateDaoImpl;
import sri.data.State;

@Path("state")
public class StateResource {
	
	@Context
	protected UriInfo uriInfo;
	@Context
	protected HttpServletRequest req;
	@Context
	protected HttpServletResponse resp;
	
	private StateDao stateDao;
	
	//Web Service for Getting all States in the DB
	@GET
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<State> getStates() {
		stateDao = new StateDaoImpl();
    	List<State> states =stateDao.getAllState();
    	return states;
    }
	
	@Path("{stateId}")
	@GET
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public State getStateById(@PathParam("stateId") String stateId) {
		
		stateDao = new StateDaoImpl();
		State state = stateDao.getStateById(stateId);
		return state;
		
	}


}
