package org.company.pkg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SettingandPrivacy
 */
@WebServlet("/SettingandPrivacy")
public class SettingandPrivacy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SettingandPrivacy() {
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
		
		RequestDispatcher rd=request.getRequestDispatcher("link.html");
		rd.include(request, response);
		
		HttpSession session=request.getSession(false);
		if(session==null){
			response.sendRedirect("login.html");
		}else{
			String email=(String)session.getAttribute("email");
			pw.print("<span style='float:right'> "+email+"</span>");
			pw.print("<h1> setting </h1>");
			
		
		}
		
		
		
		pw.print("<body>");
		pw.print("<a href='ChangePassword'>Change Password");
		pw.print("</body>");
		
		request.getRequestDispatcher("footer.html").include(request, response);
		
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
