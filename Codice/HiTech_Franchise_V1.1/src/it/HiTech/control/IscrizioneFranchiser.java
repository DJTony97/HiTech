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

@WebServlet("/IscrizioneFranchiser")
public class IscrizioneFranchiser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static UtenteModelDM model = new UtenteModelDM();
	
    public IscrizioneFranchiser() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			


		
		String nome=request.getParameter("name");
		String indirizzo=request.getParameter("Address");
		String cognome=request.getParameter("surname");
		String cf=request.getParameter("cf");
		String email=request.getParameter("email");
		String citta=request.getParameter("City");
		String cap=request.getParameter("Cap");
		String provincia=request.getParameter("Provincia");
		String regione=request.getParameter("Regione");
		String username=provincia+RandomPassword.generate();
		String pass=RandomPassword.generate();
		UtenteBean bean= new UtenteBean();
		bean.setUsername(username);
		bean.setCF(cf);
		bean.setPassword(pass);
		bean.setNome(nome);
		bean.setCognome(cognome);
		bean.setEmail(email);
		bean.setIndirizzo(indirizzo);
		bean.setCitta(citta);
		bean.setCAP(cap);
		bean.setProvincia(provincia);
		bean.setRegione(regione);
		try {
		model.doSave(bean);
		}
		catch(SQLException | NumberFormatException e) {
			System.out.println(e);
			request.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/register.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
