package org.company.pkg;
import java.sql.*;
public class LoginDao {

	public static boolean validate(String email,String password){
		boolean status=false;
		try{
			Connection con=ConnectionProvider.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from company where email=? and password=? and authorized=? ");
			ps.setString(1,email);
			ps.setString(2,password);
			ps.setString(3,"yes");
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				status=true;
			}
			
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}
}
