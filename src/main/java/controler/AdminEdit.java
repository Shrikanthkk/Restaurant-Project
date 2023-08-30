package controler;

import java.io.IOException;

import dao.Items;
import ddo.Mydao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class AdminEdit extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		Mydao dao=new Mydao();
		//Finding object because remove method accepts object
		Items item=dao.find(id);
		
		req.setAttribute("item", item);
		req.getRequestDispatcher("Edit.jsp").forward(req, resp);
		
		}

}
