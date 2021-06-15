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
	List<Scan> scans1, scans2;
	
	@Before
	public void init() {
		scan1 = new Scan("document1.pdf", "Lorem ipsumdolor sit amet, consectetuer adipiscing elit", 5);
		scans1 = new ArrayList<>();
		scans1.add(scan1);
		
		scan2 = new Scan("document2.pdf", "Cum sociis natoque penatibus et magnis dis parturient montes", 3);
		scans2 = new ArrayList<>();
		scans2.add(scan2);
		
		agreement = new Agreement(new Company("kutxa", "Kutxa Bank"), new Company("sbd", "Banc Sabadell"), scans1);
	}

    //1.1. Add scans to a contract
	@Test
	public void testAddScans() {
		agreement.addScans(scans2);
		assertEquals(Arrays.asList(scan1, scan2), agreement.getScans());
	}

	//1.2. Remove scans from a contract
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
		agreement.addScans(scans2);
		assertEquals(8, agreement.countPages());
	}
	
}
