package org.company.pkg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckDao {
	
	public static boolean inValid(String email) 
	{
		//boolean status=false;
		
		try {
			
			Connection con=ConnectionProvider.getConnection();
	
			PreparedStatement ps=con.prepareStatement("select * from company");
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				if (rs.getString(1).equals(email))
				{
					//System.out.println("email from this name is already registered");
					return true;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		return false;
		
	}
	

}
