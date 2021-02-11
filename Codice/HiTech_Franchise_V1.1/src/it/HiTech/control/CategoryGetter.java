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

import it.HiTech.model.CategoriaBean;
import it.HiTech.model.CategoriaModelDM;

/**
 * Servlet implementation class CategoryGetter
 */
@WebServlet("/CategoryGetter")
public class CategoryGetter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       static CategoriaModelDM model= new CategoriaModelDM();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryGetter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page=request.getParameter("page");
		@SuppressWarnings("unchecked")
		Collection<CategoriaBean> categorie= (Collection<CategoriaBean>)request.getAttribute("categorie");
		try {
				if(categorie==null)
				categorie=model.doRetrieveAll();
		}
			catch(SQLException | NumberFormatException e) {
				System.out.println("Error: "+ e.getMessage());
				request.setAttribute("error", e.getMessage());	
			}
		request.setAttribute("categorie", categorie);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
