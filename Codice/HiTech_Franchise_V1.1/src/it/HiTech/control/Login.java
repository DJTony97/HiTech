package it.HiTech.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.HiTech.model.Carrello;
import it.HiTech.model.ProdottoBean;
import it.HiTech.model.UtenteBean;
import it.HiTech.model.UtenteModelDM;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static UtenteModelDM model = new UtenteModelDM();
    public Login() {
        super();
    }




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("userid");
		String password=request.getParameter("passid");
		@SuppressWarnings("unchecked")
		Carrello<ProdottoBean> cart = (Carrello<ProdottoBean>)request.getSession().getAttribute("carrello");
		if(cart == null) {
			cart = new Carrello<ProdottoBean>();
			request.getSession().setAttribute("carrello", cart);
			request.setAttribute("carrello", cart);					
		}
		if(username==null||password==null) {
			request.setAttribute("error", "0");
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		UtenteBean utent = null;
		int role;
		try {
			utent=model.doRetrieveByKey(username);
			
			role=checkLogin(username,password,utent);
			if(role==9) {
				request.getSession().setAttribute("role", "9");
				request.getSession().setAttribute("utente", utent);
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
				return;
			}
			else if(role==1) {
				request.getSession().setAttribute("role", "1");
				request.getSession().setAttribute("utente", utent);
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
				return;
				}	
					else {
						request.setAttribute("error", "0");
						RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login.jsp");
						dispatcher.forward(request, response);
						return;
						}
		}catch(SQLException | NumberFormatException e) {
			request.setAttribute("error", "0");
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		
		
		
		
	}
	
	private int checkLogin(String username, String password, UtenteBean utent)throws SQLException{
		if(utent==null)return 0;
		if(!password.equals(utent.getPassword()))return -1;
		if(utent.getType()==9) return 9;
		return 1;
		
	}

}
