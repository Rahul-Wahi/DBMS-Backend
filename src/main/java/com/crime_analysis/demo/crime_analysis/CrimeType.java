package com.crime_analysis.demo.crime_analysis;

public class CrimeType {
	
	private String crimeType ;

	public String getCrimeType() {
		return crimeType;
	}

	public void setCrimeType(Object crimeType) {
		
		if( crimeType != null )
		this.crimeType = crimeType.toString();
	}
	
	

}
