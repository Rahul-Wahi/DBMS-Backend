package com.crime_analysis.demo.crime_analysis;

import java.math.*;

public class CrimeData {

	public Long id;
	public double lattitude ;
	public double longtitude ;
	public String report_date ;
	public String last_updated_by ;
	public String offense_time ;
	public String report_time ;
	public String c_name ;
	public String s_name ;
	public String c_type ;
	public long a_id ;
	public String area ;
	public Long getId() {
		return id;
	}
	public void setId(Object id) {
		if( id != null )
		{
			this.id = Long.parseLong( id.toString() ) ;
		}
		
	}
	public double getLattitude() {
		return lattitude;
	}
	public void setLattitude(Object lattitude) {
		if(lattitude != null )
		{
			this.lattitude = Double.parseDouble( lattitude.toString() ) ;
		}
		
	}
	public double getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(Object longtitude) {
		
		if(longtitude != null )
		{
			this.longtitude = Double.parseDouble( longtitude.toString() ) ;
		}
		
	}
	public String getReport_date() {
		return report_date;
	}
	public void setReport_date(Object report_date) {
		if( report_date != null)
		{
			this.report_date = report_date.toString();
		}
		
	}
	public String getLast_updated_by() {
		return last_updated_by;
	}
	public void setLast_updated_by(Object last_updated_by) {
		if( last_updated_by != null )
		{
			this.last_updated_by = last_updated_by.toString();
		}
		
	}
	public String getOffense_time() {
		return offense_time;
	}
	public void setOffense_time(Object offense_time) {
		if( offense_time != null )
		{
			this.offense_time = offense_time.toString();
		}
		
	}
	public String getReport_time() {
		return report_time;
	}
	public void setReport_time(Object report_time) {
		
		if( report_time !=null)
		this.report_time = report_time.toString();
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(Object c_name) {
		
		if( c_name != null )
		this.c_name = c_name.toString();
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(Object s_name) {
		if( s_name != null)
		this.s_name = s_name.toString();
	}
	public String getC_type() {
		return c_type;
	}
	public void setC_type(Object c_type) {
		if( c_type != null)
		this.c_type = c_type.toString();
	}
	public long getA_id() {
		return a_id;
	}
	public void setA_id(Object a_id) {
		if( a_id != null)
		this.a_id = Long.parseLong( a_id.toString() );
	}
	public String getArea() {
		return area;
	}
	public void setArea(Object area) {
		if( area != null)
		this.area = area.toString();
	}
	
	
	
	
	
}
