package bioskop.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bioskop.dao.KorisnikDAO;
import bioskop.dao.ProjekcijaDAO;
import model.Korisnik;
import model.Projekcija;

@SuppressWarnings("serial")
public class ProjekcijeServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loggedInUserName = (String) request.getSession().getAttribute("loggedInUserName");
		if (loggedInUserName == null) {
			response.sendRedirect("./Login.html");
			request.getRequestDispatcher("./LogoutServlet").forward(request, response);
			return;
		}
		try {
			Korisnik loggedInUser = KorisnikDAO.get(loggedInUserName);
			if (loggedInUser == null) {
				request.getRequestDispatcher("./LogoutServlet").forward(request, response);
				return;
			}

			String filmFilter = request.getParameter("filmFilterInput");
			filmFilter = (filmFilter != null ? filmFilter : "");

			String tipProjekcijeFilter = request.getParameter("tipProjekcijeFilterInput");
			tipProjekcijeFilter = (tipProjekcijeFilter != null ? tipProjekcijeFilter : "");

			String salaFilter = request.getParameter("salaFilterInput");
			salaFilter = (salaFilter != null ? salaFilter : "");
			
			double cenaOdFilter = 0.0;
			try {
				String cenaOdFilterInput = request.getParameter("cenaOdFilterInput");
				cenaOdFilter = Double.parseDouble(cenaOdFilterInput);
				cenaOdFilter = (cenaOdFilter >= 0.0 ? cenaOdFilter : 0.0);
			} catch (Exception ex) {
			}

			double cenaDoFilter = Double.MAX_VALUE;
			try {
				String cenaDoFilterInput = request.getParameter("cenaDoFilterInput");
				cenaDoFilter = Double.parseDouble(cenaDoFilterInput);
				cenaDoFilter = (cenaDoFilter >= 0.0 ? cenaDoFilter : Double.MAX_VALUE);
			} catch (Exception ex) {
			}

			List<Projekcija> filteredProjekcije = ProjekcijaDAO.getAll(filmFilter, tipProjekcijeFilter, salaFilter, cenaOdFilter,
					cenaDoFilter);
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("filteredProjekcije", filteredProjekcije);
	
			request.setAttribute("data", data);
			System.out.println("data " +  data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);			
			
		} catch (Exception ex) {
			System.out.print("Greska!");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
