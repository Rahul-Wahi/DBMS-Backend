package com.crime_analysis.demo.crime_analysis;

public class CrimeTime {
	private int frequency;
	private String crimeType;
	private String hour;
	
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(Object frequency) {
		
		if( frequency != null)
		this.frequency = Integer.parseInt(frequency.toString());
	}
	
	public String getCrimeType() {
		return crimeType;
	}
	public void setCrimeType(Object crimeType) {
		
		if( crimeType != null)
		this.crimeType = crimeType.toString();
	}
	
	public String getHour() {
		return hour;
	}
	public void setHour(Object hour) {
		
		if( hour != null)
		this.hour = hour.toString();
	}
}
