package it.HiTech.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.HiTech.model.UtenteBean;
import it.HiTech.model.UtenteModelDM;

@WebServlet(name= "emailcheck", urlPatterns = {"/emailcheck"})

public class emailcheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static UtenteModelDM model = new UtenteModelDM();

    public emailcheck() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email= request.getParameter("myemail");
		try {
			UtenteBean bean=model.doRetrieveByEmail(email);
			if(bean.getEmail().equals(email)) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.print("Already Exists");
				}
			
		}catch(Exception e) {
			
		}
	}
}
