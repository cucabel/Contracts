package com.contracts.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import com.contractcs.service.AgreementService;
import com.contracts.domain.Agreement;
import com.contracts.domain.Scan;
import com.contracts.service.impl.AgreementServiceImpl;

public class AgreementServiceTest {
	
	private AgreementService agreementService = new AgreementServiceImpl();
		
	//1.1. Add scans to a contract
	@Test
	public void testAddScans() throws IOException, ParseException {
		agreementService.addAgreement();	
		List<Agreement> agreementsList = agreementService.getAllAgreements();
		Agreement agreement1 = agreementsList.get(0);
		
		Scan scan1 = new Scan("document1.pdf", "Lorem ipsumdolor sit amet, consectetuer adipiscing elit", 5);
		Scan scan2 = new Scan("document2.pdf", "Cum sociis natoque penatibus et magnis dis parturient montes", 3);
		
		//result
		List<Scan> scansListToAdd = new ArrayList<>();
		scansListToAdd.add(scan2);
		
		agreementService.addScansToAgreement(agreement1, scansListToAdd);
		
		List<Scan> resultAddedScansList = agreement1.getScansList();
		
		//expResult
		List<Scan> expScansList = new ArrayList<>();
		expScansList.add(scan1);
		expScansList.add(scan2);
				
		//Assert
		Assert.assertEquals(expScansList, resultAddedScansList);

	}
	
	//1.2. Remove scans from a contract
	@Test	
	public void testRemoveAllScans() throws IOException, ParseException {
		agreementService.addAgreement();	
		List<Agreement> agreementsList = agreementService.getAllAgreements();
		Agreement agreement6 = agreementsList.get(5);
		
		agreementService.removeAllAgreemetScans(agreement6);
		
		//result
		List<Scan> resultRemovedScansList = agreement6.getScansList();
		System.out.println(resultRemovedScansList);
				
		//expResult
		List<Scan> expEmptyList = new ArrayList<>();
		System.out.println(expEmptyList);

		//Assert
		Assert.assertEquals(expEmptyList, resultRemovedScansList);
		
	}
	
	//2. Check if the text is found in any of the contract scans
	@Test
	public void scansWithTextList() throws IOException, ParseException {
		agreementService.addAgreement();	
		List<Agreement> agreementsList = agreementService.getAllAgreements();
		Agreement agreement1 = agreementsList.get(1);
				
		//result
		String text = "Nullam dictum felis eu pede mollis pretium. Integer tincidunt";
		List<String> resultScansNamesList = agreementService.scansWithTextAgreementsList(agreement1, text);
		
		//expResult
		Scan scan3 = new Scan("document3.pdf", "Nullam dictum felis eu pede mollis pretium. Integer tincidunt", 4);
		List<String> expScansNamesList = new ArrayList<String>();
		expScansNamesList.add(scan3.getFileName());
		
		//Assert
		Assert.assertEquals(expScansNamesList, resultScansNamesList);

	}
	
	//3. Total page count based on the scans contained in the contract
	@Test
	public void testNumberOfPages() throws IOException, ParseException {
		agreementService.addAgreement();	
		List<Agreement> agreementsList = agreementService.getAllAgreements();
		Agreement agreement1 = agreementsList.get(1);

		//result
		int resultTotalPagesNumber = agreementService.agreementTotalPages(agreement1);
		
		//expResult
		int expTotalPagesNumber = 13;
		
		//Assert
		Assert.assertEquals(expTotalPagesNumber, resultTotalPagesNumber);
		
	}

	//4. Get a list of the file names from all the scans contained in a list of contracts where the company acting as myCompany matches
	//the input id
	@Test
	public void fileNamesOfTheCompanyWithGivenId() throws IOException, ParseException {
		agreementService.addAgreement();	
		List<Agreement> agreementsList = agreementService.getAllAgreements();
		Agreement agreement3 = agreementsList.get(2);
		Agreement agreement4 = agreementsList.get(3);
		Agreement agreement5 = agreementsList.get(4);
		
		List<Agreement> agreementsList2 = new ArrayList<>();
		agreementsList2.add(agreement3);
		agreementsList2.add(agreement4);
		agreementsList2.add(agreement5);

		//result
		List<String> resultFileNamesList = agreementService.fileNamesOfTheCompanyWithGivenId(agreementsList2, "san");

		//expResult
		Scan scan1 = new Scan("document1.pdf", "Lorem ipsumdolor sit amet, consectetuer adipiscing elit", 5);
		Scan scan5 = new Scan("document5.pdf", "it must appear", 1);

		List<String> expFileNamesList = new ArrayList<>();
		expFileNamesList.add(scan1.getFileName());
		expFileNamesList.add(scan5.getFileName());
		
		//Assertion
		Assert.assertEquals(expFileNamesList, resultFileNamesList);
		
	}

	//5. Group a list of contracts by counterParty company and return a map structure so that the key is the counterParty id and the 
	//value is the list of contracts for that company
	@Test
	public void testAsignAgreementsListToCompany() throws IOException, ParseException {
		agreementService.addAgreement();	
		List<Agreement> agreementsList = agreementService.getAllAgreements();
		Agreement agreement3 = agreementsList.get(2);
		Agreement agreement4 = agreementsList.get(3);
		Agreement agreement5 = agreementsList.get(4);
		
		List<Agreement> agreementsList2 = new ArrayList<>();
		agreementsList2.add(agreement3);
		agreementsList2.add(agreement4);
		agreementsList2.add(agreement5);

		//result
		Map<String, List<Agreement>> resultAgreementsPerCounterpartyCompanyMap = agreementService.asignAgreementsListToCompany(agreementsList2);
		
		//expResult
		List<Agreement> sbdAgreementsList = new ArrayList<>();
		sbdAgreementsList.add(agreement5);
		
		List<Agreement> bbvaAgreementsList = new ArrayList<>();
		bbvaAgreementsList.add(agreement3);
		bbvaAgreementsList.add(agreement4);
		
		Map<String, List<Agreement>> expAgreementsPerCounterpartyCompanyMap = new HashMap<>();
		expAgreementsPerCounterpartyCompanyMap.put("bbva", bbvaAgreementsList);
		expAgreementsPerCounterpartyCompanyMap.put("sbd", sbdAgreementsList);
		
		//Assert
		Assert.assertEquals(expAgreementsPerCounterpartyCompanyMap, resultAgreementsPerCounterpartyCompanyMap);
		
	}

}
