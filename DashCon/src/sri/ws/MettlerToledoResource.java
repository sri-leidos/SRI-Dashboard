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

import sri.data.WeightReport;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@Path("mettlerToledo")
public class MettlerToledoResource {

	@Context
	protected UriInfo uriInfo;
	@Context
	protected HttpServletRequest req;
	@Context
	protected HttpServletResponse resp;
	
	@POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response insertNewWeight(WeightReport report) {
		String URL = "http://localhost:8080/DashCon";

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource webResource = client.resource(UriBuilder.fromUri(URL)
				.build());

        ClientResponse response = 
        	webResource.path("/mettPoll")
				.queryParam("siteId", String.valueOf(report.getSiteId()))
		        .queryParam("timestamp", report.getTimestamp())
		        .queryParam("axleCount", String.valueOf(report.getAxleCount()))
		        .queryParam("grossWeight", String.valueOf(report.getGrossWeight()))
		        .queryParam("massUnit", report.getMassUnit())
		        .queryParam("status", String.valueOf(report.getStatus()))
		        .queryParam("reason", String.valueOf(report.getReason()))
		        .queryParam("scaleType", String.valueOf(report.getScaleType()))
		        .queryParam("sequenceNumber", String.valueOf(report.getSequenceNumber()))
				.post(ClientResponse.class, report);
        
		System.out.println("Response " + response.getEntity(String.class));
		System.out.println("Response " + response.toString());
        
        return Response.status(200).entity(report.toString()).build();
	}
}
