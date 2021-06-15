package com.contracts.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;

import com.contractcs.service.AgreementService;
import com.contracts.domain.Agreement;
import com.contracts.domain.Scan;
import com.contracts.service.impl.AgreementServiceImpl;

public class AgreementController {

	private AgreementService agreementService = new AgreementServiceImpl();

	public void addAll() throws IOException, ParseException {
		agreementService.addAll();
	}

	public List<Agreement> getAll() {
		return agreementService.getAll();
	}

	// 1.1. Add scans to a contract
	public void addScans(Agreement agreement, List<Scan> scansToAdd) {
		agreementService.addScans(agreement, scansToAdd);
	}

	// 1.2. Remove scans from a contract
	public void removeAllScans(Agreement agreement) {
		agreementService.removeAllScans(agreement);
	}

	// 2. Check if the text is found in any of the contract scans
	public List<String> getScansWithText(Agreement agreement, String text) {
		return agreementService.getScansWithText(agreement, text);
	}

	// 3. Total page count based on the scans contained in the contract
	public int countPages(Agreement agreement) {
		return agreementService.countPages(agreement);
	}

	// 4. Get a list of the file names from all the scans contained in a list of contracts where the company acting as myCompany matches the input id
	public List<String> getFileNames(List<Agreement> agreements, String id) {
		return agreementService.getFileNames(agreements, id);
	}

	// 5. Group a list of contracts by counterParty company and return a map structure so that the key is the counterParty id and the value is the list of contracts for that company
	public Map<String, List<Agreement>> getAgreements(List<Agreement> totalAgreements) {
		return agreementService.getAgreements(totalAgreements);
	}

}
