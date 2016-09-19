package sri.ws;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.multipart.FormDataParam;

import sri.dao.LicensePlateDao;
import sri.dao.LicensePlateDaoImpl;
import sri.dao.WeightReportDao;
import sri.dao.WeightReportDaoImpl;
import sri.data.LicensePlate;
import sri.data.TruckFeed;
import sri.data.WeightReport;


@Path("lpr")
public class LicensePlateResource {

	@Context
	protected UriInfo uriInfo;
	@Context
	protected HttpServletRequest req;
	@Context
	protected HttpServletResponse resp;
	
	private LicensePlateDao lpd = new LicensePlateDaoImpl();
	private WeightReportDao wrd = new WeightReportDaoImpl();

	//List 50 License Plates
	@GET
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<LicensePlate> listFiftyLicensePlaest() {
		
		List<LicensePlate> fiftyPlates = lpd.getFiftyLicensePlates();
    	return fiftyPlates;
    }
	
	// Site License Plates
	@GET
	@Path("{siteId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<LicensePlate> listAllSiteLicensePlates(
    		@PathParam("siteId") Integer siteId) {
		
		List<LicensePlate> sitePlates = lpd.getAllSiteLicensePlates(siteId);
    	return sitePlates;
    }
	
	// Site and Sequence License Plate
	@GET
	@Path("{siteId}/{sequenceNumber}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public LicensePlate selectLprBySequenceNumberAndSiteId(
    		@PathParam("siteId") Integer siteId,
    		@PathParam("sequenceNumber") Integer sequenceNumber) {
		
		LicensePlate licensePlate = new LicensePlate();
		licensePlate.setSiteId(siteId);
		licensePlate.setSequenceNumber(sequenceNumber);
		
		LicensePlate lp = lpd.getLprBySequenceNumberAndSiteId(licensePlate);
    	return lp;
    }

	// New License Plate
	@POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response postNewLicensePlate(LicensePlate _licensePlate) {

		WeightReport weightReport = wrd.getLatestWeightReport(_licensePlate);
		_licensePlate.setSequenceNumber(weightReport.getSequenceNumber());
		
		LicensePlate licensePlate = lpd.insertLicensePlate(_licensePlate);
		invokeCometServlet(licensePlate);
		return Response.status(200).entity(licensePlate).build();
    }
	
	// CometServlet
	public Response invokeCometServlet(LicensePlate report) {
		
		String URL = "http://localhost:8080/DashCon";

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource webResource = client.resource(UriBuilder.fromUri(URL)
				.build());

        ClientResponse response = 
        	webResource.path("/licensePlatePoll")
				.queryParam("id", String.valueOf(report.getId()))
		        .queryParam("timestamp", report.getTimestamp())
				.queryParam("siteId", String.valueOf(report.getSiteId()))
		        .queryParam("fileName", report.getFileName())
		        .queryParam("sequenceNumber", String.valueOf(report.getSequenceNumber()))
		        .queryParam("licensePlateNumber", report.getLicensePlateNumber())
		        .queryParam("state", report.getState())
				.post(ClientResponse.class, report);
        
		System.out.println("Response " + response.getEntity(String.class));
		System.out.println("Response " + response.toString());
        
        return Response.status(200).entity(report.toString()).build();
	}

	@POST 
	@Path("saveImage")
	@Consumes({MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_JSON})
	public void saveImage(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("imageId") Integer imageId
			) {
		
		System.out.println("saveLicensePlate > CONTEXT PATH SAVE IMAGE->" + req.getSession().getServletContext().getRealPath( String.valueOf(File.separatorChar)  ) );
		System.out.println("saveLicensePlate > IMAGEID->" + imageId );
		
		String workingFolder = req.getSession().getServletContext().getRealPath( String.valueOf(File.separatorChar)  );
		workingFolder += "dashboard" + File.separatorChar + "images" + File.separatorChar + "lpr" + File.separatorChar;
		
		String image = workingFolder + File.separatorChar + imageId + ".jpg";
		File files = new File(workingFolder);

		System.out.println("IMAGE->" + image);
		
		if (files.mkdirs()) {
			System.out.println("saveLicensePlate > Multiple directories are created!" + workingFolder);
		} else {
			System.out.println("saveLicensePlate > directory may already exist..." + workingFolder );
		}
		System.out.println("saveLicensePlate > SAVE IMAGE->" + workingFolder );

		try {
			OutputStream out = new FileOutputStream(new File(image) );
			int read = 0;
			byte[] bytes = new byte[1024];
 
			System.out.println("saveLicensePlate > IMAGE SIZE SAVING->" + uploadedInputStream.available() );
			
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}