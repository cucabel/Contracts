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

	public void addAgreement() throws IOException, ParseException {
		agreementService.addAgreement();
	}

	public List<Agreement> getAllAgreements() {
		return agreementService.getAllAgreements();
	}

	// 1.1. Add scans to a contract
	public void addScansToAgreement(Agreement agreement, List<Scan> scansListToAdd) {
		agreementService.addScansToAgreement(agreement, scansListToAdd);
	}

	// 1.2. Remove scans from a contract
	public void removeAllAgreemetScans(Agreement agreement) {
		agreementService.removeAllAgreemetScans(agreement);
	}

	// 2. Check if the text is found in any of the contract scans
	public List<String> scansWithTextAgreementsList(Agreement agreementGiven, String text) {
		return agreementService.scansWithTextAgreementsList(agreementGiven, text);
	}

	// 3. Total page count based on the scans contained in the contract
	public int agreementTotalPages(Agreement agreement) {
		return agreementService.agreementTotalPages(agreement);
	}

	// 4. Get a list of the file names from all the scans contained in a list of
	// contracts where the company acting as myCompany matches
	// the input id
	public List<String> fileNamesOfTheCompanyWithGivenId(List<Agreement> agreementsList, String id) {
		return agreementService.fileNamesOfTheCompanyWithGivenId(agreementsList, id);
	}

	// 5. Group a list of contracts by counterParty company and return a map
	// structure so that the key is the counterParty id and the
	// value is the list of contracts for that company
	public Map<String, List<Agreement>> asignAgreementsListToCompany(List<Agreement> agreementsList) {
		return agreementService.asignAgreementsListToCompany(agreementsList);
	}

}
