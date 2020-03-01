package org.company.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		PrintWriter pw=response.getWriter();
		
		request.getRequestDispatcher("header.html").include(request, response);
		
		

		request.getRequestDispatcher("link.html").include(request, response);
		
		HttpSession session=request.getSession(false);
		if(session==null){
			response.sendRedirect("login.html");
		}else{
			String email=(String)session.getAttribute("email");
			pw.print("<span style='float:right'>Hi, "+email+"</span>");
			pw.print("<h1>change password</h1>");
		}
		
		
		pw.print("<body>");
		
		
		pw.print("old password                   <input type='password' name='oldpass'><br><br>");
		
		pw.print("New password                   <input type='password' name='new'><br><br>");
		pw.print("confirm password        <input type='password' name='confirm'><br><br>");
		
		pw.print("<input type='submit' value='save changes'>");
		
		
		pw.print("</body>");
		
		request.getRequestDispatcher("footer.html").include(request, response);
		
		
		String oldpassword=request.getParameter("oldpass");
		String newPassword=request.getParameter("new");
		String confirmpassword=request.getParameter("comfirm");
		
		try
		{
			Connection con=ConnectionProvider.getConnection();
			PreparedStatement ps=con.prepareStatement("");
			
			
		}
		catch(Exception e)
		{
			pw.print(e);
		}
		
	}

		
		
		
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	doGet(request, response);
		
		
		
	}
}
