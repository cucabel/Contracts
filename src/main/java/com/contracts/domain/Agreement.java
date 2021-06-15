package com.contracts.domain;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Agreement {			//An agreement is a contract between two companies
	public Company myCompany;
	public Company counterpartyCompany;
	public List<Scan> scans = new ArrayList<>();
	
	public Agreement() {}

	public Agreement(Company myCompany, Company counterpartyCompany, List<Scan> scans) {
		this.myCompany = myCompany;
		this.counterpartyCompany = counterpartyCompany;
		this.scans = scans;
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

	public List<Scan> getScans() {
		return scans;
	}

	public void setScans(List<Scan> scans) {
		this.scans = scans;
	}
		
	//1.1. Add scans to a contract
	public void addScans(List<Scan> scansToAdd) {
		if (this.scans == null) setScans(scansToAdd);
		else scansToAdd.forEach(this.scans::add);
	}
	
	//1.2. Remove scans from a contract
	public void removeAllScans() {
		this.scans.clear();
	}
		
	//2. Check if the text is found in any of the contract scans
	public List<String> getScansWithText (String text) {
		return this.scans.stream().filter(s -> s.getText().equals(text)).map(s -> s.getFileName()).collect(Collectors.toList());
	}
	
	//3. Total page count based on the scans contained in the contract
	public int countPages() {
		return this.scans.stream().map(s -> s.getPageCount()).reduce(0, Integer::sum);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agreement other = (Agreement) obj;
		if (counterpartyCompany == null) {
			if (other.counterpartyCompany != null)
				return false;
		} else if (!counterpartyCompany.equals(other.counterpartyCompany))
			return false;
		if (myCompany == null) {
			if (other.myCompany != null)
				return false;
		} else if (!myCompany.equals(other.myCompany))
			return false;
		if (scans == null) {
			if (other.scans != null)
				return false;
		} else if (!scans.equals(other.scans))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String result =  "myCompany: " + myCompany + ", \ncounterpartyCompany: " + counterpartyCompany + ", \nscansList: ";
		for (Scan scan : getScans()) {
			result += "scan: " + scan + ",\n";
		}
		return result;
	}

}
