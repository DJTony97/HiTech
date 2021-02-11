package it.HiTech.control;

import java.io.IOException;

import java.sql.SQLException;
import java.util.Collection;

import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import it.HiTech.model.UtenteBean;
import it.HiTech.model.UtenteModelDM;
/**
 * Servlet implementation class CategoryGetter
 */
@WebServlet("/RicercaPV")
public class RicercaPV extends HttpServlet {
	private static final long serialVersionUID = 1L;
       static UtenteModelDM model= new UtenteModelDM();
   /**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaPV() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
	
	
		
		try {
			Collection<UtenteBean> ritorno= new LinkedList<UtenteBean>();
			
			if((request.getParameter("regione"))==null)
			ritorno=model.doRetrieveAll();
			else if((request.getParameter("provincia"))==null)
				ritorno= model.doRetrieveByReg(request.getParameter("regione"));
			else ritorno= model.doRetrieveByProv(request.getParameter("provincia"));
			request.setAttribute("PV", ritorno);
		} catch(SQLException e) {
			System.out.println("Error: "+ e.getMessage());
			request.setAttribute("error", e.getMessage());
			
		}

	

		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}


