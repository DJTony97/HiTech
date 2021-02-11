package it.HiTech.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.HiTech.model.ProdottoBean;
import it.HiTech.model.ProdottoModelDM;

@WebServlet(name= "prodcheck", urlPatterns = {"/prodcheck"})

public class prodcheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ProdottoModelDM model = new ProdottoModelDM();

    public prodcheck() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String code= request.getParameter("codeid");
		try {
			ProdottoBean bean=model.doRetrieveByKey(code);
			if(bean.getCodice().equals(code)) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.print("existent product");
				}
			else {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.print("non-existent product");
			}
			
		}catch(Exception e) {
			
		}
	}
}
