package com.crime_analysis.demo.crime_analysis;

import java.math.*;

public class CrimeChange{
	public Integer year;
	public Integer number_of_times;
	public String c_type;
	public Integer getYear() {
		return year;
	}
	public void setYear(Object year) {
		this.year = Integer.parseInt( year.toString() );
	}
	public Integer getNumber_of_times() {
		return number_of_times;
	}
	public void setNumber_of_times(Object number_of_times) {
		this.number_of_times = Integer.parseInt( number_of_times.toString() );
	}
	public String getC_type() {
		return c_type;
	}
	public void setC_type(Object c_type) {
		this.c_type = c_type.toString();
	}
	
}