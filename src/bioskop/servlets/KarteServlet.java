package bioskop.servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bioskop.dao.KartaDAO;
import model.Karta;

@SuppressWarnings("serial")
public class KarteServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Karta> filteredKarte= KartaDAO.getAll();
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("filteredKarte", filteredKarte);
	
			request.setAttribute("data", data);
			System.out.println("data " +  data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);			
			
		} catch (Exception e) {
			System.out.print("Greska!");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
