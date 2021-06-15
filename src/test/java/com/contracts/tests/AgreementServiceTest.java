package com.contracts.tests;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import com.contractcs.service.AgreementService;
import com.contracts.domain.Agreement;
import com.contracts.domain.Company;
import com.contracts.domain.Scan;
import com.contracts.service.impl.AgreementServiceImpl;

public class AgreementServiceTest {
	
	@InjectMocks
	private AgreementService agreementService = new AgreementServiceImpl();
	private Agreement agreement;
	
	Agreement agreement0, agreement1, agreement2, agreement3, agreement4, agreement5;
	
	@Before
	public void init() {
		agreement = Mockito.mock(Agreement.class);
		
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
	public void testGetScansWithText() {
		String text = "Nullam dictum felis eu pede mollis pretium. Integer tincidunt";
		List<String> names = Arrays.asList("document3.pdf");
		
		when(agreement.getScansWithText(ArgumentMatchers.any(String.class))).thenReturn(names);
		Assert.assertEquals(names, agreementService.getScansWithText(agreement1, text));
	}
	
	@Test
	public void testCountPages() {
		when(agreement.countPages()).thenReturn(13);
		Assert.assertEquals(13, agreementService.countPages(agreement1));
	}

	@Test
	public void getFileNames() {
		List<String> names = Arrays.asList("document1.pdf", "document5.pdf");

		Assert.assertEquals(names, agreementService.getFileNames(Arrays.asList(agreement2, agreement3, agreement4), "san"));		
	}

	@Test
	public void testGetAgreents() {
		Map<String, List<Agreement>> expected = new HashMap<>();
		expected.put("bbva", Arrays.asList(agreement2, agreement3));
		expected.put("sbd", Arrays.asList(agreement4));
		
		Assert.assertEquals(expected, agreementService.getAgreements(Arrays.asList(agreement2, agreement3, agreement4)));		
	}

}
