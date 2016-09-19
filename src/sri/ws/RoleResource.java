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
import javax.ws.rs.core.UriInfo;

import sri.dao.RoleDao;
import sri.dao.RoleDaoImpl;
import sri.data.Role;

@Path("role")
public class RoleResource {
	
	@Context
	protected UriInfo uriInfo;
	@Context
	protected HttpServletRequest req;
	@Context
	protected HttpServletResponse resp;
	
	public RoleDao roleDao;
		
		@GET
		@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    public List<Role> getRoles() {
			roleDao = new RoleDaoImpl();
	    	List<Role> roles = roleDao.getAllRoles();
	    	return roles;
	    }
		
		@GET @Path("{roleId}")
		@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		public Role getRoleById(@PathParam("roleId") String roleId) {
			roleDao = new RoleDaoImpl();
			Role role = roleDao.getRoleById(roleId);
			return role;
		}
		

}
