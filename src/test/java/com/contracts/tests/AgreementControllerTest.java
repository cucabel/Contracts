package com.contracts.tests;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.contracts.controller.AgreementController;
import com.contracts.domain.Agreement;
import com.contracts.domain.Scan;

public class AgreementControllerTest {
	
	AgreementController agreementController = new AgreementController();
	
	List<Agreement> agreementsList;
	Agreement agreement0, agreement1, agreement2, agreement3, agreement4, agreement5;
	
	@Before
	public void init() throws IOException, ParseException {
		agreementController.addAll();	
		agreementsList = agreementController.getAllAgreements();
		agreement0 = agreementsList.get(0);
		agreement1 = agreementsList.get(1);
		agreement2 = agreementsList.get(2);
		agreement3 = agreementsList.get(3);
		agreement4 = agreementsList.get(4);
		agreement5 = agreementsList.get(5);
	}
	
    //1.1. Add scans to a contract
	@Test
	public void testAddScans() {		
		Scan scan1 = new Scan("document1.pdf", "Lorem ipsumdolor sit amet, consectetuer adipiscing elit", 5);
		Scan scan2 = new Scan("document2.pdf", "Cum sociis natoque penatibus et magnis dis parturient montes", 3);
		
		agreementController.addScans(agreement0, Arrays.asList(scan2));								
		Assert.assertEquals(Arrays.asList(scan1, scan2), agreement0.getScans());
	}
	
	//1.2. Remove scans from a contract
	@Test	
	public void testRemoveAllScans() {		
		agreementController.removeAllScans(agreement5);
		Assert.assertEquals(new ArrayList<>(), agreement5.getScans());
	}
	
	//2. Check if the text is found in any of the contract scans
	@Test
	public void getScansWithText() {				
		String text = "Nullam dictum felis eu pede mollis pretium. Integer tincidunt";
		Scan scan3 = new Scan("document3.pdf", "Nullam dictum felis eu pede mollis pretium. Integer tincidunt", 4);

		Assert.assertEquals(Arrays.asList(scan3.getFileName()), agreementController.getScansWithText(agreement1, text));
	}
	
	//3. Total page count based on the scans contained in the contract
	@Test
	public void testCountPages() {
		Assert.assertEquals(13, agreementController.countPages(agreement1));
	}

	//4. Get a list of the file names from all the scans contained in a list of contracts where the company acting as myCompany matches the input id
	@Test
	public void testGetFileNames() {
		Assert.assertEquals(Arrays.asList("document1.pdf", "document5.pdf"), agreementController.getFileNames(Arrays.asList(agreement2, agreement3, agreement4), "san"));
	}

	//5. Group a list of contracts by counterParty company and return a map structure so that the key is the counterParty id and the value is the list of contracts for that company
	@Test
	public void testGetAgreements() {					
		Map<String, List<Agreement>> expected = new HashMap<>();
		expected.put("bbva", Arrays.asList(agreement2, agreement3));
		expected.put("sbd", Arrays.asList(agreement4));
		
		Assert.assertEquals(expected, agreementController.getAgreements(Arrays.asList(agreement2, agreement3, agreement4)));
	}

}
