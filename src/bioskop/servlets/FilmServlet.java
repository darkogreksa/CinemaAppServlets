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

@SuppressWarnings("serial")
public class FilmServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String loggedInUserName = (String) request.getSession().getAttribute("loggedInUserName");
//		if (loggedInUserName == null) {
////			response.sendRedirect("./Login.html");
//			request.getRequestDispatcher("./LogoutServlet").forward(request, response);
//			return;
//		}
		try {
//			Korisnik loggedInUser = KorisnikDAO.getOne(loggedInUserName);
//			if (loggedInUser == null) {
//				request.getRequestDispatcher("./LogoutServlet").forward(request, response);
//				return;
//			}
			
			int id = Integer.parseInt(request.getParameter("id"));
			Film film = FilmDAO.get(id);

			Map<String, Object> data = new LinkedHashMap<>();
			data.put("film", film);

			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			String action = request.getParameter("action");
			switch (action) {
				case "add": {
					int id = FilmDAO.getFilmId();
					String naziv = request.getParameter("naziv");
					naziv = (!"".equals(naziv)? naziv: "<prazan naziv>");
					String reziser = request.getParameter("reziser");
					reziser = (!"".equals(reziser)? reziser: "<prazno polje reziser>");
					String glumci = request.getParameter("glumci");
					glumci = (!"".equals(glumci)? glumci: "<prazno polje glumci>");
					String zanrovi = request.getParameter("zanrovi");
					zanrovi = (!"".equals(zanrovi)? zanrovi: "<prazno polje zanrovi>");
					int trajanje = Integer.parseInt(request.getParameter("trajanje"));
					trajanje = (trajanje > 0? trajanje: 99999999);
					String distributer = request.getParameter("distributer");
					distributer = (!"".equals(distributer)? distributer: "<prazno polje distributer>");
					String zemljaPorekla = request.getParameter("zemljaPorekla");
					zemljaPorekla = (!"".equals(zemljaPorekla)? zemljaPorekla: "<prazno polje zemljaPorekla>");
					int godinaProizvodnje = Integer.parseInt(request.getParameter("godinaProizvodnje"));
					godinaProizvodnje = (godinaProizvodnje > 0? godinaProizvodnje: 99999999);
					String opis = request.getParameter("opis");
					opis = (!"".equals(opis)? opis: "<prazno polje opis>");

					Film film = new Film(id, naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis);
					FilmDAO.dodajFilm(film);
					break;
				}
				case "update": {
					int id = Integer.parseInt(request.getParameter("id"));
					Film film = FilmDAO.get(id);

					String naziv = request.getParameter("naziv");
					naziv = (!"".equals(naziv)? naziv: film.getNaziv());
					String reziser = request.getParameter("reziser");
					reziser = (!"".equals(reziser)? reziser: film.getReziser());
					String glumci = request.getParameter("glumci");
					glumci = (!"".equals(glumci)? glumci: film.getGlumci());
					String zanrovi = request.getParameter("zanrovi");
					zanrovi = (!"".equals(zanrovi)? zanrovi: film.getZanrovi());
					int trajanje = Integer.parseInt(request.getParameter("trajanje"));
					trajanje = (trajanje > 0? trajanje: film.getTrajanje());
					String distributer = request.getParameter("distributer");
					distributer = (!"".equals(distributer)? distributer: film.getDistributer());
					String zemljaPorekla = request.getParameter("zemljaPorekla");
					zemljaPorekla = (!"".equals(zemljaPorekla)? zemljaPorekla: film.getZemljaPorekla());
					int godinaProizvodnje = Integer.parseInt(request.getParameter("godinaProizvodnje"));
					godinaProizvodnje = (godinaProizvodnje > 0? godinaProizvodnje: film.getGodinaProizvodnje());
					String opis = request.getParameter("opis");
					opis = (!"".equals(opis)? opis: film.getOpis());

					film.setNaziv(naziv);
					film.setReziser(reziser);
					film.setGlumci(glumci);
					film.setZanrovi(zanrovi);
					film.setTrajanje(trajanje);
					film.setDistributer(distributer);
					film.setZemljaPorekla(zemljaPorekla);
					film.setGodinaProizvodnje(godinaProizvodnje);
					film.setOpis(opis);
					FilmDAO.izmeniFilm(film);
					break;
				}
				case "delete": {
					int id = Integer.parseInt(request.getParameter("id"));
					FilmDAO.obrisiFilm(id);
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
