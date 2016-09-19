package sri.ws;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import sri.dao.UserDao;
import sri.dao.UserDaoImpl;
import sri.data.User;
import sri.util.ShaHasher;


@Path("profile")
public class ProfileResource {

	@Context
	protected UriInfo uriInfo;
	@Context
	protected HttpServletRequest req;
	@Context
	protected HttpServletResponse resp;
	
	private UserDao userDao = new UserDaoImpl();
	
	
	// GET USER BY userId
	@GET
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User getUserPrincipal() {
		String userId = req.getUserPrincipal().getName();
		User user = userDao.getUserByUserId(userId);
		return user;
	}
	
	// UPDATE USER PROFILE
	@PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateUserProfile( User user) {
		String userId = req.getUserPrincipal().getName();
		if( user.getUserId().equals(userId) ) {
			userDao.updateUser(user);
		} else {
			return Response.status(405).build();
		}
		return Response.status(200).build();
	}	
	// UPDATE USER EMAIL
	@PUT @Path("email")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateProfileEmail( String email ) {
		User user = new User();
		user.setUserId( req.getUserPrincipal().getName() );
		user.setEmail( email );
		userDao.updateUserEmail( user );

		return Response.status(200).build();
	}
	// UPDATE USER EMAIL
	@PUT @Path("password")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateProfilePassword( User user ) {
		// get userId from our logged-in user
		String userId = req.getUserPrincipal().getName();
		// check if user object submitted matches our logged-in user
		if( user.getUserId().equals(userId) ) {
			// get user's current password
			String password = new String(userDao.getUserPasswordbyUserId(userId));
			// get the string hash of the submitted password since passwords
			// in the database are hashed
			String submittedPassword = new String(ShaHasher.hash( user.getUserPassword() ));
			// compare current passwords to update new password
			if( password.equals( submittedPassword ) ) {
				// hash our new password for security then set
				// it to our user object and save in the database
				String newPassword = ShaHasher.hash(user.getNewPassword());
				user.setNewPassword(newPassword);
				userDao.updatePassword(user);
				return Response.status(200).build();
			}
		}
		return Response.status(405).build();
	}
}
