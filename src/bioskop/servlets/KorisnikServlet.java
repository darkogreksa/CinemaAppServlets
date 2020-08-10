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
public class KorisnikServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String loggedInUserName = (String) request.getSession().getAttribute("loggedInUserName");
//		if (loggedInUserName == null) {
////			response.sendRedirect("./Login.html");
//			request.getRequestDispatcher("./LogoutServlet").forward(request, response);
//			return;
//		}
		try {
//			Korisnik loggedInUser = KorisnikDAO.get(loggedInUserName);
//			if (loggedInUser == null) {
//				request.getRequestDispatcher("./LogoutServlet").forward(request, response);
//				return;
//			}
			
			String username = request.getParameter("username");
			Korisnik korisnik = KorisnikDAO.get(username);

			Map<String, Object> data = new LinkedHashMap<>();
			data.put("korisnik", korisnik);

			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
