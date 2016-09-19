package sri.ws;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import sri.dao.SiteDao;
import sri.dao.SiteDaoImpl;
import sri.dao.SiteTypeDao;
import sri.dao.SiteTypeDaoImpl;
import sri.dao.UserDao;
import sri.dao.UserDaoImpl;
import sri.data.Site;
import sri.data.SiteType;
import sri.data.User;

@Path("site")
public class SiteResource {
	
	@Context
	protected UriInfo uriInfo;
	@Context
	protected HttpServletRequest req;
	@Context
	protected HttpServletResponse resp;

	public SiteDao siteDao = new SiteDaoImpl();
	public SiteTypeDao siteTypeDao = new SiteTypeDaoImpl();
	
	@GET @Path("admin")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Site> getAllSites() {
		
    	List<Site> sites = siteDao.getAllSites();
    	return sites;
    }
	
	// Get site by id
	@GET @Path("admin/{siteId}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Site getSiteById(@PathParam("siteId") Integer siteId) {
				
		return siteDao.getSiteById(siteId);
	}
	
	// List sites by state
	@GET @Path("admin/state/{stateId}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Site> getAllSitesByStateId(@PathParam("stateId") String stateId) {
		
		List<Site> sites = siteDao.getAllSitesByStateId(stateId);
		return sites;
	}
	
	// List sites by site type
	@GET @Path("admin/type/{siteId}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Site> getAllSitesBySiteType(@PathParam("siteId") Integer siteType) {
		
		List<Site> sites = siteDao.getAllSitesBySiteType(siteType);
		return sites;
	}
	
	// Create New Site
	@POST @Path("admin")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void insertNewSite(String site) {
		 siteDao.insertNewSite(site);
	}
	
	// Update Site
	@PUT @Path("admin")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void updateSite(Site site) {
		
		if ( siteDao.getSiteById(site.getId()) != null) {
			siteDao.updateSite(site);
		}
	}
	
	// Delete Site
	@DELETE @Path("admin/{siteId}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void deleteSite(@PathParam("siteId") Integer siteId) {
		
		Site site = siteDao.getSiteById(siteId);	
		if(site != null) {
			siteDao.deleteSite(siteId);
		}
	}
	
	// Resources for the types of Sites
	@GET @Path("admin/types")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<SiteType> getAllSiteTypes() {
    	return siteTypeDao.getAllSiteType();
    }
	
	// Profile: Get Site by ID
	@GET
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Site getSiteByIdProfile() {
		String userId = req.getUserPrincipal().getName();
		UserDao userDao = new UserDaoImpl();
		User user = userDao.getUserByUserId(userId);
		
		return siteDao.getSiteById(Integer.parseInt( user.getSiteId() ));
	}
		
}
