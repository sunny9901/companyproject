package org.company.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Sendmail
 */
@WebServlet("/Sendmail")
public class Sendmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sendmail() {
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
		
		
		String host=request.getParameter("host");
		String port=request.getParameter("port");
		String email=request.getParameter("email");
		String sender=request.getParameter("sender");
		String reciever=request.getParameter("to");
		String subject=request.getParameter("subject");
		String message=request.getParameter("message");
		
		
		message=message.replaceAll("\n","<br/>");
		email=(String)request.getSession(false).getAttribute("email");		
			
		

		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		
		Session session =Session.getInstance(properties);
		
		Message msg=new MimeMessage(session);
		try {
		msg.setFrom(new InternetAddress(email,sender));
		
		InternetAddress[] toAddress= { new InternetAddress(reciever)};
		msg.setRecipients(Message.RecipientType.TO,toAddress);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setText(message);
		Transport.send(msg);
		
		
		
		}
		catch(Exception e)
		{
			pw.print(e);
		}
		
		
		int i=ComposeDao.save(email, reciever, subject, message);
		if(i>0){
			request.setAttribute("msg","message successfully sent");
			request.getRequestDispatcher("ComposeServlet").forward(request, response);
		}
		
		
		
		
		request.getRequestDispatcher("footer.html").include(request, response);
		pw.close();
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		PrintWriter pw=response.getWriter();
		request.getRequestDispatcher("header.html").include(request, response);
		
		
		String host=request.getParameter("host");
		String port=request.getParameter("port");
		String email=request.getParameter("email");
		String sender=request.getParameter("sender");
		String reciever=request.getParameter("to");
		String subject=request.getParameter("subject");
		String message=request.getParameter("message");
		
		
		message=message.replaceAll("\n","<br/>");
		email=(String)request.getSession(false).getAttribute("email");		
			
		

		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		
		Session session =Session.getInstance(properties);
		
		Message msg=new MimeMessage(session);
		try {
		msg.setFrom(new InternetAddress(email,sender));
		
		InternetAddress[] toAddress= { new InternetAddress(reciever)};
		msg.setRecipients(Message.RecipientType.TO,toAddress);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setText(message);
		Transport.send(msg);
		
		int i=ComposeDao.save(email, reciever, subject, message);
		if(i>0){
			request.setAttribute("msg","message successfully sent");
			request.getRequestDispatcher("composeform.html").forward(request, response);
		}
		
		}
		catch(Exception e)
		{
			pw.print(e);
		}
		
		
		request.getRequestDispatcher("footer.html").include(request, response);
		pw.close();
		
		
	}

}
