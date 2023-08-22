package controler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Customer;
import ddo.Mydao;

@WebServlet("/login")
public class customerlogin extends HttpServlet {

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String email=req.getParameter("email");
	String pass=req.getParameter("pass");
	Mydao dao=new Mydao();
	if(email.equals("admin@jsp.com") && pass.equals("admin")) {
		resp.getWriter().print("<h1>Login success</h1>");
		req.getRequestDispatcher("Adminhome.html").include(req, resp);
		}
	else{
	Customer customer=dao.fetchByEmail(email);
	
	if(customer==null) {
		resp.getWriter().print("<h1>Invalid Email</h1>");
		req.getRequestDispatcher("login.html").include(req, resp);
		
	}
	else {
		if(pass.equals(customer.getPassword())) {
			resp.getWriter().print("<h1>LOgin success</h1>");
			req.getRequestDispatcher("customerhome.html").include(req, resp);
			
		}else {
			resp.getWriter().print("<h1>Invalid password</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		}
	}
	}
}
}
