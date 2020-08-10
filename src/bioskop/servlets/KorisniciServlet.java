package bioskop.servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bioskop.dao.FilmDAO;
import bioskop.dao.KorisnikDAO;
import model.Film;
import model.Korisnik;

@SuppressWarnings("serial")
public class KorisniciServlet extends HttpServlet {
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	String loggedInUserName = (String) request.getSession().getAttribute("loggedInUserName");
//		if (loggedInUserName == null) {
//			request.getRequestDispatcher("./LogoutServlet").forward(request, response);
//			return;
//		}
		try {
//			Korisnik loggedInUser = KorisnikDAO.get(loggedInUserName);
//			if (loggedInUser == null) {
//				request.getRequestDispatcher("./LogoutServlet").forward(request, response);
//				return;
//			}
//			
			String username = request.getParameter("usernameFilter");
			username = (username != null? username: "");
			String datum = request.getParameter("datumFilter");
			datum = (datum != null? datum: "");
			String role = request.getParameter("roleFilter");
			role = (role != null? role: "");
	
			List<Korisnik> filteredKorisnici = KorisnikDAO.getAll(username, datum, role);
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("filteredKorisnici", filteredKorisnici);
	
			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
