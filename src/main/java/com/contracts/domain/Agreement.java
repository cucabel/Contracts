package com.contracts.domain;
import java.util.ArrayList;
import java.util.List;

public class Agreement {			//An agreement is a contract between two companies
	public Company myCompany;
	public Company counterpartyCompany;
	public List<Scan> scansList = new ArrayList<>();
	public int numberOfPages;
	
	public Agreement() {}

	public Agreement(Company companyOne, Company companyTwo, List<Scan> scansList) {
		myCompany = companyOne;
		counterpartyCompany = companyTwo;
		this.scansList = scansList;
	}

	public Company getMyCompany() {
		return myCompany;
	}

	public void setMyCompany(Company myCompany) {
		this.myCompany = myCompany;
	}

	public Company getCounterpartyCompany() {
		return counterpartyCompany;
	}

	public void setCounterpartyCompany(Company counterpartyCompany) {
		this.counterpartyCompany = counterpartyCompany;
	}

	public List<Scan> getScansList() {
		return scansList;
	}

	public void setScansList(List<Scan> scansList) {
		this.scansList = scansList;
	}
		
	//1.1. Add scans to a contract
	public void addScans(List<Scan> scansListToAdd) {
		if (this.scansList == null)
				setScansList(scansListToAdd);
		else if (!(this.scansList == null))
			for (Scan scanToAdd : scansListToAdd)
				scansList.add(scanToAdd);		
	}
	
	//1.2. Remove scans from a contract
	public void removeAllScans() {
		scansList.clear();
	}
		
	//2. Check if the text is found in any of the contract scans
	public List<String> scansWithTextList (String text) {
		List<String> scansWithTextList = new ArrayList<>();
		for (Scan scan : scansList)
			if (scan.getText().equals(text))
				scansWithTextList.add(scan.getFileName());
		return scansWithTextList;			
	}
	
	//3. Total page count based on the scans contained in the contract
	public int numberOfPages() {
		for (Scan scan : scansList)
			numberOfPages += scan.getPageCount();
		return numberOfPages;
	}

	//4. Get a list of the file names from all the scans contained in a list of contracts where the company name matches the input id
	public List<String> scansFilesNames (List<Scan> scansList) {
		List<String> scansFileNames = new ArrayList<>();
		for (Scan scan : scansList)
			scansFileNames.add(scan.getFileName());
		return scansFileNames;
	}

	@Override
	public String toString() {
		String result =  "myCompany: " + myCompany + ", \ncounterpartyCompany: " + counterpartyCompany + ", \nscansList: ";
		for (Scan scan : getScansList()) {
			result += "scan: " + scan + ",\n";
		}
		return result;
		
	}
	
}



