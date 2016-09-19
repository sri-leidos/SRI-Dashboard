package sri.ws;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;
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

import sri.dao.TruckFeedDao;
import sri.dao.TruckFeedDaoImpl;
import sri.dao.WeightReportDao;
import sri.dao.WeightReportDaoImpl;
import sri.data.TruckFeed;
import sri.data.TruckFeedWimTimes;
import sri.data.USDOT;
import sri.data.WeightReport;
import sri.util.TimestampHelper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.BodyPartEntity;
import com.sun.jersey.multipart.FormDataParam;
import com.sun.jersey.multipart.MultiPart;


@Path("truck")
public class TruckFeedResource {

	@Context
	protected UriInfo uriInfo;
	@Context
	protected HttpServletRequest req;
	@Context
	protected HttpServletResponse resp;
	
	private TruckFeedDao truckfeedDao = new TruckFeedDaoImpl();
	private WeightReportDao weightReportDao = new WeightReportDaoImpl();
	
	//LIST All TRUCKS
	@GET
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<TruckFeed> listAllTrucks() {
		List<TruckFeed> allTrucks = truckfeedDao.listAllTrucks();
    	return allTrucks;
    }
	
	@GET @Path("{timestamp}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public TruckFeed getTruckFeed(@PathParam("timestamp") Timestamp timestamp) {
		TruckFeed truckfeed = truckfeedDao.getTruckFeed(timestamp);
		return truckfeed;
	}
	
	/*
	 * @author	rothr
	 * @desc	Get a TruckFeed based on a timestamp, sequenceNumber, and siteId.
	 */		
	@GET 
	@Path("{timestamp}/{sequenceNumber}/{siteId}")
	@Produces({MediaType.APPLICATION_JSON})
	public TruckFeed getTruckFeed(
			@PathParam("timestamp") String timestamp, 
			@PathParam("sequenceNumber") String sequenceNumber,
			@PathParam("siteId") Integer siteId) {

		TruckFeed truckfeed = truckfeedDao.getTruckFeed(timestamp, sequenceNumber, siteId);
		return truckfeed;
	}
	
	/*
	 * @desc	Get a TruckFeed based on a sequenceNumber and siteId.
	 */		
	@GET 
	@Path("{sequenceNumber}/{siteId}")
	@Produces({MediaType.APPLICATION_JSON})
	public TruckFeed getTruckFeedBySequenceNumberAndSiteId(
			@PathParam("sequenceNumber") String sequenceNumber,
			@PathParam("siteId") Integer siteId) {

		TruckFeed truckfeed = truckfeedDao.getTruckFeedBySequenceNumberAndSiteId(sequenceNumber, siteId);
		return truckfeed;
	}
	
