package com.crime_analysis.demo.crime_analysis;

public class CrimeRate {
	
	private String year ;
	private Integer crimeCount ;
	public String getYear() {
		return year;
	}
	public void setYear(Object year) {
		
		if( year != null)
		this.year = year.toString();
	}
	public int getCrimeCount() {
		return crimeCount;
	}
	public void setCrimeCount(Object crimeCount) {
		
		if( crimeCount != null)
		this.crimeCount = Integer.parseInt( crimeCount.toString() ) ;
	}
	
	 

}
