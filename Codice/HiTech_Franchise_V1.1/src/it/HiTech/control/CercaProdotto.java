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

import it.HiTech.model.Carrello;
import it.HiTech.model.CategoriaBean;
import it.HiTech.model.CategoriaModelDM;
import it.HiTech.model.ProdottoBean;
import it.HiTech.model.ProdottoModelDM;


@WebServlet("/CercaProdotto")
public class CercaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static ProdottoModelDM model = new ProdottoModelDM();
	static CategoriaModelDM model2=new CategoriaModelDM();
	
    public CercaProdotto() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		String code=request.getParameter("code");
		String sort = request.getParameter("sort");
		@SuppressWarnings("unchecked")
		Carrello<ProdottoBean> cart = (Carrello<ProdottoBean>)request.getSession().getAttribute("carrello");
		if(cart == null) {
			cart = new Carrello<ProdottoBean>();
			request.getSession().setAttribute("carrello", cart);
		}

	
	
		Collection<ProdottoBean> prodotti= new LinkedList<ProdottoBean>();	
		
		try {
			if(code==null) {
				request.removeAttribute("products");
						
				if((request.getParameter("category"))==null)
				prodotti= model.doRetrieveAll(sort);
				else prodotti=model.doRetrieveByCategory(request.getParameter("category"));
				
			}else {
			ProdottoBean product = new ProdottoBean();
			product=model.doRetrieveByKey(code);
			prodotti.add(product);}
			request.setAttribute("products",prodotti);
		} catch(SQLException e) {
			System.out.println("Error: "+ e.getMessage());
			request.setAttribute("error", e.getMessage());
			
		}
	
	
		Collection<CategoriaBean> categorie= new LinkedList<CategoriaBean>();
		try {
				categorie=model2.doRetrieveAll();
		}
			catch(SQLException | NumberFormatException e) {
				System.out.println("Error: "+ e.getMessage());
				request.setAttribute("error", e.getMessage());	
			}
		request.setAttribute("categorie", categorie);
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/catalogo.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

