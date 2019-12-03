package com.crime_analysis.demo.crime_analysis;

public class Area {
	
	private String street_address ;
	private long a_id ;
	public String getStreet_address() {
		return street_address;
	}
	public void setStreet_address(Object street_address) {
		
		if( street_address != null )
		this.street_address = street_address.toString();
	}
	public long getA_id() {
		return a_id;
	}
	public void setA_id(Object a_id) {
		if( a_id != null )
		this.a_id = Long.parseLong( a_id.toString() );
	}
	
	
	

}
