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

/**
 * Servlet implementation class ViewMailServlet
 */
@WebServlet("/ViewMailServlet")
public class ViewMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewMailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("header.html").include(request, response);
		request.getRequestDispatcher("link.html").include(request, response);
		
		HttpSession session=request.getSession(false);
		if(session==null){
			response.sendRedirect("index.html");
		}else{
			String email=(String)session.getAttribute("email");
			out.print("<span style='float:right'>"+email+"</span>");
			
			int id=Integer.parseInt(request.getParameter("id"));
			
			
			
			try{
				Connection con=ConnectionProvider.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from comapny where id=?");
				ps.setInt(1,id);
				ResultSet rs=ps.executeQuery();
				if(rs.next()){
					out.print("<p> <b> Subject  : </b>"+rs.getString("subject")+"<p>");
					out.print("<p><b>Message:</b><br/> "+rs.getString("message")+" <br/> <b>By: "+rs.getString("sender")+"</b></p>");
					out.print("<p><a href='DeleteMailServlet?id="+rs.getString(1)+"'>Delete Mail</a></p>");			
									
				}
				
				con.close();
			}catch(Exception e){out.print(e);}
		}
		
		
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();

		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
