package com.contracts.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.contractcs.service.AgreementService;
import com.contracts.controller.AgreementController;
import com.contracts.domain.Agreement;
import com.contracts.domain.Company;
import com.contracts.domain.Scan;
import com.contracts.service.impl.AgreementServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AgreementControllerTest {
	
	@InjectMocks
	AgreementController agreementController = new AgreementController();
	@Mock
	AgreementService agreementService = new AgreementServiceImpl();
	
	Agreement agreement1, agreement2, agreement3, agreement4, agreement5;
	
	@Before
	public void init() {
		Scan scan1 = new Scan("document1.pdf", "Lorem ipsumdolor sit amet, consectetuer adipiscing elit", 5);
		Scan scan2 = new Scan("document2.pdf", "Cum sociis natoque penatibus et magnis dis parturient montes", 3);
		Scan scan3 = new Scan("document3.pdf", "Nullam dictum felis eu pede mollis pretium. Integer tincidunt", 4);
		Scan scan4 = new Scan("document4.pdf", "Etiam ultricies nisi vel augue", 1);
		Scan scan5 = new Scan("document5.pdf", "it must appear", 1);
		Scan scan7 = new Scan("document7.pdf", "it must not appear", 1);

		List<Scan> scans0 = Arrays.asList(scan1);
		List<Scan> scans1 = Arrays.asList(scan1, scan2, scan3, scan4);
		List<Scan> scans3 = Arrays.asList(scan5);
		List<Scan> scans4 = Arrays.asList(scan7);

		agreement1 = new Agreement(new Company("san", "Santander"), new Company("bbva", "Banco Bilbao Vizcaya Argentaria"), scans1);
		agreement2 = new Agreement(new Company("san", "Santander"), new Company("bbva", "Banco Bilbao Vizcaya Argentaria"), scans0);
		agreement3 = new Agreement(new Company("san", "Santander"), new Company("bbva", "Banco Bilbao Vizcaya Argentaria"), scans3);
		agreement4 = new Agreement(new Company("kutxa", "kutxa bank"), new Company("sbd", "Banc Sabadell"), scans4);
	}
	
	@Test
	public void getAll() {
		List<Agreement> agreements = Arrays.asList(agreement1, agreement2, agreement3, agreement4);
		when(agreementService.getAll()).thenReturn(agreements);
		assertEquals(agreements, agreementController.getAll());
	}
		
	//2. Check if the text is found in any of the contract scans
	@Test
	public void getScansWithText() {				
		String text = "Nullam dictum felis eu pede mollis pretium. Integer tincidunt";
		List<String> names = Arrays.asList("document3.pdf");
		
		when(agreementService.getScansWithText(ArgumentMatchers.any(Agreement.class), ArgumentMatchers.any(String.class))).thenReturn(names);
		Assert.assertEquals(names, agreementController.getScansWithText(agreement1, text));
	}
	
	//3. Total page count based on the scans contained in the contract
	@Test
	public void testCountPages() {
		when(agreementService.countPages(ArgumentMatchers.any(Agreement.class))).thenReturn(13);
		Assert.assertEquals(13, agreementController.countPages(agreement1));
	}

	//4. Get a list of the file names from all the scans contained in a list of contracts where the company acting as myCompany matches the input id
	@Test
	public void testGetFileNames() {
		List<String> names = Arrays.asList("document1.pdf", "document5.pdf");

		when(agreementService.getFileNames(ArgumentMatchers.anyList(), ArgumentMatchers.any(String.class))).thenReturn(names);
		Assert.assertEquals(names, agreementController.getFileNames(Arrays.asList(agreement2, agreement3, agreement4), "san"));
	}

	//5. Group a list of contracts by counterParty company and return a map structure so that the key is the counterParty id and the value is the list of contracts for that company
	@Test
	public void testGetAgreements() {					
		Map<String, List<Agreement>> expected = new HashMap<>();
		expected.put("bbva", Arrays.asList(agreement2, agreement3));
		expected.put("sbd", Arrays.asList(agreement4));
		
		when(agreementService.getAgreements(ArgumentMatchers.anyList())).thenReturn(expected);
		Assert.assertEquals(expected, agreementController.getAgreements(Arrays.asList(agreement2, agreement3, agreement4)));
	}

}
