package bioskop.servlets;

import java.io.IOException;
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
//					int id = ProjekcijaDAO.getProjekcijaId();
//					
//					String nazivFilma = request.getParameter("nazivFilma");
//					nazivFilma = (!"".equals(nazivFilma)? nazivFilma: "<prazan naziv filma>");
//					
//					String tipProjekcije = request.getParameter("tipProjekcije");
//					tipProjekcije = (!"".equals(tipProjekcije)? tipProjekcije: "<prazan tip projekcije>");
//
//					String sala = request.getParameter("sala");
//					sala = (!"".equals(sala)? sala: "<prazno polje sala>");
//
//					//FALI DATUM
//					
//					Double cena = Double.parseDouble(request.getParameter("trajanje"));
//					cena = (cena > 0? cena: 999999999.0);
//					
//					String korisnik = request.getParameter("korisnik");
//					korisnik = (!"".equals(korisnik)? korisnik: "<prazan korisnik>");
//
//					Film film = new Film(id, naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, obrisan);
//					FilmDAO.dodajFilm(film);
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
