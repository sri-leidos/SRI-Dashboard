package sri.ws;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import sri.data.TruckFeed;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@Path("mobile")
public class MobileResource {

	@Context
	protected UriInfo uriInfo;
	@Context
	protected HttpServletRequest req;
	@Context
	protected HttpServletResponse resp;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response insertNewWeight(TruckFeed report) {
		String URL = "http://localhost:8080/DashCon";

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource webResource = client.resource(UriBuilder.fromUri(URL)
				.build());

		webResource =
        	webResource
        		.path("/truckFeedPoll")
		        .queryParam("siteId", String.valueOf(report.getSiteId()))
		        .queryParam("timestamp", report.getTimestamp())
		        .queryParam("licensePlate", report.getLicensePlate())
		        .queryParam("vin", String.valueOf(report.getVin()))
		        .queryParam("usdotNumber", String.valueOf(report.getUsdotNumber()))
		        .queryParam("latitude", String.valueOf(report.getLatitude()))
		        .queryParam("longitude", String.valueOf(report.getLongitude()));
		
		// these could be null so don't add them if they are
		if (report.getDriversLicense() != null) {
			webResource = 
				webResource.queryParam("driversLicense", report.getDriversLicense());
		}
		
		if (report.getDriversLicense() != null) {
			webResource = 
				webResource.queryParam("commercialDriversLicense", report.getCommercialDriversLicense());
		}

		if (report.getSequenceNumber() != null) {
			webResource = webResource.queryParam("sequenceNumber", report.getSequenceNumber());
		}
		
		ClientResponse response = webResource.post(ClientResponse.class, report);

		System.out.println("Response " + response.getEntity(String.class));
		System.out.println("Response " + response.toString());
        
        return Response.status(200).entity(report.toString()).build();
	}
}
