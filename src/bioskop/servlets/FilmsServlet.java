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
			Korisnik loggedInUser = KorisnikDAO.get(loggedInUserName);
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
			String zanrovi = request.getParameter("zanrFilter");
			zanrovi = (zanrovi != null? zanrovi: "");
			String distributer = request.getParameter("distributerFilter");
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
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("filteredFilms", filteredFilms);
	
			request.setAttribute("loggedInUserRole", loggedInUser.getRole());
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
	

