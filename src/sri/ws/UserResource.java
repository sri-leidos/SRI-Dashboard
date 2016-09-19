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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import sri.dao.UserDao;
import sri.dao.UserDaoImpl;
import sri.data.User;
import sri.data.UserGroup;


@Path("user")
public class UserResource {

	@Context
	protected UriInfo uriInfo;
	@Context
	protected HttpServletRequest req;
	@Context
	protected HttpServletResponse resp;
	
	private UserDao userDao = new UserDaoImpl();
	
	//GET All USERS
	@GET @Path("admin")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<User> getUsers() {
		List<User> users = userDao.getAllUser();
    	return users;
    }	
	
	//GET All USERS ASSOCIATED WITH THE ADMIN'S SITE ID
	@GET @Path("admin/site")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<User> getUsersForAdmin() {
		// get our user admin from the session
		String userId = req.getUserPrincipal().getName();
		UserDao userDao = new UserDaoImpl();
		User user = userDao.getUserByUserId(userId);
		// get all user associated with our admin's site id
		List<User> users = userDao.getUsersBySiteId( user.getSiteId() );
    	return users;
    }
	
	//GET USER BY userId
	@GET @Path("admin/{userId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User getUserById(@PathParam("userId") String userId) {
		User user = userDao.getUserByUserId(userId);
		return user;
	}
	
	//CREATE NEW USER
	@POST @Path("admin")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User createUser(User user) {
		userDao.insertNewUser(user);
		
		UserGroup userGroup = new UserGroup();
		userGroup.setUserId(user.getUserId());
		userGroup.setGroupId(user.getUserGroup().getGroupId());
		
		userDao.insertUserGroup(userGroup);
		
		return user;
	}
	
	//UPDATE USER
	@PUT @Path("admin")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void updateUser(User user) {
		userDao.updateUser(user);
	}
	
	//DELETE USER
	@DELETE @Path("admin/{userId}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void deleteUser(@PathParam("userId") String userId) {
		userDao.deleteUserGroupByUserId(userId);
		userDao.deleteUserByUserId(userId);
	}
	
	//VALIDATE USER BASED ON DATABASE
	@POST @Path("register/validate/user")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	//@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User validateUserId(User user) {		
		
		User userCheck = null;
		//SEARCH USER DETAILS BY request's user.userId
		userCheck = userDao.getUserByUserId(user.getUserId());
		
		if (userCheck != null) {
			//Set unavailable to properties that are taken
			user.setUserId("unavailable");			
		}
		
		userCheck = null;
		userCheck = userDao.getUserByEmail(user.getEmail());
		
		if (userCheck != null) {
			user.setEmail("unavailable");
		}
		
		return user;
		
	}
	//---
	//--
	//-
	
	
	
	@Path("register")
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_XML})
	public String createNewUser(User user) {
		
		userDao = new UserDaoImpl();
		
		String response = "You have successfully registered! You can now <a href=\"#\">Login</a>" ;
		User userCheck;
		
		userCheck = userDao.getUserByUserId(user.getUserId());
		if (userCheck != null) {
			response = "Registration failed: Username is already being used.";
			return response;
		}
		
		userCheck = userDao.getUserByEmail(user.getEmail());
		if (userCheck != null) {
			response = "Registration failed: Email is already being used.";
			return response;
		}
		
		userDao.insertNewUser(user);
//		userDao.insertNewUserRole(userId);
		
		return null;
		
	}

	@Path("email")
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_XML})
	public User getUserByEmail(@PathParam("email") String email) {
		
		userDao = new UserDaoImpl();
		User user = null;
		
		user = userDao.getUserByEmail(email);
		
		return user;
		
	}

	
	@Path("state")
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_XML})
	public List<User> getUsersByStateId(@PathParam("stateId") String stateId) {
		
		userDao = new UserDaoImpl();
		List<User> users = userDao.getUsersByStateId(stateId);
		return users;
		
	}
	
	@Path("edit")
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void editUser(User user) {
				
		userDao = new UserDaoImpl();

		User editedUser = userDao.getUserByUserId(user.getUserId());
		
		editedUser.setFirstName(user.getFirstName());
		editedUser.setLastName(user.getLastName());
		
		if (user.getUserPassword() != null || user.getUserPassword() != "") {
			editedUser.setUserPassword(user.getUserPassword());
		}
			
		userDao.updateUser(editedUser);
			
//			if(userDao.getUserRoleByUserId(user.getUserId()).toString() != "Admin")
//			{
//				userDao.updateRole(user);
//				userDao.updateSuperUser(user);
//			}		
	}
	
	// GET USER BY userId
	@GET @Path("profile")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User getUserPrincipal() {
		String userId = req.getUserPrincipal().getName();
		User user = userDao.getUserByUserId(userId);
		return user;
	}
	
	// UPDATE USER
	@PUT @Path("profile")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateUserProfile( User user) {
		String userId = req.getUserPrincipal().getName();
		if( user.getUserId().equals(userId) ) {
			userDao.updateUser(user);
		} else {
			return Response.status(500).build();
		}
		return Response.status(200).build();
	}	
	// UPDATE USER
	@PUT @Path("profile/email")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateProfileEmail( String email ) {
		User user = new User();
		user.setUserId( req.getUserPrincipal().getName() );
		user.setEmail( email );
		userDao.updateUserEmail( user );

		return Response.status(200).build();
	}
	
	
}
