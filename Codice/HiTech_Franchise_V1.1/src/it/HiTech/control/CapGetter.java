package it.HiTech.control;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import it.HiTech.model.CittaModelDM;

/**
 * Servlet implementation class CategoryGetter
 */
@WebServlet(name="CapGetter",urlPatterns="/CapGetter")
public class CapGetter extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public CapGetter() {
        super();

    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CittaModelDM model= new CittaModelDM();
		Collection<String> risultato= new LinkedList<String>();
		String inoltra="";
		try {
		
		String prov= request.getParameter("provincia");
				if(prov!=null)
					
				risultato=model.doRetrieveCap(prov);
		
		response.setContentType("html");
		PrintWriter out = response.getWriter();
		
		if(risultato != null && risultato.size() > 0) {
			
			Iterator<?> itb  = risultato.iterator();
			while(itb.hasNext()) {
				String ris = (String)itb.next();
				String inserisci="<option  value='"+ris+"' >"+ris+"</option>";
				
				inoltra=inoltra+inserisci;
				
			}
		}

		out.print(inoltra);
	}
	catch( Exception e) {
		System.out.println("Error: "+ e.getMessage());
		request.setAttribute("error", e.getMessage());	
	}
	
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

}
