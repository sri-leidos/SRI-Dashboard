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

import sri.data.TruckFeed;
import sri.data.WeightReport;

import com.sun.grizzly.comet.CometContext;
import com.sun.grizzly.comet.CometEngine;
import com.sun.grizzly.comet.CometEvent;
import com.sun.grizzly.comet.CometHandler;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


public class TruckFeedPollServlet extends HttpServlet {

	private TruckFeed tf;

	private class TruckFeedHandler implements CometHandler<HttpServletResponse> {

		private HttpServletResponse response;

		public void onEvent(CometEvent event) throws IOException {
			if (CometEvent.NOTIFY == event.getType()) {

				String json = "{ " + 
						"\"id\":\"" + tf.getId() + "\"," + 
						"\"siteId\":\"" + tf.getSiteId() + "\"," + 
						"\"timestamp\":\"" + tf.getTimestamp() + "\"," + 
						"\"licensePlate\":\"" + tf.getLicensePlate() + "\"," + 
						"\"driversLicense\":\"" + tf.getDriversLicense() + "\"," + 
						"\"commercialDriversLicense\":\"" + tf.getCommercialDriversLicense()+ "\"," + 
						"\"vin\":\"" + tf.getVin()+ "\"," + 
						"\"usdotNumber\":\"" + tf.getUsdotNumber()+ "\"," + 
						"\"latitude\":\"" + tf.getLatitude() + "\"," + 
						"\"longitude\":\"" + tf.getLongitude() + "\"," + 
						"\"sequenceNumber\":\"" + tf.getSequenceNumber() + "\"" + 
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
					"\"id\":\"" + tf.getId() + "\"," + 
					"\"siteId\":\"" + tf.getSiteId() + "\"," + 
					"\"timestamp\":\"" + tf.getTimestamp() + "\"," + 
					"\"licensePlate\":\"" + tf.getLicensePlate() + "\"," + 
					"\"driversLicense\":\"" + tf.getDriversLicense() + "\"," + 
					"\"commercialDriversLicense\":\"" + tf.getCommercialDriversLicense()+ "\"," + 
					"\"vin\":\"" + tf.getVin()+ "\"," + 
					"\"usdotNumber\":\"" + tf.getUsdotNumber()+ "\"," + 
					"\"latitude\":\"" + tf.getLatitude() + "\"," + 
					"\"longitude\":\"" + tf.getLongitude() + "\"," + 
					"\"sequenceNumber\":\"" + tf.getSequenceNumber() + "\"" + 
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

		tf = new TruckFeed();
		ServletContext context = config.getServletContext();
		contextPath = context.getContextPath() + "/truckFeedPoll";

		CometEngine engine = CometEngine.getEngine();
		CometContext cometContext = engine.register(contextPath);
		cometContext.setExpirationDelay(5 * 30 * 1000);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		TruckFeedHandler handler = new TruckFeedHandler();
		handler.attach(res);

		CometEngine engine = CometEngine.getEngine();
		CometContext context = engine.getCometContext(contextPath);

		context.addCometHandler(handler);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String json = "{ " + 
				"\"id\":\"" + tf.getId() + "\"," + 
				"\"siteId\":\"" + tf.getSiteId() + "\"," + 
				"\"timestamp\":\"" + tf.getTimestamp() + "\"," + 
				"\"licensePlate\":\"" + tf.getLicensePlate() + "\"," + 
				"\"driversLicense\":\"" + tf.getDriversLicense() + "\"," + 
				"\"commercialDriversLicense\":\"" + tf.getCommercialDriversLicense()+ "\"," + 
				"\"vin\":\"" + tf.getVin()+ "\"," + 
				"\"usdotNumber\":\"" + tf.getUsdotNumber()+ "\"," + 
				"\"latitude\":\"" + tf.getLatitude() + "\"," + 
				"\"longitude\":\"" + tf.getLongitude() + "\"," + 
				"\"sequenceNumber\":\"" + tf.getSequenceNumber() + "\"" + 
			" }";

		if (req.getParameter("timestamp") != null) {
			tf = new TruckFeed();
			tf.setId(Integer.valueOf(req.getParameter("id")));
			tf.setSiteId(Integer.valueOf(req.getParameter("siteId")));
			tf.setTimestamp(req.getParameter("timestamp"));
			tf.setLicensePlate(req.getParameter("licensePlate"));
			tf.setDriversLicense(req.getParameter("driversLicense"));
			tf.setCommercialDriversLicense(req.getParameter("commercialDriversLicense"));
			tf.setVin(req.getParameter("vin"));
			tf.setUsdotNumber(req.getParameter("usdotNumber"));
			tf.setLatitude(Double.valueOf(req.getParameter("latitude")));
			tf.setLongitude(Double.valueOf(req.getParameter("longitude")));
			tf.setSequenceNumber(req.getParameter("sequenceNumber"));
		}

//		postTruckFeed(tf);
		
		CometEngine engine = CometEngine.getEngine();
		CometContext context = engine.getCometContext(contextPath);
		context.notify(null);

		PrintWriter writer = res.getWriter();
		writer.write("success");
		writer.flush();
	}

	public void postTruckFeed(TruckFeed truckFeed) {
		String URL = "http://localhost:8080/DashCon/resources/truck/feed";
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource webResource = client.resource(UriBuilder.fromUri(URL)
				.build());

		ClientResponse response = webResource.type(
				MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class,
				truckFeed);
		
		System.out.println("Response: " + response.getStatus());
		if( response.getStatus() == 204 ) {
			System.out.println("Success!");
		} else {
			System.out.println(response.getEntity(String.class));
		}
	}
	
}
