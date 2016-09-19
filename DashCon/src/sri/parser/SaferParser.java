package sri.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import sri.data.safer.CompanyData;
import sri.data.safer.CrashData;
import sri.data.safer.InspectionData;
import sri.data.safer.InspectionDataRow;
import sri.data.safer.SaferUSDOTData;
import sri.data.safer.SafetyRating;


public class SaferParser {
	
	private static final String BASE_URL = "https://safer.fmcsa.dot.gov/query.asp?searchtype=ANY&query_type=queryCarrierSnapshot&query_param=USDOT&query_string=";
	
	public static void main(String[] args){
		try {
			SaferUSDOTData data = new SaferParser().getSaferDataForUSDOT("2465358");
			System.out.println("Done "+data.getCompanyData().getLegalName());
			System.out.println(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public SaferUSDOTData getSaferDataForUSDOT(String usDotNumber) throws IOException{
		SaferUSDOTData data = new SaferUSDOTData();
		Document doc = getDocument(false, BASE_URL+usDotNumber);
		Elements tables = doc.select("center > table");
		
		if (tables.size() == 6) {
			CompanyData cData = parseCompanyTable(tables.get(0));
			InspectionData usInspectionData = parseUSInspectionData(tables.get(1));
			CrashData usCrashData = parseUSCrashData(tables.get(2));
			InspectionData canadaInspectionData = parseCanadaInspectionData(tables.get(3)); 
			CrashData canadaCrashData = parseUSCrashData(tables.get(4));
			SafetyRating rating = parseSafetyRating(tables.get(5));
			
			
			data.setCompanyData(cData);
			data.setUsInspectionData(usInspectionData);
			data.setUsCrashData(usCrashData);
			data.setCanadaInspectionData(canadaInspectionData);
			data.setCanadaCrashData(canadaCrashData);
			data.setSafetyRating(rating);
			
			return data;
		}
		
		return null;
	}



	private CompanyData parseCompanyTable(Element element){
		List<String> values = new ArrayList<String>();
		Elements rows = element.select("TR");
		for(int i=1;i<12;i++){
			Element rowElement = rows.get(i);
			Elements cellElements = rowElement.select("TD");
			if(cellElements.size() > 0){
				for(Element dataCell:cellElements){
					TextNode node = null;
					String text = null;
					if(dataCell.childNode(0) instanceof TextNode && dataCell.childNodes().size() ==1){
						node = (TextNode)dataCell.childNode(0);
						text = node.text();
						text = text.replace("\u00a0", "");
					}else{
//						node = findTextNode(dataCell);
						text = getTextForElement(dataCell);
					}
					values.add(text);
				}
			}
		}
		CompanyData data = new CompanyData();
		if(values.size() > 15){
			data.setEntityType(values.get(0));
			data.setOperatingStatus(values.get(1));
			data.setOutOfServiceDate(values.get(2));
			data.setLegalName(values.get(3));
			data.setDbaName(values.get(4));
			data.setPhysicalAddress(values.get(5));
			data.setPhone(values.get(6));
			data.setMailingAddress(values.get(7));
			data.setUsDOTNumber(values.get(8));
			data.setStateCarrierId(values.get(9));
			data.setMcmxffNumber(values.get(10));
			data.setDunsNumber(values.get(11));
			data.setPowerUnits(values.get(12));
			data.setDrivers(values.get(13));
			data.setMcs150(values.get(14));
			data.setMcs150Mileage(values.get(15));
			return data;
		}else{
			System.err.println("Error parsing all 16 fields");
			return null;
		}
		
		
	}
	
	
	private InspectionData parseUSInspectionData(Element element){
		InspectionData data = new InspectionData();
		
		Elements rows = element.select("TR");
		
		for(int i=1;i<5;i++){
			List<String> values = new ArrayList<String>();
			Element rowElement = rows.get(i);
			Elements cellElements = rowElement.select("TD");
			if(cellElements.size() > 0){
				for(Element dataCell:cellElements){
					TextNode node = null;
					String text = null;
					if(dataCell.childNode(0) instanceof TextNode && dataCell.childNodes().size() ==1){
						node = (TextNode)dataCell.childNode(0);
						text = node.text();
					}else{
//						node = findTextNode(dataCell);
						text = getTextForElement(dataCell);
					}
					values.add(text);
				}
				InspectionDataRow dataRow = new InspectionDataRow();
				dataRow.setVehicle(values.get(0));
				dataRow.setDriver(values.get(1));
				dataRow.setHazmat(values.get(2));
				dataRow.setIep(values.get(3));
				setInspectionDataRow(data, dataRow, i);
			}
			
		}
		
		return data;
	}
	
	private CrashData parseUSCrashData(Element element){
		CrashData data = new CrashData();
		Elements rows = element.select("TR");
		
		List<String> values = new ArrayList<String>();
		Element rowElement = rows.get(1);
		Elements cellElements = rowElement.select("TD");
		if(cellElements.size() > 0){
			for(Element dataCell:cellElements){
				TextNode node = null;
				String text = null;
				if(dataCell.childNode(0) instanceof TextNode && dataCell.childNodes().size() ==1){
					node = (TextNode)dataCell.childNode(0);
					text = node.text();
				}else{
					text = getTextForElement(dataCell);
				}
				values.add(text);
			}
			data.setFatal(values.get(0));
			data.setInjury(values.get(1));
			data.setTow(values.get(2));
			data.setTotal(values.get(3));
		
		}
		
		return data;
	}
	
	
	private InspectionData parseCanadaInspectionData(Element element) {
		InspectionData data = new InspectionData();
		
		Elements rows = element.select("TR");
		
		for(int i=1;i<4;i++){
			List<String> values = new ArrayList<String>();
			Element rowElement = rows.get(i);
			Elements cellElements = rowElement.select("TD");
			if(cellElements.size() > 0){
				for(Element dataCell:cellElements){
					TextNode node = null;
					String text = null;
					if(dataCell.childNode(0) instanceof TextNode && dataCell.childNodes().size() ==1){
						node = (TextNode)dataCell.childNode(0);
						text = node.text();
					}else{
//						node = findTextNode(dataCell);
						text = getTextForElement(dataCell);
					}
					values.add(text);
				}
				InspectionDataRow dataRow = new InspectionDataRow();
				dataRow.setVehicle(values.get(0));
				dataRow.setDriver(values.get(1));
				setInspectionDataRow(data, dataRow, i);
			}
			
		}
		
		return data;

	}	
	
	
	private SafetyRating parseSafetyRating(Element element){
		SafetyRating data = new SafetyRating();
		Elements rows = element.select("TR");
		List<String> values = new ArrayList<String>();
		for(int i=1;i<3;i++){
			
			Element rowElement = rows.get(i);
			Elements cellElements = rowElement.select("TD");
			if(cellElements.size() > 0){
				for(Element dataCell:cellElements){
					TextNode node = null;
					String text = null;
					if(dataCell.childNode(0) instanceof TextNode && dataCell.childNodes().size() ==1){
						node = (TextNode)dataCell.childNode(0);
						text = node.text();
					}else{
//						node = findTextNode(dataCell);
						text = getTextForElement(dataCell);
					}
					values.add(text);
				}

			}
		}
		data.setRatingDate(values.get(0));
		data.setReviewDate(values.get(1));
		data.setRating(values.get(2));
		data.setType(values.get(3));
		
		
		return data;
	}
	
	private void setInspectionDataRow(InspectionData data, InspectionDataRow row, int line){
		switch(line){
			case 1:{
				data.setInspections(row);
			}break;
			case 2:{
				data.setOutOfService(row);
			}break;
			case 3:{
				data.setOutOfServicePercent(row);
			}break;
			case 4:{
				data.setNatAveragePercent(row);
			}break;
		}
	}

	private String getTextForElement(Element element){
		List<TextNode> retList = getAllTextNodes(element);
		StringBuilder sb = new StringBuilder();
		String ts;
		for(TextNode node:retList){
			if(!"".equals(node.text().trim()) && !" ".equals(node.text().trim())){
				ts = node.text();
				ts = ts.replace("\u00a0", "");
				sb.append(ts);
			}
		}
		return sb.toString();
	}
	
	private List<TextNode> getAllTextNodes(Element element){
		List<TextNode> retList = new ArrayList<TextNode>();
		List<Node> nodes = element.childNodes();
		for(int i=0;i<nodes.size();i++){
			if(nodes.get(i) instanceof TextNode){
				if(!"".equals(((TextNode)nodes.get(i)).text().trim()) && ! " ".equals(((TextNode)nodes.get(i)).text().trim())){
					retList.add((TextNode)nodes.get(i));
				}
			}else{
				List<TextNode> aList = getAllTextNodes((Element)element.childNode(i));
				if(aList != null){
					retList.addAll(aList);	
				}
			}
		}
		
		
		return retList;
	}
	
	private TextNode findTextNode(Element element){
		TextNode node = null;
		List<Node> nodes = element.childNodes();
		for(int i=0;i<nodes.size();i++){
			if(nodes.get(i) instanceof TextNode){
				if(!"".equals(((TextNode)nodes.get(i)).text().trim()) && ! " ".equals(((TextNode)nodes.get(i)).text().trim())){
					return (TextNode)nodes.get(i);
				}
			}else{
				node = findTextNode((Element)element.childNode(i));
				if(node != null){
					return node;
				}
			}
		}
		
		
//		while(! (element.childNode(0) instanceof TextNode)){
//			element = element.child(0);
//		}
//		node = (TextNode)element.childNode(0);
		return node;
	}
	
	private Document getDocument(boolean isFile, String location) throws IOException{
		if(isFile){
			File input = new File(location);
			return Jsoup.parse(input, "UTF-8");
		}else{
			return Jsoup.connect(location).get();
		}
		
	}
}
