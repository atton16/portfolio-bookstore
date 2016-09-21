package com.sixppl.examples;

import java.sql.*;

import com.sixppl.main.Application;

public class databaseQuery {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "SELECT Name, Value FROM Variable WHERE Name = ?";
		try{
			Application.getSharedInstance().init(null);	//This statement is normally executed by servlet
			conn = Application.getSharedInstance().getDAOSupport().getConnection();

			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			
			// Use prepare statement to secure the query string
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "OrderNumber");	// Every input should be set by so that we are safe from malicious input
			ResultSet rs = stmt.executeQuery();

			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				String name = rs.getString("Name");
				int value  = rs.getInt("Value");

				//Display values
				System.out.print("Name: " + name);
				System.out.println(", Value: " + value);
			}
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			if(conn!=null)
				Application.getSharedInstance().getDAOSupport().destroy();	//This statement is normally executed by servlet
		}//end try
		System.out.println("Goodbye!");
	}
}
