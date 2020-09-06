package bioskop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Film;
import model.Karta;
import model.Korisnik;
import model.Projekcija;
import model.Sala;
import model.Sediste;
import model.TipProjekcije;

public class KartaDAO {
	public static SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static List<Karta> getAll() {
		List<Karta> karte = new ArrayList<>();
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "SELECT film.id, film.naziv, projekcija.id, projekcija.vremePrikazivanja, tipProjekcije.naziv, sala.naziv, karta.id, karta.sediste_redniBroj, karta.username\r\n" + 
					"FROM karta\r\n" + 
					"LEFT JOIN projekcija ON projekcija.id = karta.projekcija_id\r\n" + 
					"LEFT JOIN film ON film.id = projekcija.film_id\r\n" + 
					"LEFT JOIN tipProjekcije ON tipProjekcije.id = projekcija.tipProjekcije_id\r\n" + 
					"LEFT JOIN sala ON sala.id = projekcija.sala_id ";

			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			System.out.println(pstmt);
			while (rset.next()) {
				int index = 1;

				int idFilma = rset.getInt(index++);
				String nazivFilma = rset.getString(index++);
				Film film = new Film(idFilma, nazivFilma);
				
				int idd = rset.getInt(index++);
				
				Date vremePrikazivanjaa = rset.getDate(index++);
				String vremePrikazivanja = dateToString(vremePrikazivanjaa);			
				
				String tipProjekcijeNaziv = rset.getString(index++);
				TipProjekcije tipProjekcije = new TipProjekcije(tipProjekcijeNaziv);
				
				String salaNaziv = rset.getString(index++);	
				Sala sala = new Sala(salaNaziv);	
				
				int kartaId = rset.getInt(index++);
				int sediste = rset.getInt(index++);
				Sediste sedistee = new Sediste(sediste);
				
				String korisnik = rset.getString(index++);
				Korisnik korisnikk = new Korisnik(korisnik);
				
				Projekcija projekcija = new Projekcija(idd, film, tipProjekcije, sala, vremePrikazivanja);
						
				Karta karta = new Karta(kartaId, projekcija, sedistee, korisnikk);
				karte.add(karta);

			}
			return karte;
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
	
	public static List<Karta> getKarteByProjekcija(int id) {
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Karta> karte = new ArrayList<Karta>();
		
		try {
			String query = "SELECT karta.id, karta.sediste_redniBroj, karta.vremeProdaje, karta.username, projekcija.id\r\n" + 
					"FROM karta\r\n" + 
					"JOIN projekcija ON projekcija.id = karta.projekcija_id\r\n" + 
					"WHERE projekcija.id = ?";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setInt(1, id);
			rset = pstmt.executeQuery();

			while(rset.next()) {
				index = 1;
				int idKarte = rset.getInt(index++);
				
				int sediste = rset.getInt(index++);
				Sediste sedistee = new Sediste(sediste);
				
				Date datumKarte = rset.getDate(index++);
				String kartaDatum = dateToString(datumKarte);
				
				String username = rset.getString(index++);
				Korisnik korisnik = new Korisnik(username);
				
				Integer idProjekcije = rset.getInt(index++);
				Projekcija projekcijaa = new Projekcija(idProjekcije);
				
				Karta karta =  new Karta(idKarte, projekcijaa, sedistee, kartaDatum, korisnik);
				karte.add(karta);
			}
			return karte;
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

	public static Karta get(int id) {

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "SELECT film.id, film.naziv, projekcija.id, projekcija.vremePrikazivanja, tipProjekcije.naziv, sala.naziv, karta.id, karta.sediste_redniBroj, karta.username\r\n" + 
					"FROM karta\r\n" + 
					"LEFT JOIN projekcija ON projekcija.id = karta.projekcija_id\r\n" + 
					"LEFT JOIN film ON film.id = projekcija.film_id\r\n" + 
					"LEFT JOIN tipProjekcije ON tipProjekcije.id = projekcija.tipProjekcije_id\r\n" + 
					"LEFT JOIN sala ON sala.id = projekcija.sala_id WHERE karta.id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			rset = pstmt.executeQuery();
			
			System.out.println(pstmt);
			if (rset.next()) {
				int index = 1;

				int idFilma = rset.getInt(index++);
				String nazivFilma = rset.getString(index++);
				Film film = new Film(idFilma, nazivFilma);
				
				int idd = rset.getInt(index++);
				
				Date vremePrikazivanjaa = rset.getDate(index++);
				String vremePrikazivanja = dateToString(vremePrikazivanjaa);
				
				String tipProjekcijeNaziv = rset.getString(index++);
				TipProjekcije tipProjekcije = new TipProjekcije(tipProjekcijeNaziv);
				
				String salaNaziv = rset.getString(index++);	
				Sala sala = new Sala(salaNaziv);	
				
				int idKarte = rset.getInt(index++);
				int sediste = rset.getInt(index++);
				Sediste sedistee = new Sediste(sediste);
				
				String korisnik = rset.getString(index++);
				Korisnik korisnikk = new Korisnik(korisnik);
				
				Projekcija projekcija = new Projekcija(idd, film, tipProjekcije, sala, vremePrikazivanja);
						
				return new Karta(idKarte, projekcija, sedistee, korisnikk);

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

	public static boolean dodajKartu(Karta karta) throws Exception {

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO karta(projekcija_id, sediste_redniBroj, vremeProdaje, username)" + 
					"VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);


			int index = 1;
			pstmt.setInt(index++, karta.getProjekcija().getId());
			pstmt.setInt(index++, karta.getSediste().getRedniBroj());
			Date desDate=stringToDateForWrite(karta.getVremeProdaje());
			java.sql.Date date1=new java.sql.Date(desDate.getTime());
			pstmt.setDate(index++, date1);
			pstmt.setString(index++, karta.getKorisnik().getUsername());
			
			System.out.println(pstmt);

			return pstmt.executeUpdate() == 1;

		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		}finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}		
		return false;
	}

	public static boolean obrisiKartu(int id) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "DELETE FROM karta WHERE id = ?";

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
	
	public static String dateToString(Date date) {
		SimpleDateFormat formatvr = new SimpleDateFormat("dd.MM.yyyy");
		String datum;
		datum = formatvr.format(date);
		return datum;
	}
	
	public static Date stringToDateForWrite(String datum) {

		try {
			DateFormat formatvr = new SimpleDateFormat("yyyy-MM-dd");

			return (Date) formatvr.parse(datum);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
