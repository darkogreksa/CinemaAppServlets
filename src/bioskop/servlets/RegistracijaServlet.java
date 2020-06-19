package bioskop.servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bioskop.dao.KorisnikDAO;
import model.Korisnik;

@SuppressWarnings("serial")
public class RegistracijaServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			if (KorisnikDAO.get(username) != null)
				throw new Exception("Korisnicko ime vec postoji!");
			if ("".equals(username))
				throw new Exception("Korisnicko ime je prazno!");

			String password = request.getParameter("password");
			if ("".equals(password))
				throw new Exception("Lozinka je prazna!");
			
			Korisnik korisnik = new Korisnik(username, password, model.Korisnik.Role.USER);
			KorisnikDAO.dodajKorisnika(korisnik);

//			response.sendRedirect("./Login.html");
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		} catch (Exception ex) {
			String message = ex.getMessage();
			if (message == null) {
				message = "Nepredvidjena greska!";
				ex.printStackTrace();
			}

//			request.setAttribute("message", message);
//			request.getRequestDispatcher("./Message.jsp").forward(request, response);
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("message", message);

			request.setAttribute("data", data);
			request.getRequestDispatcher("./FailureServlet").forward(request, response);
		}
	}

}
