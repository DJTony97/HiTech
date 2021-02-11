package it.HiTech.control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.HiTech.model.UtenteBean;
import it.HiTech.model.UtenteModelDM;

@WebServlet("/Dimissioni")
public class Dimissioni extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	static UtenteModelDM model = new UtenteModelDM();

	
	
    public Dimissioni() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("role")==null) {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		String role=(String)request.getSession().getAttribute("role");
		if(!role.equals("1") && !role.equals("9")) {
			request.getSession().invalidate();
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		UtenteBean user=new UtenteBean();
		user=(UtenteBean) request.getSession().getAttribute("utente");


		try {
				model.doDelete(user);

		}
			catch(SQLException | NumberFormatException e) {
				System.out.println("Error: "+ e.getMessage());
				request.setAttribute("error", e.getMessage());	
			}
			

		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Logout");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
