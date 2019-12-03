package com.crime_analysis.demo.crime_analysis;

import java.sql.*;

class DBConnection {
	
	public static Connection con ;
	
	public static void DBConnection() throws ClassNotFoundException, SQLException
	{
		  
		 
	}
	public static ResultSet processQuery(String query) {
		try {
			
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");

	//step2 create  the connection object  
			//Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl", "rachna", "Rach10893");

			 con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl", "rachna", "Rach10893");

			
			//step3 create the statement object
			Statement stmt = con.createStatement();
			//step4 execute query  
						ResultSet rs = stmt.executeQuery(query);
						System.out.println("haha") ;
						ResultSetMetaData rsmd = rs.getMetaData();
						int columnsNumber = rsmd.getColumnCount();
//						while (rs.next())
//						{//System.out.println(rs.getObject(1) + "  " + rs.getObject(2) + "  " + rs.getObject(3));
//							for (int i = 1; i <= columnsNumber; i++) {
//						        if (i > 1) System.out.print(",  ");
//						        String columnValue = rs.getString(i);
//						        //System.out.println(columnValue + " " + rsmd.getColumnName(i));
//						    }
//						}
						//System.out.println(rs);

						

			//step5 close the connection object  
						 
						//con.close();
						return rs ;

					} catch (Exception e) {
						System.out.println(e);
					}
		return null;
	}
}
