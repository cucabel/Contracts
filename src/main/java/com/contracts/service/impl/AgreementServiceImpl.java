package com.contracts.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.json.simple.parser.ParseException;

import com.contractcs.service.AgreementService;
import com.contracts.domain.Agreement;
import com.contracts.domain.Scan;
import com.contracts.repository.AgreementRepository;

public class AgreementServiceImpl implements AgreementService {
	
	private AgreementRepository agreementRepository;

	public AgreementServiceImpl() {
		agreementRepository = new AgreementRepository();
	}

	@Override
	public void addAgreement() throws IOException, ParseException {
		agreementRepository.addAgreement();
	}

	@Override
	public List<Agreement> getAllAgreements() {
		return agreementRepository.getAllAgreements();
	}

	@Override
	public void addScansToAgreement(Agreement agreement, List<Scan> scansListToAdd) {
		agreement.addScans(scansListToAdd);		
	}

	@Override
	public void removeAllAgreemetScans(Agreement agreement) {
		agreement.removeAllScans();
	}

	@Override
	public List<String> scansWithTextAgreementsList(Agreement agreementGiven, String text) {
		return agreementGiven.scansWithTextList(text);
	}

	@Override
	public int agreementTotalPages(Agreement agreement) {
		return agreement.numberOfPages();
	}

	@Override
	public List<String> fileNamesOfTheCompanyWithGivenId(List<Agreement> agreementsList, String id) {
		
		return agreementsList.stream().filter(a -> a.getMyCompany().getId().equals(id)).map(a -> a.getScansList()).flatMap(s -> s.stream()).map(s -> s.getFileName()).collect(Collectors.toList());
	}

	@Override
	public Map<String, List<Agreement>> asignAgreementsListToCompany(List<Agreement> agreementsList) {

		String counterpartyCompanyId = null;
		List<String> counterpartyCompaniesIdsList = new ArrayList<>();
		List<List<Agreement>> counterpartyCompanyAgreementsListsList = new ArrayList<>();
		Map<String, List<Agreement>> agreementsPerCounterpartyCompanyMap = new HashMap<>();

		for (Agreement agreement1 : agreementsList) {
			counterpartyCompanyId = agreement1.getCounterpartyCompany().getId();
			if (!counterpartyCompaniesIdsList.contains(counterpartyCompanyId))
				counterpartyCompaniesIdsList.add(counterpartyCompanyId);
		}

		for (int i = 0; i < counterpartyCompaniesIdsList.size(); i++) {
			counterpartyCompanyAgreementsListsList.add(new ArrayList<>());
			for (Agreement agreement2 : agreementsList) {
				if (agreement2.getCounterpartyCompany().getId().equals(counterpartyCompaniesIdsList.get(i)))
					counterpartyCompanyAgreementsListsList.get(i).add(agreement2);
			}
			if (!agreementsPerCounterpartyCompanyMap.containsKey(counterpartyCompaniesIdsList.get(i)))
				agreementsPerCounterpartyCompanyMap.put(counterpartyCompaniesIdsList.get(i),
						counterpartyCompanyAgreementsListsList.get(i));

		}

		return agreementsPerCounterpartyCompanyMap;
	}

}
