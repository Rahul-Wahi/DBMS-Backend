package com.crime_analysis.demo.crime_analysis;

public class Unemployement {

	private String year ;
	private Double rate ;
	
	public String getYear() {
		return year;
	}
	public  void setYear(Object year) {
		
		if( year != null)
			this.year = year.toString() ;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Object rate) {
		if( rate != null)
			this.rate = Double.parseDouble( rate.toString() ) ;
	}
}
