package org.company.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class InboxServlet
 */
@WebServlet("/InboxServlet")
public class InboxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InboxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("header.html").include(request, response);
		request.getRequestDispatcher("link.html").include(request, response);
		
		HttpSession session=request.getSession(false);
		if(session==null){
			out.print("plz login first");
			//response.sendRedirect("login.html");
			
			//out.print("please login first");	
			request.getRequestDispatcher("login.html").forward(request, response);
			
			out.print("plz login first");
		}else{
			String email=(String)session.getAttribute("email");
			out.print("<span style='float:right'>"+email+"</span>");
			out.print("<h1>Inbox</h1>");
			
			String msg=(String)request.getAttribute("msg");
			if(msg!=null){
				out.print("<p>"+msg+"</p>");
			}
			
			try{
				Connection con=ConnectionProvider.getConnection();
				java.sql.PreparedStatement ps=con.prepareStatement("select * from comapny where reciever=? and trash='no'  order by id desc ");
				ps.setString(1,email);
				ResultSet rs=ps.executeQuery();
				out.print("<table border='1' style='width:700px;'>");
				out.print("<tr style='background-color:grey;color:white'><td>Sender</tr>");
				while(rs.next()){
					out.print("<tr><td><a href='ViewMailServlet?id="+rs.getString(1)+"'>"+rs.getString("sender")+"</a></td</tr>");
				
				///out.print("<tr><td><a href='ViewMailServlet?id="+"'>"+rs.getString("sender")+"</a></td></tr>");
				
				
				
				}
				out.print("</table>");
				
				con.close();
			}catch(Exception e){out.print(e);}
		}
		
		
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);

		
		
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	}

}
