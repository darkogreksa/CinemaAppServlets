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
public class FilmServlet extends HttpServlet {

	
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
			Film film = FilmDAO.get(id);

			Map<String, Object> data = new LinkedHashMap<>();
			data.put("film", film);

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
//		response.sendRedirect("./Login.html");
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
//			response.sendRedirect("./WebShop.html");
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
				glumci = (!"".equals(glumci)? glumci: "<prazni glumci>");

				String zanrovi = request.getParameter("zanrovi");
				zanrovi = (!"".equals(zanrovi)? zanrovi: "<prazni zanrovi>");

				int trajanje = Integer.parseInt(request.getParameter("trajanje"));
				trajanje = (trajanje > 0? trajanje: 999999999);
				
				String distributer = request.getParameter("distributer");
				distributer = (!"".equals(distributer)? distributer: "<prazan distributer>");

				String zemljaPorekla = request.getParameter("zemljaPorekla");
				zemljaPorekla = (!"".equals(zemljaPorekla)? zemljaPorekla: "<prazna zemljaPorekla>");

				Integer godinaProizvodnje = Integer.parseInt(request.getParameter("godinaProizvodnje"));
				godinaProizvodnje = (godinaProizvodnje > 0? godinaProizvodnje: 999999999);
				
				String opis = request.getParameter("opis");
				opis = (!"".equals(opis)? opis: "<prazan opis>");

				Film film = new Film(id, naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, false);
				FilmDAO.dodajFilm(film);
				break;
			}
			case "update": {
				int id = Integer.parseInt(request.getParameter("id"));
				Film film = FilmDAO.get(id);

				String naziv = request.getParameter("naziv");
				naziv = (!"".equals(naziv) ? naziv : film.getNaziv());

				String reziser = request.getParameter("reziser");
				reziser = (!"".equals(reziser) ? reziser : film.getReziser());

				String glumci = request.getParameter("glumci");
				glumci = (!"".equals(glumci) ? glumci : film.getGlumci());

				String zanrovi = request.getParameter("zanrovi");
				zanrovi = (!"".equals(zanrovi) ? zanrovi : film.getZanrovi());

				int trajanje = Integer.parseInt(request.getParameter("trajanje"));
				trajanje = (trajanje > 0 ? trajanje : film.getTrajanje());

				String distributer = request.getParameter("distributer");
				distributer = (!"".equals(distributer) ? distributer : film.getDistributer());

				String zemljaPorekla = request.getParameter("zemljaPorekla");
				zemljaPorekla = (!"".equals(zemljaPorekla) ? zemljaPorekla : film.getZemljaPorekla());

				int godinaProizvodnje = Integer.parseInt(request.getParameter("godinaProizvodnje"));
				godinaProizvodnje = (godinaProizvodnje > 0 ? godinaProizvodnje : film.getGodinaProizvodnje());

				String opis = request.getParameter("opis");
				opis = (!"".equals(opis) ? opis : film.getOpis());

				film.setNaziv(naziv);
				film.setReziser(reziser);
				film.setGlumci(glumci);
				film.setZanrovi(zanrovi);
				film.setTrajanje(trajanje);
				film.setDistributer(distributer);
				film.setZemljaPorekla(zemljaPorekla);
				film.setGodinaProizvodnje(godinaProizvodnje);
				film.setOpis(opis);
				film.setObrisan(false);
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
