package bioskop.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bioskop.dao.FilmDAO;
import bioskop.dao.KorisnikDAO;
import bioskop.dao.ProjekcijaDAO;
import model.Film;
import model.Korisnik;
import model.Projekcija;
import model.Sala;
import model.TipProjekcije;
import model.Korisnik.Role;

@SuppressWarnings("serial")
public class ProjekcijaServlet extends HttpServlet {
       
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
			
			int id = Integer.parseInt(request.getParameter("id"));
			Projekcija projekcija = ProjekcijaDAO.get(id);
			
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("projekcija", projekcija);
			
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
				request.getRequestDispatcher("./UnauthorizedServlet").forward(request, response);
				return;
			}

			String action = request.getParameter("action");
			switch (action) {
				case "add": {
//					int sala = Integer.parseInt(request.getParameter("sala"));
//					Sala salaa = new Sala(sala);
//
//					String dateString = request.getParameter("date");
//					String timeString = request.getParameter("time");
//					String datetimeString = dateString + " " + timeString;
//					System.out.println(datetimeString);
//					Timestamp datetime = new Timestamp(ProjekcijaDAO.DATETIME_FORMAT.parse(datetimeString).getTime());
//
//					String cenaKarte = request.getParameter("cenaKarte");
//					double cena = Double.parseDouble(cenaKarte);
//
//					int film = Integer.parseInt(request.getParameter("film"));
//					Film f = new Film(film);
//
//					int tip = Integer.parseInt(request.getParameter("tipProjekcije"));
//					TipProjekcije tipProjekcije = new TipProjekcije(tip);
//
//					Projekcija p = new Projekcija(f, tipProjekcije, salaa, datetime, cena, loggedInUser);
//					ProjekcijaDAO.add(p);
//					
//					break;
				}
				case "delete": {
					int id = Integer.parseInt(request.getParameter("id"));
					ProjekcijaDAO.delete(id);
					break;
				}
			}
			
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
			request.getRequestDispatcher("./FailureServlet").forward(request, response);
		}
	}

}
