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
    	Todo todo = new Todo();
    	todo.first = 1;
    	todo.second = 2;
    	ResultSet rs = DBConnection.processQuery("select * from crime_data Fetch First 1000 rows only");
    	List<CrimeData>  result = crimeDataRowMapper( rs ) ;
    	DBConnection.con.close();
        return result;
    }
    
    @GET
    @Path("/crime/area/{area}")
    @Produces(MediaType.APPLICATION_JSON)
    public Todo crimedataByArea( @PathParam("area") String area) {
    	Todo todo = new Todo();
    	Integer A_Id = Integer.parseInt(area) ;
    	todo.first = 1;
    	todo.second = 2;
    	
    	DBConnection.processQuery("select * from crime_data where A_id = " + A_Id  );
        return todo;
    }
    
    @GET
    @Path("/crime/type/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public Todo crimedataByType( @PathParam("type") String type) {
    	Todo todo = new Todo();
    	//Integer c_type = Integer.parseInt(type) ;
    	todo.first = 1;
    	todo.second = 2;
    	
    	DBConnection.processQuery("select * from crime_data where C_Type = '" + type + "'"  );
        return todo;
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
    		
    		//System.out.println("Debug");
    		//System.out.println(rs.getObject(1) + "  " + rs.getObject(2) + "  " + rs.getObject(3));
    		list.add(crimedata) ;
    	}
			//System.out.println(rs.getObject(1) + "  " + rs.getObject(2) + "  " + rs.getObject(3));
    		
    	return list;
    	    

    }
}