	@POST @Path("image")
	@Consumes({MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_JSON})
	public Response insertTruckFeed(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			@FormDataParam("siteId") Integer siteId,
			@FormDataParam("licensePlate") String licensePlate,
			@FormDataParam("driversLicense") String driversLicense,
			@FormDataParam("commercialDriversLicense") String commercialDriversLicense,
			@FormDataParam("vin") String vin,
			@FormDataParam("usdotNumber") String usdotNumber,
			@FormDataParam("latitude") Double latitude,
			@FormDataParam("longitude") Double longitude,
			@FormDataParam("sequenceNumber") String sequenceNumber
		) {

		// We also need a calendar object for timestamps:
		Calendar calendar = Calendar.getInstance();
		// For this example we're just using the current date and time:
		Date now = calendar.getTime();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(now.getTime());
		
		TruckFeed truckfeed = new TruckFeed();
		truckfeed.setSiteId(siteId);
		truckfeed.setLicensePlate(licensePlate);
		truckfeed.setDriversLicense(driversLicense);
		truckfeed.setCommercialDriversLicense(commercialDriversLicense);
		truckfeed.setVin(vin);
		truckfeed.setUsdotNumber(usdotNumber);
		truckfeed.setLatitude(latitude);
		truckfeed.setLongitude(longitude);
		truckfeed.setImageUrl("");
		truckfeed.setTimestamp(timestamp.toString());
		truckfeed.setSequenceNumber(sequenceNumber);
		
		TruckFeed truckFeed = truckfeedDao.insertTruckFeed(truckfeed);
		
		String workingFolder = System.getProperty("user.home") + File.separator + "DashCon" + File.separator + "images" + File.separator;
		
//		Windows XP   x86   1.5.0_07
//		Linux   i386   1.4.2_06
//		Mac OS X   ppc   1.4.2_03
//		SunOS   sparc   1.4.2_04
//		FreeBSD   i386   1.4.2-p6
		System.out.println("workingFolder " + workingFolder );
		File files = new File(workingFolder );
		
		if (files.mkdirs()) {
			System.out.println("Multiple directories are created!" + workingFolder);
		} else {
			System.out.println("Failed to create multiple directories!");
		}
		
		String image = workingFolder + File.separator + truckfeed.getId() + ".png";
		try {
			OutputStream out = new FileOutputStream(new File(image) );
			int read = 0;
			byte[] bytes = new byte[1024];
 
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String output = "File uploaded to : " + workingFolder;
		
		return Response.status(200).entity(output).build();
	}
	

	@POST 
	@Path("saveImage")
	@Consumes({MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_JSON})
	public void saveImage(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("imageId") Integer imageId
			) {
		
		System.out.println("CONTEXT PATH SAVE IMAGE->" + req.getSession().getServletContext().getRealPath( String.valueOf(File.separatorChar)  ) );
		System.out.println("IMAGEID->" + imageId );
		
		String workingFolder = req.getSession().getServletContext().getRealPath( String.valueOf(File.separatorChar)  );
		workingFolder += "dashboard" + File.separatorChar + "images" + File.separatorChar + "truck" + File.separatorChar;
		
		String image = workingFolder + File.separatorChar + imageId + ".jpg";
		File files = new File(workingFolder);

		System.out.println("IMAGE->" + image);
		
		if (files.mkdirs()) {
			System.out.println("Multiple directories are created!" + workingFolder);
		} else {
			System.out.println("directory may already exist..." + workingFolder );
		}
		System.out.println("SAVE IMAGE->" + workingFolder );

		try {
			OutputStream out = new FileOutputStream(new File(image) );
			int read = 0;
			byte[] bytes = new byte[1024];
 
			System.out.println("IMAGE SIZE SAVING->" + uploadedInputStream.available() );
			
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@POST @Path("approachEnter")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response postApproachEnter(TruckFeed _truckFeed) {

		TruckFeed truckFeed = truckfeedDao.insertTruckFeed(_truckFeed);
		System.out.println("Truck Feed ID: "+truckFeed.getId());
		
		return Response.status(200).entity(truckFeed).build();

	}
	
	@POST @Path("wimEnter")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response postWimEnter(TruckFeed truckFeed) {

		truckfeedDao.updateTruckFeedWimEntered(truckFeed);
		return Response.status(200).entity(truckFeed).build();

	}
	
	@POST @Path("wimLeave")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response postWimLeave(TruckFeed truckFeed) {

		TruckFeedWimTimes tfWimTimes = truckfeedDao.getTruckFeedWimTimes(truckFeed.getId());
		tfWimTimes.setWimLeft(TimestampHelper.getCurrentTimestamp().toString());
		tfWimTimes.setSiteId(truckFeed.getSiteId());
		
		//TODO:  Shouldn't need this.
		tfWimTimes.setWimEntered(tfWimTimes.getWimEntered());

		WeightReport report = new WeightReport();
		report = weightReportDao.getWeight(tfWimTimes);
		
		if (report != null) {
			String sequenceNumber = report.getSequenceNumber().toString();
			truckFeed.setSequenceNumber(sequenceNumber);
			truckFeed.setStatus(report.getStatus());
			// insert our usdot number associated with our phone app
			USDOT usdot = new USDOT();
			usdot.setSequenceNumber( truckFeed.getSequenceNumber() );
			usdot.setUsdotNumber( truckFeed.getUsdotNumber() );
			usdot.setManualEntered(0);
			usdot.setSiteId( truckFeed.getSiteId() );
			// this would be the username if it was manually entered
			// but since this is from an SRI Android app it's not applicable
			usdot.setUserName("SRI Mobile Application");
			USDOTResource usdotResource = new USDOTResource();
			usdotResource.insertUSDOT(usdot);
		} 

		truckfeedDao.updateTruckFeedWimLeft(truckFeed);
		invokeCometServlet(truckFeed);

		return Response.status(200).entity(report).build();
	}
	
	public Response invokeCometServlet(TruckFeed report) {
		String URL = "http://localhost:8080/DashCon";

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource webResource = client.resource(UriBuilder.fromUri(URL)
				.build());

        ClientResponse response = 
        	webResource.path("/truckFeedPoll")
				.queryParam("id", String.valueOf(report.getId()))
				.queryParam("siteId", String.valueOf(report.getSiteId()))
		        .queryParam("timestamp", report.getTimestamp())
		        .queryParam("licensePlate", String.valueOf(report.getLicensePlate()))
		        .queryParam("commercialDriversLicense", String.valueOf(report.getCommercialDriversLicense()))
		        .queryParam("vin", report.getVin())
		        .queryParam("usdotNumber", String.valueOf(report.getUsdotNumber()))
		        .queryParam("latitude", String.valueOf(report.getLatitude()))
		        .queryParam("longitude", String.valueOf(report.getLongitude()))
		        .queryParam("sequenceNumber", String.valueOf(report.getSequenceNumber()))
				.post(ClientResponse.class, report);
        
		System.out.println("Response " + response.getEntity(String.class));
		System.out.println("Response " + response.toString());
        
        return Response.status(200).entity(report.toString()).build();
	}

	
	@POST @Path("feed")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response postTruckFeed(TruckFeed truckFeed) {
		truckfeedDao.insertTruckFeed(truckFeed);
		try{
			WeightReport report = 
				weightReportDao.getWimWeightForStationAndTime(
					truckFeed.getTimestamp(), truckFeed.getSiteId());
		}catch(Exception e){
			e.printStackTrace();
		}
		return Response.status(200).entity(truckFeed).build();
	}
	
	@POST @Path("multi")
	@Consumes("multipart/mixed")
	public Response postTruckFeed2(MultiPart multiPart) {
		
	    // First part contains a TruckFeed object
	    TruckFeed truckFeed = multiPart.getBodyParts().get(0).getEntityAs(TruckFeed.class);
	    System.out.println("id : " + truckFeed.getId());
	    System.out.println("license plate : " + truckFeed.getLicensePlate());
	    System.out.println("driver's license : " + truckFeed.getDriversLicense());
	    System.out.println("commercial driver's license : " + truckFeed.getCommercialDriversLicense());
	    System.out.println("vin : " + truckFeed.getVin());
	    System.out.println("usdot number : " + truckFeed.getUsdotNumber());
	    System.out.println("latitude : " + truckFeed.getLatitude());
	    System.out.println("longitude : " + truckFeed.getLongitude());
	    System.out.println("sequenct number: " + truckFeed.getSequenceNumber());

		// We also need a calendar object for timestamps:
		Calendar calendar = Calendar.getInstance();
		// For this example we're just using the current date and time:
		Date now = calendar.getTime();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(now.getTime());
		truckFeed.setTimestamp(timestamp.toString());
		
	    // Insert our truck feed into our database:
		truckfeedDao.insertTruckFeed(truckFeed);
		
		// get the second part which is the truck image
	    BodyPartEntity bpe = (BodyPartEntity) multiPart.getBodyParts().get(1).getEntity();
	    boolean isProcessed = false;
	    String message = null;
	    try {
	      InputStream source = bpe.getInputStream();
	      BufferedImage bi = ImageIO.read(source);
	 
	      File file = new File(".\\received\\" + truckFeed.getId() + ".jpg");
	 
	      //storing the image to file system.
	      if (file.isDirectory()) {
	        ImageIO.write(bi, "jpg", file);
	      } else {
	        file.mkdirs();
	        ImageIO.write(bi, "jpg", file);
	      }
	      isProcessed = true;
	 
	    } catch (Exception e) {
	      message = e.getMessage();
	    }
	    if (isProcessed) {
	      return Response.status(Response.Status.ACCEPTED).entity("Attachements processed successfully.").type(MediaType.TEXT_PLAIN).build();
	    }
	    
	    return Response.status(Response.Status.BAD_REQUEST).entity("Failed to process attachments. Reason : " + message).type(MediaType.TEXT_PLAIN).build();
	}
	
	public static boolean isWindows() {
		String OS = System.getProperty("os.name");

		return (OS.indexOf("win") >= 0);
 
	}
 
	public static boolean isMac() {
		String OS = System.getProperty("os.name");
 
		return (OS.indexOf("mac") >= 0);
 
	}
 
	public static boolean isUnix() {
		String OS = System.getProperty("os.name");
 
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
 
	}
 
	public static boolean isSolaris() {
		String OS = System.getProperty("os.name");
 
		return (OS.indexOf("sunos") >= 0);
 
	}
}
