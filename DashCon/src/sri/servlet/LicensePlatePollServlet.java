package sri.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import sri.data.LicensePlate;

import com.sun.grizzly.comet.CometContext;
import com.sun.grizzly.comet.CometEngine;
import com.sun.grizzly.comet.CometEvent;
import com.sun.grizzly.comet.CometHandler;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


public class LicensePlatePollServlet extends HttpServlet {

	private LicensePlate lp;

	private class LicensePlateHandler implements CometHandler<HttpServletResponse> {

		private HttpServletResponse response;

		public void onEvent(CometEvent event) throws IOException {
			if (CometEvent.NOTIFY == event.getType()) {

				String json = "{ " + 
						"\"id\":\"" + lp.getId() + "\"," + 
						"\"timestamp\":\"" + lp.getTimestamp() + "\"," + 
						"\"siteId\":\"" + lp.getSiteId() + "\"," + 
						"\"fileName\":\"" + lp.getFileName() + "\"," + 
						"\"sequenceNumber\":\"" + lp.getSequenceNumber() + "\"," + 
						"\"licensePlateNumber\":\"" + lp.getLicensePlateNumber() + "\"," + 
						"\"state\":\"" + lp.getState() + "\"" + 
					" }";
				response.addHeader("X-JSON", json);

				PrintWriter writer = response.getWriter();
				writer.write(json);
				writer.flush();

				event.getCometContext().resumeCometHandler(this);
			}
		}

		public void onInitialize(CometEvent event) throws IOException {
		}

		public void onInterrupt(CometEvent event) throws IOException {
			String json = "{ " + 
					"\"id\":\"" + lp.getId() + "\"," + 
					"\"timestamp\":\"" + lp.getTimestamp() + "\"," + 
					"\"siteId\":\"" + lp.getSiteId() + "\"," + 
					"\"fileName\":\"" + lp.getFileName() + "\"," + 
					"\"sequenceNumber\":\"" + lp.getSequenceNumber() + "\"," + 
					"\"licensePlateNumber\":\"" + lp.getLicensePlateNumber() + "\"," + 
					"\"state\":\"" + lp.getState() + "\"" + 
				" }";
			response.addHeader("X-JSON", json);

			PrintWriter writer = response.getWriter();
			writer.write(json);
			writer.flush();
		}

		public void onTerminate(CometEvent event) throws IOException {
		}

		public void attach(HttpServletResponse attachment) {
			this.response = attachment;
		}

	}

	private static final long serialVersionUID = 1L;

	private String contextPath = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		lp = new LicensePlate();
		ServletContext context = config.getServletContext();
		contextPath = context.getContextPath() + "/licensePlatePoll";

		CometEngine engine = CometEngine.getEngine();
		CometContext cometContext = engine.register(contextPath);
		cometContext.setExpirationDelay(5 * 30 * 1000);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		LicensePlateHandler handler = new LicensePlateHandler();
		handler.attach(res);

		CometEngine engine = CometEngine.getEngine();
		CometContext context = engine.getCometContext(contextPath);

		context.addCometHandler(handler);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String json = "{ " + 
				"\"id\":\"" + lp.getId() + "\"," + 
				"\"timestamp\":\"" + lp.getTimestamp() + "\"," + 
				"\"siteId\":\"" + lp.getSiteId() + "\"," + 
				"\"fileName\":\"" + lp.getFileName() + "\"," + 
				"\"sequenceNumber\":\"" + lp.getSequenceNumber() + "\"," + 
				"\"licensePlateNumber\":\"" + lp.getLicensePlateNumber() + "\"," + 
				"\"state\":\"" + lp.getState() + "\"" + 
			" }";

		if (req.getParameter("timestamp") != null) {
			lp = new LicensePlate();
			lp.setId(Integer.valueOf(req.getParameter("id")));
			lp.setTimestamp(req.getParameter("timestamp"));
			lp.setSiteId(Integer.valueOf(req.getParameter("siteId")));
			lp.setFileName(req.getParameter("fileName"));
			lp.setSequenceNumber(Integer.valueOf(req.getParameter("sequenceNumber")));
			lp.setLicensePlateNumber(req.getParameter("licensePlateNumber"));
			lp.setState(req.getParameter("state"));
		}

		
		CometEngine engine = CometEngine.getEngine();
		CometContext context = engine.getCometContext(contextPath);
		context.notify(null);

		PrintWriter writer = res.getWriter();
		writer.write("success");
		writer.flush();
	}

	public void postLicensePlate(LicensePlate _licensePlate) {
		String URL = "http://localhost:8080/DashCon/resources/lpr";
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource webResource = client.resource(UriBuilder.fromUri(URL)
				.build());

		ClientResponse response = webResource.type(
				MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class,
				_licensePlate);
		
		System.out.println("Response: " + response.getStatus());
		if( response.getStatus() == 204 ) {
			System.out.println("Success!");
		} else {
			System.out.println(response.getEntity(String.class));
		}
	}
	
}
