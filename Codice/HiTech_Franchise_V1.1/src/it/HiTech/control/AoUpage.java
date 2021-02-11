package it.HiTech.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AoUpage")
public class AoUpage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AoUpage() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role=(String)request.getSession().getAttribute("role");
		if(role==null) {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		else if(role.equals("9")||role.equals("1")){
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/userpage.jsp");
			dispatcher.forward(request, response);
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
