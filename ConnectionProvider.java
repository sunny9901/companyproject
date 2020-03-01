package org.company.pkg;



import java.sql.*;

import com.mysql.jdbc.Driver;
public class ConnectionProvider {

	public static Connection getConnection(){
	Connection con=null;
	try{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=sunny@123");
	}catch(Exception e){System.out.println(e);}
	return con;
    }
}
