package bioskop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import model.Film;
import model.Korisnik;
import model.Projekcija;
import model.Sala;
import model.TipProjekcije;

public class ProjekcijaDAO {
	public static SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public static List<Projekcija> getAll(String filmFilter, String tipProjekcijeFilter, String salaFilter, double cenaOdFilter, double cenaDoFilter) throws ParseException {

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<Projekcija> projekcije = new ArrayList<Projekcija>();

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDateTime = now.format(formatter);
		System.out.println(formatDateTime);
	
		try {
			String query = "SELECT projekcija.id, projekcija.cenaKarte, projekcija.vremePrikazivanja, projekcija.obrisan, film.id, film.naziv, tipProjekcije.id, tipProjekcije.naziv, sala.id, sala.naziv, korisnik.username\n" + 
					"FROM projekcija\n" + 
					"JOIN film ON film.id = projekcija.film_id\n" + 
					"JOIN tipProjekcije ON tipProjekcije.id = projekcija.tipProjekcije_id\n" + 
					"JOIN sala ON sala.id = projekcija.sala_id\n" + 
					"JOIN korisnik ON korisnik.username = projekcija.username\n" + 
					"WHERE projekcija.cenaKarte >= ? AND projekcija.cenaKarte <= ? AND film.naziv LIKE ?\n" + 
					"AND tipProjekcije.naziv LIKE ? AND sala.naziv LIKE ? AND projekcija.vremePrikazivanja >= '" + now + "' AND projekcija.obrisan = 0";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setDouble(index++, cenaOdFilter);
			pstmt.setDouble(index++, cenaDoFilter);
			pstmt.setString(index++, "%" + filmFilter + "%");
			pstmt.setString(index++, "%" + tipProjekcijeFilter + "%");
			pstmt.setString(index++, "%" + salaFilter + "%");
	
			System.out.println(pstmt);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				index = 1;
				Integer id = rset.getInt(index++);
				
				Double cenaKarte = rset.getDouble(index++);
				
				Date vreme = rset.getDate(index++);
				Timestamp vremePrikazivanja = new Timestamp(vreme.getTime());
				
				Integer obrisan = rset.getInt(index++);

				Integer idFilma = rset.getInt(index++);
				String nazivFilmaa = rset.getString(index++);
				Film film = new Film(idFilma, nazivFilmaa);
							
				Integer idTipaProjekcije = rset.getInt(index++);
				String nazivProjekcije = rset.getString(index++);				
				TipProjekcije tipProjekcijee = new TipProjekcije(idTipaProjekcije, nazivProjekcije);
				
				Integer idSale = rset.getInt(index++);
				String nazivSale = rset.getString(index++);
				Sala salaa = new Sala(idSale, nazivSale);
								
				String username = rset.getString(index++);
				Korisnik korisnik = new Korisnik(username);
						
				Projekcija projekcija = new Projekcija(id, film, tipProjekcijee, salaa, vremePrikazivanja, cenaKarte, korisnik, obrisan);
				projekcije.add(projekcija);
				
			}
			System.out.println(projekcije);
			return projekcije;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu");
			ex.printStackTrace();

		}finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return null;
	}

	public static boolean delete(int id) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE projekcija SET obrisan = 1 WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			System.out.println(pstmt);

			return pstmt.executeUpdate() == 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return false;
	}

	public static Projekcija get(int id) throws ParseException {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			//pisi velikim slovima querije! by dimi
			String query = "SELECT projekcija.id, projekcija.cenaKarte, projekcija.vremePrikazivanja, projekcija.obrisan, film.id, film.naziv, tipProjekcije.id, tipProjekcije.naziv, sala.id, sala.naziv, korisnik.username\n" + 
					"FROM projekcija\n" + 
					"JOIN film ON film.id = projekcija.film_id\n" + 
					"JOIN tipProjekcije ON tipProjekcije.id = projekcija.tipProjekcije_id\n" + 
					"JOIN sala ON sala.id = projekcija.sala_id\n" + 
					"JOIN korisnik ON korisnik.username = projekcija.username WHERE projekcija.id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				int index = 2;
				
				Integer idFilma = rset.getInt(index++);
				String nazivFilma = rset.getString(index++);
				Film film = new Film(idFilma, nazivFilma);
				
				Integer idTP = rset.getInt(index++);
				String nazivProjekcije = rset.getString(index++);				
				TipProjekcije tipProjekcije = new TipProjekcije(idTP, nazivProjekcije);
				
				Integer idSale = rset.getInt(index++);
				String nazivSale = rset.getString(index++);
				Sala sala = new Sala(idSale, nazivSale);
				
				Date vreme = rset.getDate(index++);
				Timestamp vremePrikazivanja = new Timestamp(vreme.getTime());
				
				Double cenaKarte = rset.getDouble(index++);
								
				String username = rset.getString(index++);
				Korisnik korisnik = new Korisnik(username);
				
				Integer obrisan = rset.getInt(index++);
						
				Projekcija projekcija = new Projekcija(id, film, tipProjekcije, sala, vremePrikazivanja, cenaKarte, korisnik, obrisan);
				return projekcija;
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return null;
	}
	
	public static boolean add(Projekcija projekcija) throws Exception {

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO projekcija (tipProjekcije_id, sala_id, vremePrikazivanja, cenaKarte, username, film_id)"
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);


			int index = 1;
			pstmt.setInt(index++, projekcija.getTipProjekcije().getId());
			pstmt.setInt(index++, projekcija.getSala().getId());
			pstmt.setTimestamp(index++, projekcija.getVremePrikazivanja());
			pstmt.setDouble(index++, projekcija.getCenaKarte());
			pstmt.setString(index++, projekcija.getAdministrator().getUsername());
			pstmt.setInt(index++, projekcija.getFilm().getId());
			
			System.out.println(pstmt);

			return pstmt.executeUpdate() == 1;

		} catch (Exception ex) {
			System.out.println("Greska SQL add");
			ex.printStackTrace();
		}finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}		
		return false;
	}
	
	public static int getProjekcijaId() {
		Connection conn = ConnectionManager.getConnection();
		int id = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT MAX(id) FROM projekcija";
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				id = rset.getInt(1);

			}
			id++;
			return id;
		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		}
		return 0;
	}
}
