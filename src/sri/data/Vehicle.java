package sri.data;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import sri.util.TimeAdapter;


/**
 * The persistent class for the VEHICLE database table.
 * 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Vehicle extends SriPojo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String rptnum;
	private String unitnum;
	private String cargosealremovedid;
	private String cargosealreplacedid;
	private String cvsadecal;
	private String decalnum;
	private String existingdecalnum;
	private String existingdecalstatus;
	private String iepchassispool;
	private String iepdatasource;
	@XmlJavaTypeAdapter(value=TimeAdapter.class)
	private Time ieplookuptimestamp;
	private String iepname;
	private String iepusdotnum;
	private String oosnum;
	private String unitconum;
	private int unitgvwr;
	private String unitlicnum;
	private String unitlicstate;
	private String unitmake;
	private String unittype;
	private String unitvin;
	private String unityear;

    public Vehicle() {
    }

	public String getRptnum() {
		return this.rptnum;
	}
	public void setRptnum(String rptnum) {
		this.rptnum = rptnum;
	}
	public String getUnitnum() {
		return this.unitnum;
	}
	public void setUnitnum(String unitnum) {
		this.unitnum = unitnum;
	}
	public String getCargosealremovedid() {
		return this.cargosealremovedid;
	}

	public void setCargosealremovedid(String cargosealremovedid) {
		this.cargosealremovedid = cargosealremovedid;
	}

	public String getCargosealreplacedid() {
		return this.cargosealreplacedid;
	}

	public void setCargosealreplacedid(String cargosealreplacedid) {
		this.cargosealreplacedid = cargosealreplacedid;
	}

	public String getCvsadecal() {
		return this.cvsadecal;
	}

	public void setCvsadecal(String cvsadecal) {
		this.cvsadecal = cvsadecal;
	}

	public String getDecalnum() {
		return this.decalnum;
	}

	public void setDecalnum(String decalnum) {
		this.decalnum = decalnum;
	}

	public String getExistingdecalnum() {
		return this.existingdecalnum;
	}

	public void setExistingdecalnum(String existingdecalnum) {
		this.existingdecalnum = existingdecalnum;
	}

	public String getExistingdecalstatus() {
		return this.existingdecalstatus;
	}

	public void setExistingdecalstatus(String existingdecalstatus) {
		this.existingdecalstatus = existingdecalstatus;
	}

	public String getIepchassispool() {
		return this.iepchassispool;
	}

	public void setIepchassispool(String iepchassispool) {
		this.iepchassispool = iepchassispool;
	}

	public String getIepdatasource() {
		return this.iepdatasource;
	}

	public void setIepdatasource(String iepdatasource) {
		this.iepdatasource = iepdatasource;
	}

	public Time getIeplookuptimestamp() {
		return this.ieplookuptimestamp;
	}

	public void setIeplookuptimestamp(Time ieplookuptimestamp) {
		this.ieplookuptimestamp = ieplookuptimestamp;
	}

	public String getIepname() {
		return this.iepname;
	}

	public void setIepname(String iepname) {
		this.iepname = iepname;
	}

	public String getIepusdotnum() {
		return this.iepusdotnum;
	}

	public void setIepusdotnum(String iepusdotnum) {
		this.iepusdotnum = iepusdotnum;
	}

	public String getOosnum() {
		return this.oosnum;
	}

	public void setOosnum(String oosnum) {
		this.oosnum = oosnum;
	}

	public String getUnitconum() {
		return this.unitconum;
	}

	public void setUnitconum(String unitconum) {
		this.unitconum = unitconum;
	}

	public int getUnitgvwr() {
		return this.unitgvwr;
	}

	public void setUnitgvwr(int unitgvwr) {
		this.unitgvwr = unitgvwr;
	}

	public String getUnitlicnum() {
		return this.unitlicnum;
	}

	public void setUnitlicnum(String unitlicnum) {
		this.unitlicnum = unitlicnum;
	}

	public String getUnitlicstate() {
		return this.unitlicstate;
	}

	public void setUnitlicstate(String unitlicstate) {
		this.unitlicstate = unitlicstate;
	}

	public String getUnitmake() {
		return this.unitmake;
	}

	public void setUnitmake(String unitmake) {
		this.unitmake = unitmake;
	}

	public String getUnittype() {
		return this.unittype;
	}

	public void setUnittype(String unittype) {
		this.unittype = unittype;
	}

	public String getUnitvin() {
		return this.unitvin;
	}

	public void setUnitvin(String unitvin) {
		this.unitvin = unitvin;
	}

	public String getUnityear() {
		return this.unityear;
	}

	public void setUnityear(String unityear) {
		this.unityear = unityear;
	}
	
	public String toIyeTekXml() {
		StringBuffer sb = new StringBuffer();

		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>").append(System.lineSeparator())
		.append("<Message UniqueId=\"123\" Requesting=\"LoadResponse\">").append(System.lineSeparator())
		.append("<LEIN>").append(System.lineSeparator())
		.append("<Argument>").append(System.lineSeparator())
		.append("<Response IsHtml=\"False\" IsDataRaw=\"False\">").append(System.lineSeparator())
		.append("<MessageText>This is a test.</MessageText>").append(System.lineSeparator())
		.append("<Data>").append(System.lineSeparator())
		.append("<Vehicle>").append(System.lineSeparator())
		.append("<PlateNumber>").append(this.getUnitlicnum()).append("</PlateNumber>").append(System.lineSeparator())
		.append("<PlateState>").append(this.getUnitlicstate()).append("</PlateState>").append(System.lineSeparator())
		.append("<Make>").append(this.getUnitmake()).append("</Make>").append(System.lineSeparator())
		.append("<Model></Model>").append(System.lineSeparator())
		.append("<Type>").append(this.getUnittype()).append("</Type>").append(System.lineSeparator())
		.append("<Style></Style>").append(System.lineSeparator())
		.append("<VIN>").append(this.getUnitvin()).append("</VIN>").append(System.lineSeparator())
		.append("<PlateYear>").append(this.getUnityear()).append("</PlateYear>").append(System.lineSeparator())
		.append("<VehicleYear>").append(this.getUnityear()).append("</VehicleYear>").append(System.lineSeparator())
		.append("<ExpirationDate></ExpirationDate>").append(System.lineSeparator())
		.append("<PrimaryColor></PrimaryColor>").append(System.lineSeparator())
		.append("</Vehicle></Data></Response></Argument></LEIN></Message>").append(System.lineSeparator());

		return sb.toString();
	}

}