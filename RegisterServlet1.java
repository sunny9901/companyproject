package org.company.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet1
 */
@WebServlet("/RegisterServlet1")
public class RegisterServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	doGet(request, response);
		

		PrintWriter pw=response.getWriter();
		
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String gender=request.getParameter("gender");
		String dob=request.getParameter("dob");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String rpassword=request.getParameter("rpwd");
		String contact=request.getParameter("contact");
		
		java.sql.Date sqlDOB=Formatter.getSqlDate(dob);
		
		
		if(CheckDao.inValid(email))
		{
			pw.print("email from this name is already registered ! please select different email name ");
		request.getRequestDispatcher("index.html").include(request, response);
		} else {
		if(name.isEmpty() || password.isEmpty() || email.isEmpty() ||
				gender.isEmpty() || dob.isEmpty() || city.isEmpty() ||
				state.isEmpty() || rpassword.isEmpty() || contact.isEmpty())
		{
			pw.print("please fill all the fields");	
			request.getRequestDispatcher("index.html").include(request, response);
		}
		else
		{
			try {
					Connection con=ConnectionProvider.getConnection();
					PreparedStatement ps=con.prepareStatement("insert into company(name,email,password,gender,dob,city,state,contact,rpassword,authorized,registerdate) values(?,?,?,?,?,?,?,?,?,?,?)");
			
					if(password.equals(rpassword)) 
					{	
					
								ps.setString(1,name);
								ps.setString(2,email+"@tit.com");
								ps.setString(3,password);
								ps.setString(4,gender);
								ps.setDate(5,sqlDOB);
								ps.setString(6,city);
								ps.setString(7,state);
								ps.setString(8,contact);
								ps.setString(9,rpassword);
								ps.setDate(11,Formatter.getCurrentDate());
								ps.setString(10,"yes");
								ps.executeUpdate();
								pw.print("you are successfully registered");
								RequestDispatcher rd=request.getRequestDispatcher("login.html");
								rd.include(request, response);
							
						}
						
						else {
								pw.print("password not match");
				
								RequestDispatcher rd=request.getRequestDispatcher("index.html");
								rd.include(request, response);
					
							}
					
				
				
						//con.commit();
						//con.close();
						
				}
			
			catch(Exception e)
			{
				System.out.println(e);
			}
			
		}
			pw.close();
		
		
		}
	}		
	}

		

