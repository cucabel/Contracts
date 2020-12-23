package com.contracts.domain;

public class Company {
	
	private String id;
	public String name;
	
	public Company() {}
	
	public Company(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "id = " + id + ", name = " + name;
	}

}



