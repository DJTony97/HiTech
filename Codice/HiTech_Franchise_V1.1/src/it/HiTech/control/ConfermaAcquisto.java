package it.HiTech.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.HiTech.model.Carrello;
import it.HiTech.model.ContenutoModelDM;
import it.HiTech.model.OrdineModelDM;
import it.HiTech.model.ProdottoBean;
import it.HiTech.model.ContenutoBean;
import it.HiTech.model.OrdineBean;
import it.HiTech.model.UtenteBean;
import it.HiTech.model.UtenteModelDM;
import it.HiTech.model.ProdottoModelDM;



@WebServlet("/ConfermaAcquisto")
public class ConfermaAcquisto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ProdottoModelDM model = new ProdottoModelDM();
	static ContenutoModelDM model2 = new ContenutoModelDM();
	static OrdineModelDM model3=new OrdineModelDM();
	static UtenteModelDM model4=new UtenteModelDM();

	
    public ConfermaAcquisto() {
    	
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteBean user = (UtenteBean) request.getSession().getAttribute("utente");
		if(request.getSession().getAttribute("role")==null) {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		@SuppressWarnings("unchecked")
		Carrello<ProdottoBean> cart = (Carrello<ProdottoBean>)request.getSession().getAttribute("carrello");
		if(cart == null) {
			cart = new Carrello<ProdottoBean>();
			request.getSession().setAttribute("carrello", cart);
		}
		
		String action = request.getParameter("action");
		ProdottoBean product= (ProdottoBean)request.getAttribute("product");  

		try {
			if(action != null) {
				if(action.equals("clearCart")) {
					cart.deleteItems();
					request.setAttribute("message", "Cart cleaned");
				} else if(action.equals("deleteCart")) {

						cart.deleteItem(Integer.parseInt(request.getParameter("choice"))-1);
						
				} else if(action.equals("OrderNow")) {
					 
					
					OrdineBean order= new OrdineBean();
					order.setDataAuto();
					order.setIndirizzo((String)request.getAttribute("Address"));
					order.setCap((String)request.getAttribute("Cap"));
					order.setCitta((String)request.getAttribute("City"));
					order.setProvincia((String)request.getAttribute("Provincia"));
					order.setRegione((String)request.getAttribute("Regione"));
					order.setMetodo_Spedizione((String)request.getAttribute("MSpedizione"));
					order.setMetodoPagamento((String)request.getAttribute("MPagamento"));
					order.setRifCliente((String)request.getAttribute("RCliente"));
					order.setUtente_Username(user.getUsername());
					model3.doSave(order);
					
					
					List<ProdottoBean> prodList= cart.getItems();	
					Iterator<?> it  =prodList.iterator();
					while(it.hasNext()){
						ProdottoBean prod= (ProdottoBean) it.next();
						ContenutoBean content= (ContenutoBean) model2.doRetrieveByKey(prod.getCodice(), model3.doRetriveLastID(user.getUsername()), user.getUsername());
						if (content.getOrdine_Codice()==0&&content.getProdotto_Codice().equals("")&&content.getQuantita()==1) {
							content =new ContenutoBean();
							content.setOrdine_Codice( model3.doRetriveLastID(user.getUsername()));
							content.setProdotto_Codice(prod.getCodice());
							content.setQuantita(1);
							content.setUtente(user.getUsername());
							model.RiduciScorte(prod, 1);
							model2.doSave(content);
						}
						else {
							model2.doUpdate(content);
							model.RiduciScorte(prod, 1);
						}
					}
					
						
					cart.deleteItems();
					request.setAttribute("message", "Order Completed");
			} 
			}
		} catch(SQLException | NumberFormatException e) {
			System.out.println("Error: "+ e.getMessage());
			request.setAttribute("error", e.getMessage());			
		} 
		request.setAttribute("product", product);
		request.getSession().setAttribute("carrello", cart);
	
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/carrello.jsp");
		dispatcher.forward(request, response);
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
