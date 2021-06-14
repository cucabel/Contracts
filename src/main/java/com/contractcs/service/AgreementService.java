package com.contractcs.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;

import com.contracts.domain.Agreement;
import com.contracts.domain.Scan;

public interface AgreementService {

	public void addAll() throws IOException, ParseException;

	public List<Agreement> getAll();

	public void addScans(Agreement agreement, List<Scan> scans);

	public void removeAllScans(Agreement agreement);

	public List<String> getScansWithText(Agreement agreement, String text);

	public int countPages(Agreement agreement);

	public List<String> getFileNames(List<Agreement> agreementsList, String id);

	public Map<String, List<Agreement>> getAgreements(List<Agreement> agreementsList);

}
