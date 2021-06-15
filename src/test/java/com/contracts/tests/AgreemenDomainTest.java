package com.contracts.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.contracts.domain.Agreement;
import com.contracts.domain.Company;
import com.contracts.domain.Scan;

public class AgreemenDomainTest {
	
	Scan scan1, scan2;
	Agreement agreement;
	
	@Before
	public void init() {
		Company myCompany = new Company("kutxa", "Kutxa Bank");
		Company counterpartyCompany = new Company("sbd", "Banc Sabadell");
		scan1 = new Scan("document1.pdf", "Lorem ipsumdolor sit amet, consectetuer adipiscing elit", 5);
		scan2 = new Scan("document2.pdf", "Cum sociis natoque penatibus et magnis dis parturient montes", 3);
		List<Scan> scans1 = new ArrayList<>();
		scans1.add(scan1);

		agreement = new Agreement(myCompany, counterpartyCompany, scans1);
	}

	@Test
	public void testAddScans() {
		List<Scan> scans2 = new ArrayList<>();
		scans2.add(scan2);
		
		agreement.addScans(scans2);
		assertEquals(Arrays.asList(scan1, scan2), agreement.getScans());
	}

	@Test
	public void testRemoveAllScans() {
		agreement.removeAllScans();
		assertEquals(new ArrayList<>(), agreement.getScans());
	}

	@Test
	public void testGetScansWithText() {
		String text = "Lorem ipsumdolor sit amet, consectetuer adipiscing elit";

		assertEquals(Arrays.asList("document1.pdf"), agreement.getScansWithText(text));
	}
	
	@Test
	public void testCountPages() {
		List<Scan> scans2 = new ArrayList<>();
		scans2.add(scan2);
		
		agreement.addScans(scans2);
		assertEquals(8, agreement.countPages());
	}
	
}
