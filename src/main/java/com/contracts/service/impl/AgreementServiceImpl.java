package com.contracts.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	public void addAll() throws IOException, ParseException {
		agreementRepository.addAll();
	}

	@Override
	public List<Agreement> getAll() {
		return agreementRepository.getAll();
	}

	@Override
	public void addScans(Agreement agreement, List<Scan> scansToAdd) {
		agreement.addScans(scansToAdd);		
	}

	@Override
	public void removeAllScans(Agreement agreement) {
		agreement.removeAllScans();
	}

	@Override
	public List<String> getScansWithText(Agreement agreement, String text) {
		return agreement.getScansWithText(text);
	}

	@Override
	public int countPages(Agreement agreement) {
		return agreement.countPages();
	}

	@Override
	public List<String> getFileNames(List<Agreement> agreements, String id) {
		return agreements.stream()
				.filter(a -> a.getMyCompany().getId().equals(id))
				.map(a -> a.getScans())
				.flatMap(s -> s.stream())
				.map(s -> s.getFileName())
				.collect(Collectors.toList());
	}

	@Override
	public Map<String, List<Agreement>> getAgreements(List<Agreement> totalAgreements) {
		Map<String, List<Agreement>> agreements = new HashMap<>();

		totalAgreements.forEach(a -> {
			String counterpartyCiaId = a.getCounterpartyCompany().getId();
			
				if (!agreements.containsKey(counterpartyCiaId)) {
					agreements.put(counterpartyCiaId, new ArrayList<>());
					agreements.get(counterpartyCiaId).add(a);
				} else {
					agreements.get(counterpartyCiaId).add(a);
				}
		});
		
		return agreements;
	}

}
