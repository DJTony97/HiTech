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

@WebServlet(name= "usernamecheck", urlPatterns = {"/usernamecheck"})

public class usernamecheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static UtenteModelDM model = new UtenteModelDM();

    public usernamecheck() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userid= request.getParameter("myuser");
		try {
			UtenteBean bean=model.doRetrieveByKey(userid);
			if(bean.getUsername().equals(userid)) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.print("Already Exists");
				}else {
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.print("Avaible");
				}
			
		}catch(Exception e) {
			
		}
	}
}
