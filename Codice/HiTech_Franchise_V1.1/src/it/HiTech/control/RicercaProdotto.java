package it.HiTech.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

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


@WebServlet("/RicercaProdotto")
public class RicercaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static ProdottoModelDM model = new ProdottoModelDM();
	static CategoriaModelDM model2=new CategoriaModelDM();
	
    public RicercaProdotto() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		@SuppressWarnings("unchecked")
		Carrello<ProdottoBean> cart = (Carrello<ProdottoBean>)request.getSession().getAttribute("carrello");
		if(cart == null) {
			cart = new Carrello<ProdottoBean>();
			request.getSession().setAttribute("carrello", cart);
		}
		
		String sort = request.getParameter("sort");
		
		String action = request.getParameter("action");
		
		try {
			if(action != null) {
				 if(action.equals("addCart")) {
					String id = request.getParameter("id");
					ProdottoBean bean = model.doRetrieveByKey(id);
					if(bean != null && !bean.isEmpty()) {
						cart.addItem(bean);
						request.setAttribute("message", "Product "+ bean.getNome()+" added to cart");
					}
				} else if(action.equals("clearCart")) {
					cart.deleteItems();
					request.setAttribute("message", "Cart cleaned");
				} else if(action.equals("deleteCart")) {
					String id = request.getParameter("id");
					ProdottoBean bean = model.doRetrieveByKey(id);
					if(bean != null && !bean.isEmpty()) {
						cart.deleteItem(bean);
						request.setAttribute("message", "Product "+ bean.getNome()+" deleted from cart");
					}
				} 
			}
		} catch(SQLException | NumberFormatException e) {
			System.out.println("Error: "+ e.getMessage());
			request.setAttribute("error", e.getMessage());			
		} 
		
		request.setAttribute("carrello", cart);
		
		try {
			request.removeAttribute("products");
			
			if((request.getParameter("category"))==null)
			request.setAttribute("products", model.doRetrieveAll(sort));
			else request.setAttribute("products", model.doRetrieveByCategory(request.getParameter("category")));
		} catch(SQLException e) {
			System.out.println("Error: "+ e.getMessage());
			request.setAttribute("error", e.getMessage());
			
		}
		@SuppressWarnings("unchecked")			
		Collection<CategoriaBean> categorie= (Collection<CategoriaBean>)request.getAttribute("catecorie");
		try {
				if(categorie==null)
				categorie=model2.doRetrieveAll();
		}
			catch(SQLException | NumberFormatException e) {
				System.out.println("Error: "+ e.getMessage());
				request.setAttribute("error", e.getMessage());	
			}
		request.setAttribute("categorie", categorie);
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

