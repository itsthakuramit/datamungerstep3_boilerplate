package com.stackroute.datamunger.query;

public class Header {
	/*
	 * This class should contain a member variable which is a String array, to hold
	 * the headers.
	 */	

	String[] header= new String[] {};
	
	
	public Header(String[] header) {
		super();
		this.header = header;
	}

	
	public String[] getHeaders() {
		return header;
	}

}
