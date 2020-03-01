package org.company.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/SentServlet")
public class SentServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("header.html").include(request, response);
		request.getRequestDispatcher("link.html").include(request, response);
		
		HttpSession session=request.getSession(false);
		if(session==null){
			response.sendRedirect("login.html");
		}else{
			String email=(String)session.getAttribute("email");
			out.print("<span style='float:right'> "+email+"</span>");
			out.print("<h1>Sent Mail</h1>");
			
			try{
				Connection con=ConnectionProvider.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from comapny where sender=? and trash='no'  order by id desc  ");
				ps.setString(1,email);
				ResultSet rs=ps.executeQuery();
				out.print("<table border='1' style='width:700px;'>");
				out.print("<tr style='background-color:grey;color:white'><td>To</td></tr>");
				while(rs.next()){
					
					
			out.print("<tr><td><a href='ViewMailServlet?id="+rs.getString(1)+"'>"+rs.getString("reciever")+"</a></td></tr>");
				}
				out.print("</table>");
				
				con.close();
			}catch(Exception e){out.print(e);}
		}
		
		
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();

	}

}
