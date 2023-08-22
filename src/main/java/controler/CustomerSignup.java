package controler;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import dao.Customer;
import ddo.Mydao;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
//This is to map the action to this class(should be same as action and this is case sensitive)

@WebServlet("/signup")
@MultipartConfig
//This is to make the class as Servlet class
public class CustomerSignup extends HttpServlet {
	@Override
	// When there is form and we get data to be secured so we will go for doPost
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Receive values from Front End
		String user = req.getParameter("user");
		String pass = req.getParameter("password");
		String email = req.getParameter("email");
		long phno = Long.parseLong(req.getParameter("pon"));
		LocalDate dob = LocalDate.parse(req.getParameter("dob"));
		String gender = req.getParameter("gen");
		String pro = req.getParameter("qual");
		int age = Period.between(dob, LocalDate.now()).getYears();

		Part pic = req.getPart("picture");
		byte[] picture = null;
		picture = new byte[pic.getInputStream().available()];
		pic.getInputStream().read(picture);

		Mydao dao=new Mydao();
		

		if(dao.fetchByEmail(email)== null && dao.fetchbyMobile(phno)==null) {
		Customer customer = new Customer();
		customer.setUsername(user);
		customer.setPassword(pass);
		customer.setEmail(email);
		customer.setPhonenumber(phno);
		customer.setDob(dob);
		customer.setGender(gender);
		customer.setCountry(pro);
		customer.setAge(age);
		customer.setPicture(picture);


		dao.save(customer);
		resp.getWriter().print("<h1 style='colour:green'>Account create Successfully </h1>");
		req.getRequestDispatcher("login.html").include(req, resp);
		}else {
			resp.getWriter().print("<h1 style='colour:red'>Email ID and Mobile number already exists </h1>");
			req.getRequestDispatcher("signup.html").include(req, resp);
		}
		}
		
	}

