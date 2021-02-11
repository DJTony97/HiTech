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

import it.HiTech.model.CategoriaModelDM;
import it.HiTech.model.ProdottoBean;
import it.HiTech.model.ProdottoModelDM;

//import it.HiTech.model.UtenteBean;

@WebServlet(name="AcquistaProdotto",urlPatterns="/AcquistaProdotto")
public class AcquistaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	static ProdottoModelDM model = new ProdottoModelDM();

	static CategoriaModelDM model3 = new CategoriaModelDM();
	
    public AcquistaProdotto() {
        super();
    }
/*
	@SuppressWarnings("deprecation")*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code=request.getParameter("code");
		String action = request.getParameter("action");
		@SuppressWarnings("unchecked")
		Carrello<ProdottoBean> cart = (Carrello<ProdottoBean>)request.getSession().getAttribute("carrello");
		if(cart == null) {
			cart = new Carrello<ProdottoBean>();
			request.getSession().setAttribute("carrello", cart);	
			request.setAttribute("carrello", cart);
		}

		int indice;
		String attributo= (String) request.getParameter(code);
		if(attributo==null) {
			indice=0;
			}
		else { indice=Integer.parseInt(attributo);}

		if(code.length()==8) {
			code="0".concat(code);
			 }
	
		

		ProdottoBean prodotto= (ProdottoBean)request.getAttribute("product");
		try {
			if(prodotto==null) {
				prodotto=model.doRetrieveByKey(code);
			}
			if(action != null && action.equals("addCart")) {
					if(prodotto != null && !prodotto.isEmpty()) {

						cart.addItem(prodotto);
						request.getSession().setAttribute("product", prodotto);
						request.setAttribute("message", "Product "+ prodotto.getNome()+" added to cart");
						indice++;

						
					}
		}
		}catch(SQLException | NumberFormatException e) {
				System.out.println("Error: "+ e.getMessage());
				request.setAttribute("error", e.getMessage());	
			}
		
		request.setAttribute("product", prodotto);
		request.setAttribute(code,indice );

		

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/prodotto.jsp?code="+code+"&"+code+"="+indice);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
