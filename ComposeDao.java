package org.company.pkg;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ComposeDao {

	public static int save(String sender,String reciever,String subject,String message){
		int status=0;
		try{
			Connection con=ConnectionProvider.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into comapny(sender,reciever,subject,message,trash,messagedate) values(?,?,?,?,?,?)");
			ps.setString(1,sender);
			ps.setString(2,reciever);
			ps.setString(3,subject);
			ps.setString(4,message);
			ps.setString(5,"no");
			ps.setDate(6,Formatter.getCurrentDate());
			
			status=ps.executeUpdate();
						
		}catch(Exception e){
			System.out.println(e);
			}
				
		return status;
	}
}
