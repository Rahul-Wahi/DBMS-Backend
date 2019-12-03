package com.crime_analysis.demo.crime_analysis;

import java.math.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
//@Path("/myresource")
@Path("/data")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     * @throws SQLException
     */
    @GET
    @Path("/crime")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CrimeData> getIt() throws SQLException {

    	ResultSet rs = DBConnection.processQuery("select * from crime_data Fetch First 1000 rows only");
    	List<CrimeData>  result = crimeDataRowMapper( rs ) ;
    	DBConnection.con.close();
        return result;
    }

    @GET
    @Path("/crime/area/{area}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CrimeData> crimedataByArea( @PathParam("area") String area) throws SQLException {

    	Integer A_Id = Integer.parseInt(area) ;

    	ResultSet rs = DBConnection.processQuery("select * from crime_data where A_id = " + A_Id  );
    	List<CrimeData>  result = crimeDataRowMapper( rs ) ;
    	DBConnection.con.close();
        return result;
    }

    @GET
    @Path("/crime_change/{c_type}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CrimeChange> crimedataByChange( @PathParam("c_type") String c_type) throws SQLException {

    	String C_TYPE = c_type ;

    	ResultSet rs = DBConnection.processQuery("select * from (SELECT num, year, C_TYPE FROM (SELECT EXTRACT(YEAR FROM REPORT_DATE) AS year, C_TYPE, COUNT(REPORT_DATE) as num  FROM crime_data group by EXTRACT(YEAR FROM REPORT_DATE), C_TYPE ) GROUP BY year, C_TYPE, num ORDER BY year DESC) where C_TYPE =  '"+ C_TYPE +"' " );
    	List<CrimeChange>  result = crimedataByChangeRowMapper( rs ) ;
    	DBConnection.con.close();
        return result;
    }





    //Crime types
    @GET
    @Path("/crimetype")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CrimeType> crimedataByType( @PathParam("type") String type) throws SQLException {

    	ResultSet rs = DBConnection.processQuery("select * from crime_type "  );
    	List<CrimeType> result = crimeTypeRowMapper(rs ) ;
    	DBConnection.con.close();
        return result;
    }


    //Crime data on with filter crime type
    @GET
    @Path("/crime/type/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CrimeData> crimeType( @PathParam("type") String type) throws SQLException {


    	ResultSet rs = DBConnection.processQuery("select * from crime_data where C_Type = '" + type + "'"  );
    	List<CrimeData>  result = crimeDataRowMapper( rs ) ;
    	DBConnection.con.close();
        return result;
    }

    @GET
    @Path("/area")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Area> area() throws SQLException {

    	ResultSet rs = DBConnection.processQuery("select * from Street_Address "  );
    	List<Area> result = areaRowMapper(rs ) ;
        return result;
    }

    @GET
    @Path("/unemployement")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unemployement> unemployement(  ) throws SQLException {

    	ResultSet rs = DBConnection.processQuery("select * from Unemployement "  );
    	List<Unemployement> result = unemployementRowMapper(rs ) ;
    	DBConnection.con.close();
        return result;
    }

    @GET
    @Path("/crime/rate")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CrimeRate> crimeRate(  ) throws SQLException {

    	ResultSet rs = DBConnection.processQuery("SELECT COUNT(id) as count , year FROM (SELECT  id, EXTRACT(YEAR FROM REPORT_DATE) AS year  FROM crime_data )\n" +
    			"GROUP BY year "  );
    	List<CrimeRate> result = crimeRateRowMapper(rs ) ;
    	DBConnection.con.close();
        return result;
    }



    @GET
    @Path("/crimetype/maxarea")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CrimeRate> crimeTypeMaxArea(  ) throws SQLException {

    	ResultSet rs = DBConnection.processQuery("select CD.C_TYPE, SA.address\n" +
    			"from crime_data CD, street_address SA\n" +
    			"where CD.A_ID = SA.ID\n" +
    			"GROUP BY C_TYPE, address\n" +
    			"HAVING COUNT(report_date) = (SELECT MAX(COUNT(report_date)) from crime_data CD, street_address SA\n" +
    			"where CD.A_ID = SA.ID\n" +
    			"GROUP BY C_TYPE, address) "  );
    	List<CrimeRate> result = crimeRateRowMapper(rs ) ;
    	DBConnection.con.close();
        return result;
    }


    @GET
    @Path("/crime/time")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CrimeTime> crimeTime() throws SQLException {
    	
    	ResultSet rs = DBConnection.processQuery("Select T.freq, T.c_type , T.hour from \n" + 
    			"(WITH crime_hour AS (select id , c_type, A_id ,  EXTRACT(HOUR FROM Report_time) AS Hour FROM crime_data)\n" + 
    			"select  COUNT(*) AS freq , CH1.c_type , CH1.hour from crime_hour  CH1, street_address  SA1\n" + 
    			"WHERE CH1.A_ID = SA1.id \n" + 
    			"GROUP BY CH1.c_type , CH1.hour order by CH1.c_type) T\n" + 
    			"WHERE T.freq = ( SELECT MAX(freq) from  (WITH crime_hour AS (select id , c_type, A_id ,  EXTRACT(HOUR FROM Report_time) AS Hour FROM crime_data)\n" + 
    			"select  COUNT(*) AS freq , CH1.c_type , CH1.hour from crime_hour  CH1, street_address  SA1\n" + 
    			"WHERE CH1.A_ID = SA1.id \n" + 
    			"GROUP BY CH1.c_type , CH1.hour  ) T1  where T1.c_type = T.c_type  )\n" + 
    			"order by freq desc\n" + 
    			";");
    	List<CrimeTime> result = crimeTimeRowMapper(rs);
    	DBConnection.con.close();
    	return result;
    }
   
    @GET
    @Path("/unemployement/factor")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Factor> crimeFactor() throws SQLException {
    	
    	ResultSet rs = DBConnection.processQuery("select year,rate \n" +
    			"from unemployment_data where year >= 2011 order by year asc;");
    	List<Factor> result = factorDataRowMapper(rs);
    	DBConnection.con.close();
    	return result;
    }




    @POST
    public String postIt(Todo todo) {
    	System.out.println(todo.first);
    	System.out.println(todo.second);
    	return "Hello World";
    }

    public List<CrimeData> crimeDataRowMapper( ResultSet rs ) throws SQLException
    {
    	List<CrimeData> list = new ArrayList<CrimeData>() ;
    	while (rs.next()) {
    		CrimeData crimedata = new CrimeData();

    		crimedata.setId( rs.getObject(1));
    		crimedata.setLattitude( rs.getObject(2));
    		crimedata.setLongtitude( rs.getObject(3));
    		crimedata.setReport_date(  rs.getObject(4));
    		crimedata.setLast_updated_by( rs.getObject(5));
    		crimedata.setOffense_time( rs.getObject(6));
    		crimedata.setReport_time( rs.getObject(7));
    		crimedata.setC_name( rs.getObject(8));
    		crimedata.setS_name( rs.getObject(9));
    		crimedata.setC_type( rs.getObject(10));
    		crimedata.setA_id( rs.getObject(11));

    		
    		list.add(crimedata) ;
    	}
			

    	return list;


    }

    //Crime Type mapper
    public List<CrimeType> crimeTypeRowMapper( ResultSet rs ) throws SQLException
    {
    	List<CrimeType> list = new ArrayList<CrimeType>() ;
    	while (rs.next()) {
    		CrimeType crimetype = new CrimeType();

    		crimetype.setCrimeType( rs.getObject(1));

    		list.add(crimetype) ;
    	}
			

    	return list;


    }

  //Area mapper
    public List<Area> areaRowMapper( ResultSet rs ) throws SQLException
    {
    	List<Area> list = new ArrayList<Area>() ;
    	while (rs.next()) {
    		Area area = new Area();

    		area.setA_id( rs.getObject(1));
    		area.setStreet_address(rs.getObject(2));

    		
    		list.add(area) ;
    	}
			

    	return list;


    }

  //Unemployment mapper
    public List<Unemployement> unemployementRowMapper( ResultSet rs ) throws SQLException
    {
    	List<Unemployement> list = new ArrayList<Unemployement>() ;
    	while (rs.next()) {
    		Unemployement unemployement = new Unemployement();

    		unemployement.setYear( rs.getObject(1));

    		unemployement.setRate( rs.getObject(2) ) ;

    		list.add(unemployement) ;
    	}
			

    	return list;


    }


    //Unemployment mapper
    public List<CrimeRate> crimeRateRowMapper( ResultSet rs ) throws SQLException
    {
    	List<CrimeRate> list = new ArrayList<CrimeRate>() ;
    	while (rs.next()) {
    		CrimeRate crimeRate = new CrimeRate();

    		crimeRate.setYear( rs.getObject(2));

    		crimeRate.setCrimeCount( rs.getObject(1) ) ;

    		list.add(crimeRate) ;
    	}
			
    	return list;

    }


    //crimeTypeMaxArea mapper
    public List<CrimeData> crimeTypeMaxAreaRowMapper( ResultSet rs ) throws SQLException
    {
    	List<CrimeData> list = new ArrayList<CrimeData>() ;
    	while (rs.next()) {
    		CrimeData crimeData = new CrimeData();
    		crimeData.setC_type( rs.getObject(1));
    		crimeData.setArea( rs.getObject(2 ) );

    		list.add(crimeData) ;
    	}
			

    	return list;


    }
    
  //CrimeChange mapper
    public List<CrimeChange> crimedataByChangeRowMapper( ResultSet rs ) throws SQLException
    {
    	List<CrimeChange> list = new ArrayList<CrimeChange>() ;
    	while (rs.next()) {
    		CrimeChange crimeChange = new CrimeChange();

    		crimeChange.setNumber_of_times( rs.getObject(1) ) ;
    		crimeChange.setYear( rs.getObject(2));
    		crimeChange.setC_type( rs.getObject(3));
  
    		list.add(crimeChange) ;
    	}
			

    	return list;


    }
    
    
    //crimeTime mapper
    public List<CrimeTime> crimeTimeRowMapper (ResultSet rs) throws SQLException
    {
    	List<CrimeTime> list = new ArrayList<CrimeTime>();
    	while(rs.next()) {
    		CrimeTime crimeTime = new CrimeTime();
    		
    		crimeTime.setFrequency(rs.getObject(1));
    		
    		crimeTime.setCrimeType(rs.getObject(2));
    		
    		crimeTime.setHour(rs.getObject(3));
    		
    		list.add(crimeTime);
    	}
    	
    	return list;
    }
    
    // factors
    private List<Factor> factorDataRowMapper(ResultSet rs) throws SQLException
    {
		
    	List<Factor> list = new ArrayList<Factor>();
    	while(rs.next()){
    		Factor factor = new Factor();
    		
    		factor.setYear(rs.getObject(1));
    		
    		factor.setRate(rs.getObject(2));
    		
    		list.add(factor);
    	}
    	
		return list;
	}
    
    
    
    
}
