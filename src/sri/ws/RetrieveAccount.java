package sri.ws;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import sri.dao.UserDao;
import sri.dao.UserDaoImpl;
import sri.data.User;

@Path("retrieve")
public class RetrieveAccount {
	
	@Context
	protected HttpServletRequest req;
	@Context
	protected HttpServletResponse resp;
	
	private UserDao userdao = new UserDaoImpl();
	
	@POST @Path("username")
	public Response retrieveUsername(String email) {
		User user = userdao.getUserByEmail(email);
		if( user != null ) {
			if( sendEmail("Hello", user) ) {
				return Response.status(200).entity(user).build();
			} else {
				return Response.status(404).build();
			}
		} else {
			return Response.status(204).build();
		}
	}
	
	@POST @Path("password")
	public Response retrievePassword(String email) {
		User user = userdao.getUserByEmail(email);
		if( user != null ) {
			return Response.status(200).entity(user).build();
		} else {
			return Response.status(204).build();
		}
	}
	
	private Boolean sendEmail( String message, User user ) {
		Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.stattls.enable", "true");
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "888");
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		Session session = Session.getDefaultInstance(props, null);
		session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("<USERNAME>", "<PASSWORD>");
            }
        });
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("sri@leidos.com", "SRI Admin"));
			msg.addRecipient(Message.RecipientType.TO,
			                 new InternetAddress( user.getEmail(), user.getFirstName()));
			msg.setSubject("Your Example.com account has been activated" + user.getUserId());
			msg.setText( message );
			Transport.send( msg );
			return true;
		} catch ( AddressException e ) {
			e.printStackTrace();
			return false;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
        } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
	}

}
