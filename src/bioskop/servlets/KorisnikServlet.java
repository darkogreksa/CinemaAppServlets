package bioskop.servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bioskop.dao.FilmDAO;
import bioskop.dao.KorisnikDAO;
import model.Film;
import model.Korisnik;
import model.Korisnik.Role;

@SuppressWarnings("serial")
public class KorisnikServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loggedInUserName = (String) request.getSession().getAttribute("loggedInUserName");
		if (loggedInUserName == null) {
//			response.sendRedirect("./Login.html");
			request.getRequestDispatcher("./LogoutServlet").forward(request, response);
			return;
		}
		try {
			Korisnik loggedInUser = KorisnikDAO.get(loggedInUserName);
			if (loggedInUser == null) {
				request.getRequestDispatcher("./LogoutServlet").forward(request, response);
				return;
			}
			
			String username = request.getParameter("username");
			Korisnik korisnik = KorisnikDAO.get(username);

			Map<String, Object> data = new LinkedHashMap<>();
			data.put("korisnik", korisnik);

			request.setAttribute("loggedInUserRole", loggedInUser.getRole());
			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loggedInUserName = (String) request.getSession().getAttribute("loggedInUserName");
		if (loggedInUserName == null) {
//			response.sendRedirect("./Login.html");
			request.getRequestDispatcher("./LogoutServlet").forward(request, response);
			return;
		}
		try {
			Korisnik loggedInUser = KorisnikDAO.get(loggedInUserName);
			if (loggedInUser == null) {
				request.getRequestDispatcher("./LogoutServlet").forward(request, response);
				return;
			}
			if (loggedInUser.getRole() != Role.ADMIN) {
//				response.sendRedirect("./WebShop.html");
				request.getRequestDispatcher("./UnauthorizedServlet").forward(request, response);
				return;
			}

			String action = request.getParameter("action");
			switch (action) {
				case "update": {
					String username = request.getParameter("username");
					Korisnik korisnik = KorisnikDAO.get(username);
					String newUsername = request.getParameter("newUsername");
					
					String role = request.getParameter("role");
					Role newRole = Role.valueOf(role);
					
					String password = request.getParameter("password");
					if ("".equals(password))
						throw new Exception("Prazna lozinka!");

					String repeatedPassword = request.getParameter("repeatedPassword");
					if (!password.equals(repeatedPassword))
						throw new Exception("Lozinke se ne podudaraju!");
					
					korisnik.setPassword(password);
					korisnik.setRole(newRole);
					KorisnikDAO.izmeniKorisnika(korisnik, newUsername);

					break;
				}
				case "delete": {
					String username = request.getParameter("username");
					KorisnikDAO.obrisiKorisnika(username);
				}
			}
			
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
			request.getRequestDispatcher("./FailureServlet").forward(request, response);
		}
	}

}
