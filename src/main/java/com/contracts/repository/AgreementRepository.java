package com.contracts.repository;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.contracts.domain.Agreement;
import com.contracts.domain.Company;
import com.contracts.domain.Scan;

public class AgreementRepository {
																			
	private static List<Agreement> agreements = new ArrayList<>();			
	
	public AgreementRepository() {}
	
	public void addAgreement() throws IOException, org.json.simple.parser.ParseException {
		
		JSONParser jsonParser = new JSONParser();
				
		FileReader reader = new FileReader("DataBase/Agreements.json");
				
		Object obj = jsonParser.parse(reader);
		
		JSONArray agreementsListJsonObj = (JSONArray) obj;
		
		for (int i = 0; i < agreementsListJsonObj.size(); i++) {
			JSONObject agrJsonObj = (JSONObject)agreementsListJsonObj.get(i);
			
			JSONObject myCompanyObj = (JSONObject) agrJsonObj.get("myCompany");

			String myCompanyId = (String) myCompanyObj.get("id");							
			String myCompanyName = (String) myCompanyObj.get("name");
			
			//java
			Company myCompany = new Company(myCompanyId, myCompanyName);
			
			JSONObject counterpartyCompanyObj = (JSONObject) agrJsonObj.get("counterpartyCompany");
			
			String counterpartyCompanyobjId = (String) counterpartyCompanyObj.get("id");							
			String counterpartyCompanyobjName = (String) counterpartyCompanyObj.get("name");
			
			//java
			Company counterpartyCompany = new Company(counterpartyCompanyobjId, counterpartyCompanyobjName);
			
			JSONArray scansListArray = (JSONArray) agrJsonObj.get("scansList");
			
			//java
			List<Scan> scansList = new ArrayList<>();
			 
			for (int j = 0; j < scansListArray.size(); j++) {							
				JSONObject scanObj = (JSONObject)scansListArray.get(j);					
				 
				String scanFileName = (String) scanObj.get("fileName");							
				String scanText = (String) scanObj.get("text");							
				String scanPageCount = (String) scanObj.get("pageCount");							
				
				//java
				int scanPageCountToInt = new Integer(scanPageCount);
				Scan scan = new Scan(scanFileName, scanText, scanPageCountToInt);
				scansList.add(scan);
			 }
			
			 Agreement agreement = new Agreement(myCompany, counterpartyCompany, scansList);
			 agreements.add(agreement);
		}

	}
		
	//2., 4.
	public List<Agreement> getAllAgreements() {
		return agreements;
	}

}
