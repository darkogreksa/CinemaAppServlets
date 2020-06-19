package bioskop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Film;
import model.Korisnik;
import model.Projekcija;
import model.Sala;
import model.TipProjekcije;

public class ProjekcijaDAO {
	
	public static ArrayList<Projekcija> getAll() {
		Connection conn = ConnectionManager.getConnection();
		
		ArrayList<Projekcija> projekcije = new ArrayList<Projekcija>();
		PreparedStatement prSt = null;
		ResultSet rSet = null;
		
		try {
			String query = "SELECT * FROM projekcija";
			prSt = conn.prepareStatement(query);
			rSet = prSt.executeQuery();
		
			while (rSet.next()) {
				int index = 1;
				int id = rSet.getInt(index++);
				int film = rSet.getInt(index++);
				Film f = FilmDAO.get(film);
				int tipProjekcije = rSet.getInt(index++);
				TipProjekcije tp = TipProjekcijeDAO.getId(tipProjekcije);
				int sala = rSet.getInt(index++);
				Sala s = SalaDAO.getId(sala);
				Date vremePrikazivanja = rSet.getDate(index++);
				String vrPrikazivanja = dateToString(vremePrikazivanja);
				double cena = rSet.getDouble(index++);
				String administrator = rSet.getString(index++);
				Korisnik k = KorisnikDAO.get(administrator);
				
				Projekcija p = new Projekcija(id, f, tp, s, vrPrikazivanja, cena, k);
				projekcije.add(p);
			}
		} catch (Exception e) {
			System.out.println("Greska u SQL upitu!");
			e.printStackTrace();
		}finally {
			try {
				prSt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rSet.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return projekcije;
	}
	
	public static ArrayList<Film> getAllFilmovi(){
		Connection conn = ConnectionManager.getConnection();
		
		ArrayList<Film> filmovi = new ArrayList<Film>();
		PreparedStatement prSt = null;
		ResultSet rSet = null;
		
		try {
			String query = "SELECT * FROM film";
			prSt = conn.prepareStatement(query);
			rSet = prSt.executeQuery();
		
			
			while (rSet.next()) {
				int index = 1;
				int id = rSet.getInt(index++);
				String naziv = rSet.getString(index++);
				String reziser = rSet.getString(index++);
				String glumci = rSet.getString(index++);
				String zanrovi = rSet.getString(index++);
				int trajanje = rSet.getInt(index++);
				String distributer = rSet.getString(index++);
				String zemljaPorekla = rSet.getString(index++);
				int godinaProizvodnje = rSet.getInt(index++);
				String opis = rSet.getString(index++);
				
				Film f = new Film(id,naziv,reziser,glumci,zanrovi,trajanje,distributer,zemljaPorekla,godinaProizvodnje,opis);
				filmovi.add(f);
			}
		} catch (Exception e) {
			System.out.println("Greska u SQL upitu!");
			e.printStackTrace();
		}finally {
			try {
				prSt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rSet.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return filmovi;
	}
	
	public static ArrayList<Projekcija> orderAll(String column, String ascDesc){
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Projekcija> projekcije = new ArrayList<Projekcija>();
		
		PreparedStatement prSt = null;
		ResultSet rSet = null;
		try {
			String query = "SELECT * FROM projekcija ORDER BY "+column+" "+ascDesc;
			prSt = conn.prepareStatement(query);
			rSet = prSt.executeQuery();
			
			while (rSet.next()) {
				int index = 1;
				int id = rSet.getInt(index++);
				int film = rSet.getInt(index++);
				Film f = FilmDAO.get(film);
				int tipProjekcije = rSet.getInt(index++);
				TipProjekcije tp = TipProjekcijeDAO.getId(tipProjekcije);
				int sala = rSet.getInt(index++);
				Sala s = SalaDAO.getId(sala);
				String vrPrikazivanja = rSet.getString(index++);
				double cena = rSet.getDouble(index++);
				String administrator = rSet.getString(index++);
				Korisnik k = KorisnikDAO.get(administrator);
				if(k == null) {
					continue;
				} else {
					Projekcija p = new Projekcija(id, f, tp, s, vrPrikazivanja, cena, k);
					projekcije.add(p);
				}
				
				
			}
		} catch (Exception e) {
			System.out.println("Greska u SQL upitu!");
			e.printStackTrace();
		}finally {
			try {
				prSt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rSet.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return projekcije;
	}
	
	public static Projekcija getId(int id) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement prSt = null;
		ResultSet rSet = null;
		
		try {
			String query = "SELECT * FROM projekcija WHERE id=?";
			prSt = conn.prepareStatement(query);
			prSt.setInt(1, id);
			rSet = prSt.executeQuery();
			
			if(rSet.next()) {
				int index = 2;
				int film = rSet.getInt(index++);
				Film f = FilmDAO.get(film);
				int tipProjekcije = rSet.getInt(index++);
				TipProjekcije tp = TipProjekcijeDAO.getId(tipProjekcije);
				int sala = rSet.getInt(index++);
				Sala s = SalaDAO.getId(sala);
				String vrPrikazivanja = rSet.getString(index++);
				double cena = rSet.getDouble(index++);
				String administrator = rSet.getString(index++);
				Korisnik k = KorisnikDAO.get(administrator);
				
				Projekcija p = new Projekcija(id, f, tp, s, vrPrikazivanja, cena, k);
				return p;
			}
		}  catch (Exception e) {
			System.out.println("Greska u SQL upitu!");
			e.printStackTrace();
		} finally {
			try {
				prSt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rSet.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return null;
	}
	
	public static boolean dodajProjekciju(Projekcija projekcija) {
		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement prSt = null;
		
		try {
			String query = "INSERT INTO projekcija (film, tipProjekcije, sala, vremePrikazivanja, cena, administrator)"
					+ "VALUES (?,?,?,?,?,?)";
			prSt = conn.prepareStatement(query);
			
			int index = 1;
			prSt.setInt(index++, projekcija.getFilm().getId());
			prSt.setInt(index++, projekcija.getTipProjekcije().getId());
			prSt.setInt(index++, projekcija.getSala().getId());
			Date vremePr = stringToDateForWrite(projekcija.getVremePrikazivanja());
			java.sql.Date vremeP = new java.sql.Date(vremePr.getTime());
			prSt.setDate(index++,vremeP);
			prSt.setDouble(index++, projekcija.getCena());
			prSt.setString(index++, projekcija.getAdministrator().getUsername());
			
			return prSt.executeUpdate() == 1;
		
		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				prSt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return false;
	}
	
	public static boolean obrisiProjekciju(int id) {
		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement prSt = null;
		try {
			String query = "";
			prSt = conn.prepareStatement(query);
			prSt.setInt(1, id);
			
			return prSt.executeUpdate() == 1;
		}catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				prSt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		
		return false;
	}
	
	public static boolean obrisiProjekcijuById(int id) {
		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement prSt = null;
		try {
			String query = "DELETE projekcija WHERE id = ?";
			prSt = conn.prepareStatement(query);
			prSt.setInt(1, id);
			
			return prSt.executeUpdate() == 1;
		}catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				prSt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		
		return false;
	}
	
	public static String dateToString(Date date) {
		SimpleDateFormat formatvr = new SimpleDateFormat("dd.MM.yyyy");
		String datum;
		datum = formatvr.format(date);
		return datum;
	}

	public static Date stringToDate(String datum) {

		try {
			DateFormat formatvr = new SimpleDateFormat("dd.MM.yyyy");

			return formatvr.parse(datum);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;

	}
	public static String dateToStringForWrite(Date date) {
		SimpleDateFormat formatvr = new SimpleDateFormat("yyyy-MM-dd");
		String datum;
		datum = formatvr.format(date);
		return datum;
	}
	public static Date stringToDateForWrite(String datum) {

		try {
			DateFormat formatvr = new SimpleDateFormat("yyyy-MM-dd");

			return formatvr.parse(datum);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;

	}

}
