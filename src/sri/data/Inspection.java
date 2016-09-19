package sri.data;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import sri.util.TimeAdapter;


/**
 * The persistent class for the INSPMAIN database table.
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Inspection extends SriPojo {
	
	private String rptnum;
	private String accident;
	private String alcsubchk;
	private String aspen1;
	private String aspen2;
	private String aspenmatch;
	private String aspenversionnum;
	private String axles;
	private String begfundedinspection;
	private String begfundedtype;
	private String bipdverified;
	private String cargo;
	private String cargotankspecnum;
	private String carrcity;
	private String carrcolonia;
	private String carrcountry;
	private String carrdbaname;
	private String carrfax;
	private String carrname;
	private String carrphone;
	private String carrstate;
	private String carrstreet;
	private String carrzipcode;
	private Date codriverdob;
	private String codriverfname;
	private String codriverlicnum;
	private String codriverlicstate;
	private String codriverlname;
	private String codrivermi;
	private String coinspectorcode;
	private String coinspectorname;
	private String destinationCity;
	private String destinationState;
	private Date driverdob;
	private String driverfname;
	private String driverlicnum;
	private String driverlicstate;
	private String driverlname;
	private String drivermi;
	private String driveroos;
	private byte[] driversignature;
	private String drugarrest;
	private String drugsearch;
	private int duration;
	private String escreen;
	private String facilitytype;
	private String fipscountycode;
	private int gcwr;
	private String highway;
	private String hminsptype;
	private String hmplacard;
	private String iccnum;
	private String ieppretripinspection;
	private String ieppretripspace;
	private String inspcountry;
	private String inspcounty;
	private String inspectorcode;
	private String inspectorname;
	private Date inspenddate;
	@XmlJavaTypeAdapter(value=TimeAdapter.class)
	private Time inspendtime;
	private String insplocationcode;
	private String insplocationdesc;
	private Date inspstartdate;
	@XmlJavaTypeAdapter(value=TimeAdapter.class)
	private Time inspstarttime;
	private String inspstate;
	private String interstate;
	private String level;
	private String localjurisdiction;
	private String manualCarrierIsOos;
	private String manualCarrierNoOpAuth;
	private String manualOosCheckMethod;
	private String manualOosOperAuthCheck;
	private String mexicancarrierid;
	private String milepost;
	private String notes;
	private String oosuntil;
	private String originCity;
	private String originState;
	private String pasainspection;
	private String pbbtaxles;
	private double pbbtbrakeforce;
	private String pbbtcheck;
	private double pbbtminbrakeforce;
	private short pbbtvehicletypeid;
	private String rptstatus;
	private String shipname;
	private String shipnum;
	private String sizeweightenf;
	private String statecensusnum;
	private String statecountycode;
	private String study1;
	private String study10;
	private String study2;
	private String study3;
	private String study4;
	private String study5;
	private String study6;
	private String study7;
	private String study8;
	private String study9;
	private String tag;
	private String timezone;
	private String totaldriveroosvio;
	private String totalhm;
	private String totaloosvio;
	private String totalvehicle;
	private String totalvehicleoosvio;
	private String totalvio;
	private String trafficenf;
	private Date trandate;
	private String transferred;
	@XmlJavaTypeAdapter(value=TimeAdapter.class)
	private Time trantime;
	private String unit1platenum;
	private String usdotnum;
	private String vehicleoos;
	
	private Vehicle vehicle;
	
    public Inspection() {
    }

	public String getRptnum() {
		return this.rptnum;
	}

	public void setRptnum(String rptnum) {
		this.rptnum = rptnum;
	}

	public String getAccident() {
		return this.accident;
	}

	public void setAccident(String accident) {
		this.accident = accident;
	}

	public String getAlcsubchk() {
		return this.alcsubchk;
	}

	public void setAlcsubchk(String alcsubchk) {
		this.alcsubchk = alcsubchk;
	}

	public String getAspen1() {
		return this.aspen1;
	}

	public void setAspen1(String aspen1) {
		this.aspen1 = aspen1;
	}

	public String getAspen2() {
		return this.aspen2;
	}

	public void setAspen2(String aspen2) {
		this.aspen2 = aspen2;
	}

	public String getAspenmatch() {
		return this.aspenmatch;
	}

	public void setAspenmatch(String aspenmatch) {
		this.aspenmatch = aspenmatch;
	}

	public String getAspenversionnum() {
		return this.aspenversionnum;
	}

	public void setAspenversionnum(String aspenversionnum) {
		this.aspenversionnum = aspenversionnum;
	}

	public String getAxles() {
		return this.axles;
	}

	public void setAxles(String axles) {
		this.axles = axles;
	}

	public String getBegfundedinspection() {
		return this.begfundedinspection;
	}

	public void setBegfundedinspection(String begfundedinspection) {
		this.begfundedinspection = begfundedinspection;
	}

	public String getBegfundedtype() {
		return this.begfundedtype;
	}

	public void setBegfundedtype(String begfundedtype) {
		this.begfundedtype = begfundedtype;
	}

	public String getBipdverified() {
		return this.bipdverified;
	}

	public void setBipdverified(String bipdverified) {
		this.bipdverified = bipdverified;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCargotankspecnum() {
		return this.cargotankspecnum;
	}

	public void setCargotankspecnum(String cargotankspecnum) {
		this.cargotankspecnum = cargotankspecnum;
	}

	public String getCarrcity() {
		return this.carrcity;
	}

	public void setCarrcity(String carrcity) {
		this.carrcity = carrcity;
	}

	public String getCarrcolonia() {
		return this.carrcolonia;
	}

	public void setCarrcolonia(String carrcolonia) {
		this.carrcolonia = carrcolonia;
	}

	public String getCarrcountry() {
		return this.carrcountry;
	}

	public void setCarrcountry(String carrcountry) {
		this.carrcountry = carrcountry;
	}

	public String getCarrdbaname() {
		return this.carrdbaname;
	}

	public void setCarrdbaname(String carrdbaname) {
		this.carrdbaname = carrdbaname;
	}

	public String getCarrfax() {
		return this.carrfax;
	}

	public void setCarrfax(String carrfax) {
		this.carrfax = carrfax;
	}

	public String getCarrname() {
		return this.carrname;
	}

	public void setCarrname(String carrname) {
		this.carrname = carrname;
	}

	public String getCarrphone() {
		return this.carrphone;
	}

	public void setCarrphone(String carrphone) {
		this.carrphone = carrphone;
	}

	public String getCarrstate() {
		return this.carrstate;
	}

	public void setCarrstate(String carrstate) {
		this.carrstate = carrstate;
	}

	public String getCarrstreet() {
		return this.carrstreet;
	}

	public void setCarrstreet(String carrstreet) {
		this.carrstreet = carrstreet;
	}

	public String getCarrzipcode() {
		return this.carrzipcode;
	}

	public void setCarrzipcode(String carrzipcode) {
		this.carrzipcode = carrzipcode;
	}

	public Date getCodriverdob() {
		return this.codriverdob;
	}

	public void setCodriverdob(Date codriverdob) {
		this.codriverdob = codriverdob;
	}

	public String getCodriverfname() {
		return this.codriverfname;
	}

	public void setCodriverfname(String codriverfname) {
		this.codriverfname = codriverfname;
	}

	public String getCodriverlicnum() {
		return this.codriverlicnum;
	}

	public void setCodriverlicnum(String codriverlicnum) {
		this.codriverlicnum = codriverlicnum;
	}

	public String getCodriverlicstate() {
		return this.codriverlicstate;
	}

	public void setCodriverlicstate(String codriverlicstate) {
		this.codriverlicstate = codriverlicstate;
	}

	public String getCodriverlname() {
		return this.codriverlname;
	}

	public void setCodriverlname(String codriverlname) {
		this.codriverlname = codriverlname;
	}

	public String getCodrivermi() {
		return this.codrivermi;
	}

	public void setCodrivermi(String codrivermi) {
		this.codrivermi = codrivermi;
	}

	public String getCoinspectorcode() {
		return this.coinspectorcode;
	}

	public void setCoinspectorcode(String coinspectorcode) {
		this.coinspectorcode = coinspectorcode;
	}

	public String getCoinspectorname() {
		return this.coinspectorname;
	}

	public void setCoinspectorname(String coinspectorname) {
		this.coinspectorname = coinspectorname;
	}

	public String getDestinationCity() {
		return this.destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public String getDestinationState() {
		return this.destinationState;
	}

	public void setDestinationState(String destinationState) {
		this.destinationState = destinationState;
	}

	public Date getDriverdob() {
		return this.driverdob;
	}

	public void setDriverdob(Date driverdob) {
		this.driverdob = driverdob;
	}

	public String getDriverfname() {
		return this.driverfname;
	}

	public void setDriverfname(String driverfname) {
		this.driverfname = driverfname;
	}

	public String getDriverlicnum() {
		return this.driverlicnum;
	}

	public void setDriverlicnum(String driverlicnum) {
		this.driverlicnum = driverlicnum;
	}

	public String getDriverlicstate() {
		return this.driverlicstate;
	}

	public void setDriverlicstate(String driverlicstate) {
		this.driverlicstate = driverlicstate;
	}

	public String getDriverlname() {
		return this.driverlname;
	}

	public void setDriverlname(String driverlname) {
		this.driverlname = driverlname;
	}

	public String getDrivermi() {
		return this.drivermi;
	}

	public void setDrivermi(String drivermi) {
		this.drivermi = drivermi;
	}

	public String getDriveroos() {
		return this.driveroos;
	}

	public void setDriveroos(String driveroos) {
		this.driveroos = driveroos;
	}

	public byte[] getDriversignature() {
		return this.driversignature;
	}

	public void setDriversignature(byte[] driversignature) {
		this.driversignature = driversignature;
	}

	public String getDrugarrest() {
		return this.drugarrest;
	}

	public void setDrugarrest(String drugarrest) {
		this.drugarrest = drugarrest;
	}

	public String getDrugsearch() {
		return this.drugsearch;
	}

	public void setDrugsearch(String drugsearch) {
		this.drugsearch = drugsearch;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getEscreen() {
		return this.escreen;
	}

	public void setEscreen(String escreen) {
		this.escreen = escreen;
	}

	public String getFacilitytype() {
		return this.facilitytype;
	}

	public void setFacilitytype(String facilitytype) {
		this.facilitytype = facilitytype;
	}

	public String getFipscountycode() {
		return this.fipscountycode;
	}

	public void setFipscountycode(String fipscountycode) {
		this.fipscountycode = fipscountycode;
	}

	public int getGcwr() {
		return this.gcwr;
	}

	public void setGcwr(int gcwr) {
		this.gcwr = gcwr;
	}

	public String getHighway() {
		return this.highway;
	}

	public void setHighway(String highway) {
		this.highway = highway;
	}

	public String getHminsptype() {
		return this.hminsptype;
	}

	public void setHminsptype(String hminsptype) {
		this.hminsptype = hminsptype;
	}

	public String getHmplacard() {
		return this.hmplacard;
	}

	public void setHmplacard(String hmplacard) {
		this.hmplacard = hmplacard;
	}

	public String getIccnum() {
		return this.iccnum;
	}

	public void setIccnum(String iccnum) {
		this.iccnum = iccnum;
	}

	public String getIeppretripinspection() {
		return this.ieppretripinspection;
	}

	public void setIeppretripinspection(String ieppretripinspection) {
		this.ieppretripinspection = ieppretripinspection;
	}

	public String getIeppretripspace() {
		return this.ieppretripspace;
	}

	public void setIeppretripspace(String ieppretripspace) {
		this.ieppretripspace = ieppretripspace;
	}

	public String getInspcountry() {
		return this.inspcountry;
	}

	public void setInspcountry(String inspcountry) {
		this.inspcountry = inspcountry;
	}

	public String getInspcounty() {
		return this.inspcounty;
	}

	public void setInspcounty(String inspcounty) {
		this.inspcounty = inspcounty;
	}

	public String getInspectorcode() {
		return this.inspectorcode;
	}

	public void setInspectorcode(String inspectorcode) {
		this.inspectorcode = inspectorcode;
	}

	public String getInspectorname() {
		return this.inspectorname;
	}

	public void setInspectorname(String inspectorname) {
		this.inspectorname = inspectorname;
	}

	public Date getInspenddate() {
		return this.inspenddate;
	}

	public void setInspenddate(Date inspenddate) {
		this.inspenddate = inspenddate;
	}

	public Time getInspendtime() {
		return this.inspendtime;
	}

	public void setInspendtime(Time inspendtime) {
		this.inspendtime = inspendtime;
	}

	public String getInsplocationcode() {
		return this.insplocationcode;
	}

	public void setInsplocationcode(String insplocationcode) {
		this.insplocationcode = insplocationcode;
	}

	public String getInsplocationdesc() {
		return this.insplocationdesc;
	}

	public void setInsplocationdesc(String insplocationdesc) {
		this.insplocationdesc = insplocationdesc;
	}

	public Date getInspstartdate() {
		return this.inspstartdate;
	}

	public void setInspstartdate(Date inspstartdate) {
		this.inspstartdate = inspstartdate;
	}

	public Time getInspstarttime() {
		return this.inspstarttime;
	}

	public void setInspstarttime(Time inspstarttime) {
		this.inspstarttime = inspstarttime;
	}

	public String getInspstate() {
		return this.inspstate;
	}

	public void setInspstate(String inspstate) {
		this.inspstate = inspstate;
	}

	public String getInterstate() {
		return this.interstate;
	}

	public void setInterstate(String interstate) {
		this.interstate = interstate;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLocaljurisdiction() {
		return this.localjurisdiction;
	}

	public void setLocaljurisdiction(String localjurisdiction) {
		this.localjurisdiction = localjurisdiction;
	}

	public String getManualCarrierIsOos() {
		return this.manualCarrierIsOos;
	}

	public void setManualCarrierIsOos(String manualCarrierIsOos) {
		this.manualCarrierIsOos = manualCarrierIsOos;
	}

	public String getManualCarrierNoOpAuth() {
		return this.manualCarrierNoOpAuth;
	}

	public void setManualCarrierNoOpAuth(String manualCarrierNoOpAuth) {
		this.manualCarrierNoOpAuth = manualCarrierNoOpAuth;
	}

	public String getManualOosCheckMethod() {
		return this.manualOosCheckMethod;
	}

	public void setManualOosCheckMethod(String manualOosCheckMethod) {
		this.manualOosCheckMethod = manualOosCheckMethod;
	}

	public String getManualOosOperAuthCheck() {
		return this.manualOosOperAuthCheck;
	}

	public void setManualOosOperAuthCheck(String manualOosOperAuthCheck) {
		this.manualOosOperAuthCheck = manualOosOperAuthCheck;
	}

	public String getMexicancarrierid() {
		return this.mexicancarrierid;
	}

	public void setMexicancarrierid(String mexicancarrierid) {
		this.mexicancarrierid = mexicancarrierid;
	}

	public String getMilepost() {
		return this.milepost;
	}

	public void setMilepost(String milepost) {
		this.milepost = milepost;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getOosuntil() {
		return this.oosuntil;
	}

	public void setOosuntil(String oosuntil) {
		this.oosuntil = oosuntil;
	}

	public String getOriginCity() {
		return this.originCity;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	public String getOriginState() {
		return this.originState;
	}

	public void setOriginState(String originState) {
		this.originState = originState;
	}

	public String getPasainspection() {
		return this.pasainspection;
	}

	public void setPasainspection(String pasainspection) {
		this.pasainspection = pasainspection;
	}

	public String getPbbtaxles() {
		return this.pbbtaxles;
	}

	public void setPbbtaxles(String pbbtaxles) {
		this.pbbtaxles = pbbtaxles;
	}

	public double getPbbtbrakeforce() {
		return this.pbbtbrakeforce;
	}

	public void setPbbtbrakeforce(double pbbtbrakeforce) {
		this.pbbtbrakeforce = pbbtbrakeforce;
	}

	public String getPbbtcheck() {
		return this.pbbtcheck;
	}

	public void setPbbtcheck(String pbbtcheck) {
		this.pbbtcheck = pbbtcheck;
	}

	public double getPbbtminbrakeforce() {
		return this.pbbtminbrakeforce;
	}

	public void setPbbtminbrakeforce(double pbbtminbrakeforce) {
		this.pbbtminbrakeforce = pbbtminbrakeforce;
	}

	public short getPbbtvehicletypeid() {
		return this.pbbtvehicletypeid;
	}

	public void setPbbtvehicletypeid(short pbbtvehicletypeid) {
		this.pbbtvehicletypeid = pbbtvehicletypeid;
	}

	public String getRptstatus() {
		return this.rptstatus;
	}

	public void setRptstatus(String rptstatus) {
		this.rptstatus = rptstatus;
	}

	public String getShipname() {
		return this.shipname;
	}

	public void setShipname(String shipname) {
		this.shipname = shipname;
	}

	public String getShipnum() {
		return this.shipnum;
	}

	public void setShipnum(String shipnum) {
		this.shipnum = shipnum;
	}

	public String getSizeweightenf() {
		return this.sizeweightenf;
	}

	public void setSizeweightenf(String sizeweightenf) {
		this.sizeweightenf = sizeweightenf;
	}

	public String getStatecensusnum() {
		return this.statecensusnum;
	}

	public void setStatecensusnum(String statecensusnum) {
		this.statecensusnum = statecensusnum;
	}

	public String getStatecountycode() {
		return this.statecountycode;
	}

	public void setStatecountycode(String statecountycode) {
		this.statecountycode = statecountycode;
	}

	public String getStudy1() {
		return this.study1;
	}

	public void setStudy1(String study1) {
		this.study1 = study1;
	}

	public String getStudy10() {
		return this.study10;
	}

	public void setStudy10(String study10) {
		this.study10 = study10;
	}

	public String getStudy2() {
		return this.study2;
	}

	public void setStudy2(String study2) {
		this.study2 = study2;
	}

	public String getStudy3() {
		return this.study3;
	}

	public void setStudy3(String study3) {
		this.study3 = study3;
	}

	public String getStudy4() {
		return this.study4;
	}

	public void setStudy4(String study4) {
		this.study4 = study4;
	}

	public String getStudy5() {
		return this.study5;
	}

	public void setStudy5(String study5) {
		this.study5 = study5;
	}

	public String getStudy6() {
		return this.study6;
	}

	public void setStudy6(String study6) {
		this.study6 = study6;
	}

	public String getStudy7() {
		return this.study7;
	}

	public void setStudy7(String study7) {
		this.study7 = study7;
	}

	public String getStudy8() {
		return this.study8;
	}

	public void setStudy8(String study8) {
		this.study8 = study8;
	}

	public String getStudy9() {
		return this.study9;
	}

	public void setStudy9(String study9) {
		this.study9 = study9;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTimezone() {
		return this.timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getTotaldriveroosvio() {
		return this.totaldriveroosvio;
	}

	public void setTotaldriveroosvio(String totaldriveroosvio) {
		this.totaldriveroosvio = totaldriveroosvio;
	}

	public String getTotalhm() {
		return this.totalhm;
	}

	public void setTotalhm(String totalhm) {
		this.totalhm = totalhm;
	}

	public String getTotaloosvio() {
		return this.totaloosvio;
	}

	public void setTotaloosvio(String totaloosvio) {
		this.totaloosvio = totaloosvio;
	}

	public String getTotalvehicle() {
		return this.totalvehicle;
	}

	public void setTotalvehicle(String totalvehicle) {
		this.totalvehicle = totalvehicle;
	}

	public String getTotalvehicleoosvio() {
		return this.totalvehicleoosvio;
	}

	public void setTotalvehicleoosvio(String totalvehicleoosvio) {
		this.totalvehicleoosvio = totalvehicleoosvio;
	}

	public String getTotalvio() {
		return this.totalvio;
	}

	public void setTotalvio(String totalvio) {
		this.totalvio = totalvio;
	}

	public String getTrafficenf() {
		return this.trafficenf;
	}

	public void setTrafficenf(String trafficenf) {
		this.trafficenf = trafficenf;
	}

	public Date getTrandate() {
		return this.trandate;
	}

	public void setTrandate(Date trandate) {
		this.trandate = trandate;
	}

	public String getTransferred() {
		return this.transferred;
	}

	public void setTransferred(String transferred) {
		this.transferred = transferred;
	}

	public Time getTrantime() {
		return this.trantime;
	}

	public void setTrantime(Time trantime) {
		this.trantime = trantime;
	}

	public String getUnit1platenum() {
		return this.unit1platenum;
	}

	public void setUnit1platenum(String unit1platenum) {
		this.unit1platenum = unit1platenum;
	}

	public String getUsdotnum() {
		return this.usdotnum;
	}

	public void setUsdotnum(String usdotnum) {
		this.usdotnum = usdotnum;
	}

	public String getVehicleoos() {
		return this.vehicleoos;
	}

	public void setVehicleoos(String vehicleoos) {
		this.vehicleoos = vehicleoos;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public String toIyeTekXml() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		StringBuffer sb = new StringBuffer();

		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>").append(System.lineSeparator())
		.append("<Message UniqueId=\"123\" Requesting=\"LoadResponse\">").append(System.lineSeparator())
		.append("<LEIN>").append(System.lineSeparator())
		.append("<Argument>").append(System.lineSeparator())
		.append("<Response IsHtml=\"False\" IsDataRaw=\"False\">").append(System.lineSeparator())
		.append("<MessageText>This is a test.</MessageText>").append(System.lineSeparator())
		.append("<Data>").append(System.lineSeparator())
		.append("<Person>").append(System.lineSeparator())
		.append("<FirstName>").append(this.getDriverfname()).append("</FirstName>").append(System.lineSeparator())
		.append("<LastName>").append(this.getDriverlname()).append("</LastName>").append(System.lineSeparator())
		.append("<DriverLicense>").append(this.getDriverlicnum()).append("</DriverLicense>").append(System.lineSeparator())
		.append("<DriverLicenseState>").append(this.getDriverlicstate()).append("</DriverLicenseState>").append(System.lineSeparator())
		.append("<DateOfBirth>").append(sdf.format(this.getDriverdob())).append("</DateOfBirth>").append(System.lineSeparator())
		.append("<Sex></Sex>").append(System.lineSeparator())
		.append("<StreetNumber></StreetNumber>").append(System.lineSeparator())
		.append("<StreetPreDirection></StreetPreDirection>").append(System.lineSeparator())
		.append("<StreetName></StreetName>").append(System.lineSeparator())
		.append("<StreetType></StreetType>").append(System.lineSeparator())
		.append("<City></City>").append(System.lineSeparator())
		.append("<State></State>").append(System.lineSeparator())
		.append("<Zip></Zip>").append(System.lineSeparator())
		.append("</Person></Data></Response></Argument></LEIN></Message>").append(System.lineSeparator());

		return sb.toString();
	}

}