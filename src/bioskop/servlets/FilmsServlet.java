package bioskop.servlets;

import java.io.IOException;
import java.util.List;

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
public class FilmsServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loggedInUserName = (String) request.getSession().getAttribute("loggedInUserName");
		if (loggedInUserName == null) {
//			response.sendRedirect("./Login.html");
			request.getRequestDispatcher("./LogoutServlet").forward(request, response);
			return;
		}
		try {
			Korisnik loggedInUser = KorisnikDAO.getOne(loggedInUserName);
			if (loggedInUser == null) {
				request.getRequestDispatcher("./LogoutServlet").forward(request, response);
				return;
			}
			
			String naziv = request.getParameter("nazivFilter");
			naziv = (naziv != null? naziv: "");
			int trajanjeOd = 0;
			try {
				String trajanjeOdFilter = request.getParameter("trajanjeOdFilter");
				trajanjeOd = Integer.parseInt(trajanjeOdFilter);
				trajanjeOd = (trajanjeOd >= 0? trajanjeOd: 0);
			} catch (Exception ex) {}

			int trajanjeDo = Integer.MAX_VALUE;
			try {
				String trajanjeDoFilter = request.getParameter("trajanjeDoFilter");
				trajanjeDo = Integer.parseInt(trajanjeDoFilter);
				trajanjeDo = (trajanjeDo >= 0? trajanjeDo: Integer.MAX_VALUE);
			} catch (Exception ex) {}
			String zanrovi = request.getParameter("zanroviFilter");
			zanrovi = (zanrovi != null? zanrovi: "");
			String distributer = request.getParameter("disributerFilter");
			distributer = (distributer != null? distributer: "");
			String zemljaPorekla = request.getParameter("zemljaPoreklaFilter");
			zemljaPorekla = (zemljaPorekla != null? zemljaPorekla: "");
			int godinaProizvodnjeOd = 0;
			try {
				String godinaProizvodnjeOdFilter = request.getParameter("godinaProizvodnjeOdFilter");
				godinaProizvodnjeOd = Integer.parseInt(godinaProizvodnjeOdFilter);
				godinaProizvodnjeOd = (godinaProizvodnjeOd >= 0? godinaProizvodnjeOd: 0);
			} catch (Exception ex) {}

			int godinaProizvodnjeDo = Integer.MAX_VALUE;
			try {
				String godinaProizvodnjeDoFilter = request.getParameter("godinaProizvodnjeDoFilter");
				godinaProizvodnjeDo = Integer.parseInt(godinaProizvodnjeDoFilter);
				godinaProizvodnjeDo = (godinaProizvodnjeDo >= 0? godinaProizvodnjeDo: Integer.MAX_VALUE);
			} catch (Exception ex) {}
			
			List<Film> filteredFilms = FilmDAO.getAll(naziv, zanrovi, trajanjeOd, trajanjeDo, distributer, zemljaPorekla, godinaProizvodnjeOd, godinaProizvodnjeDo);
		} catch (Exception ex) {}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		/*String ulogovan = (String) request.getSession().getAttribute("ulogovan");
		if(ulogovan == null) {
			request.getRequestDispatcher("./LogoutServlet").forward(request, response);
			return;
		}
		try {
			Korisnik ulogovanKorisnik = KorisnikDAO.getOne(ulogovan);
			if(ulogovanKorisnik == null) {
				request.getRequestDispatcher("./LogoutServlet").forward(request, response);
				return;
			}
			if(ulogovanKorisnik.getRole() != Role.ADMIN) {
				request.getRequestDispatcher("./UnauthorizedServlet").forward(request, response);
				return;
			}
			
			String action = request.getParameter("action");
			switch (action) {
				case "add": {
					int id = FilmDAO.getFilmId();
					
					String naziv = request.getParameter("naziv");
					naziv = (!"".equals(naziv)? naziv: "<prazan naziv>");
					
					String reziser = request.getParameter("reziser");
					reziser = (!"".equals(reziser)? reziser: "<prazan reziser>");
					
					String glumci = request.getParameter("glumci");
					glumci = (!"".equals(glumci)? glumci: "<prazan glumci>");
					
					String zanrovi = request.getParameter("zanrovi");
					zanrovi = (!"".equals(zanrovi)? zanrovi: "<prazan zanr>");
					
					int trajanje = Integer.parseInt(request.getParameter("trajanje"));
					trajanje = (int) (trajanje > 0? trajanje: 999999999);
					
					String distributer = request.getParameter("distributer");
					distributer = (!"".equals(distributer)? distributer: "<prazan distributer>");
					
					String zemljaPorekla = request.getParameter("zemljaPorekla");
					zemljaPorekla = (!"".equals(zemljaPorekla)? zemljaPorekla: "<prazna zemlja porekla>");
					
					int godinaProizvodnje = Integer.parseInt(request.getParameter("godinaProizvodnje"));
					godinaProizvodnje = (int) (godinaProizvodnje > 0? godinaProizvodnje: 999999999);
					
					String opis = request.getParameter("opis");
					opis = (!"".equals(opis)? opis: "<prazan opis>");
					
					Film film = new Film(id, naziv, reziser, glumci, zanrovi, trajanje, distributer,
							zemljaPorekla, godinaProizvodnje, opis);
					FilmDAO.dodajFilm(film);
					break;
					
				}
				case "update": {
					int id = Integer.parseInt(request.getParameter("Id"));
					Film film = FilmDAO.getId(id);
					
					String naziv = request.getParameter("naziv");
					naziv = (!"".equals(naziv)? naziv: film.getNaziv());
					
					String reziser = request.getParameter("reziser");
					reziser = (!"".equals(reziser)? reziser: film.getReziser());
					
					String glumci = request.getParameter("glumci");
					glumci = (!"".equals(glumci)? glumci: film.getGlumci());
					
					String zanrovi = request.getParameter("zanrovi");
					zanrovi = (!"".equals(zanrovi)? zanrovi: film.getZanrovi());
					
					int trajanje = Integer.parseInt(request.getParameter("trajanje"));
					trajanje = (int) (trajanje > 0? trajanje: film.getTrajanje());
					
					String distributer = request.getParameter("distributer");
					distributer = (!"".equals(distributer)? distributer: film.getDistributer());
					
					String zemljaPorekla = request.getParameter("zemljaPorekla");
					zemljaPorekla = (!"".equals(zemljaPorekla)? zemljaPorekla: film.getZemljaPorekla());
					
					int godinaProizvodnje = Integer.parseInt(request.getParameter("godinaProizvodnje"));
					godinaProizvodnje = (int) (godinaProizvodnje > 0? godinaProizvodnje: film.getGodinaProizvodnje());
					
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
					int id = Integer.parseInt(request.getParameter("Id"));
					FilmDAO.obrisiFilm(id);
					break;
				}
			}
	} catch (Exception ex) {
		ex.printStackTrace();
	}*/

	}
}
