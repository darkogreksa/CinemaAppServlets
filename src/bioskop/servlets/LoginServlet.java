package bioskop.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import bioskop.dao.KorisnikDAO;
import model.Korisnik;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			Korisnik korisnik = KorisnikDAO.get(username, password);
			if (korisnik == null) {
//				response.sendRedirect("./Login.html");
				request.getRequestDispatcher("./FailureServlet").forward(request, response);
				return;
			}
	
			request.getSession().setAttribute("loggedInUserName", korisnik.getUsername());
			request.getSession().setAttribute("loggedInRegisterDate", korisnik.getDatumRegistracije());
			request.getSession().setAttribute("loggedInUserRole", korisnik.getRole());
			
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
