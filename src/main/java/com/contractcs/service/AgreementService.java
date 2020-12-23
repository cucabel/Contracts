package com.contractcs.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;

import com.contracts.domain.Agreement;
import com.contracts.domain.Scan;

public interface AgreementService {

	public void addAgreement() throws IOException, ParseException;

	public List<Agreement> getAllAgreements();

	public void addScansToAgreement(Agreement agreement, List<Scan> scansListToAdd);

	public void removeAllAgreemetScans(Agreement agreement);

	public List<String> scansWithTextAgreementsList(Agreement agreementGiven, String text);

	public int agreementTotalPages(Agreement agreement);

	public List<String> fileNamesOfTheCompanyWithGivenId(List<Agreement> agreementsList, String id);

	public Map<String, List<Agreement>> asignAgreementsListToCompany(List<Agreement> agreementsList);

}
