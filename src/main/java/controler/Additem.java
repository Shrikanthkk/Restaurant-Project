package controler;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/additem")
@MultipartConfig

public class Additem extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String name= req.getParameter("name");
	double price=Double.parseDouble(req.getParameter("price"));
	int quantity=Integer.parseInt(req.getParameter("quantity"));
	String type=req.getParameter("type");
	
	byte[] picture=new byte[req.getPart("pic").getInputStream().available()];
	req.getPart("pic").getInputStream().read(picture);
}
}
