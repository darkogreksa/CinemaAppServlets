package bioskop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Film;
import model.Projekcija;

public class FilmDAO {
	
	public static List<Film> getAll(String naziv, String zanrovi, int trajanjeOd, int trajanjeDo, String distributer, String zemljaPorekla, int godinaProizvodnjeOd, int godinaProizvodnjeDo) {
		List<Film> filmovi = new ArrayList<>();
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT * FROM film WHERE "
					+ "naziv LIKE ? AND trajanje >= ? AND trajanje <= ? AND "
					+ "zanrovi LIKE ? AND godinaProizvodnje >= ? AND godinaProizvodnje <= ? AND "
					+ "distributer LIKE ? AND zemljaPorekla LIKE ? AND obrisan=0";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, "%" + naziv + "%");
			pstmt.setInt(index++, trajanjeOd);
			pstmt.setInt(index++, trajanjeDo);
			pstmt.setString(index++, "%" + zanrovi + "%");
			pstmt.setInt(index++, godinaProizvodnjeOd);
			pstmt.setInt(index++, godinaProizvodnjeDo);
			pstmt.setString(index++, "%" + distributer + "%");
			pstmt.setString(index++, "%" + zemljaPorekla + "%");
			System.out.println(pstmt);

			rset = pstmt.executeQuery();
		
			while (rset.next()) {
				index = 1;
				int id = rset.getInt(index++);
				String nazivFilma = rset.getString(index++);
				String reziserFilma = rset.getString(index++);
				String glumciFilma = rset.getString(index++);
				String zanroviFilma = rset.getString(index++);
				Integer trajanjeFilma = rset.getInt(index++);
				String distributerFilma = rset.getString(index++);
				String zemljaPoreklaFilma = rset.getString(index++);
				Integer godinaProizvodnjeFilma = rset.getInt(index++);
				String opisFilma = rset.getString(index++);
				Integer obrisan = rset.getInt(index++);
				
				Film f = new Film(id, nazivFilma, reziserFilma, glumciFilma, zanroviFilma, trajanjeFilma, distributerFilma, zemljaPoreklaFilma, godinaProizvodnjeFilma, opisFilma, obrisan);
				filmovi.add(f);
			}
			return filmovi;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}
		return null;
	}
	
	public static int getFilmId() {
		Connection conn = ConnectionManager.getConnection();
		int id = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT MAX(id) FROM film";
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
	public static Film get(int id) throws Exception {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM film WHERE id=?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 2;
				String naziv = rset.getString(index++);
				String reziser = rset.getString(index++);
				String glumci = rset.getString(index++);
				String zanrovi = rset.getString(index++);
				int trajanje = rset.getInt(index++);
				String distributer = rset.getString(index++);
				String zemljaPorekla = rset.getString(index++);
				int godinaProizvodnje = rset.getInt(index++);
				String opis = rset.getString(index++);
				Integer obrisan = rset.getInt(index++);
				
				return new Film(id,naziv,reziser,glumci,zanrovi,trajanje,distributer,zemljaPorekla,godinaProizvodnje,opis,obrisan);
			}
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}
		
		return null;
	}
	
	public static boolean dodajFilm(Film film) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO film (naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis)"
					+ "VALUES (?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, film.getNaziv());
			pstmt.setString(index++, film.getReziser());
			pstmt.setString(index++, film.getGlumci());
			pstmt.setString(index++, film.getZanrovi());
			pstmt.setInt(index++, film.getTrajanje());
			pstmt.setString(index++, film.getDistributer());
			pstmt.setString(index++, film.getZemljaPorekla());
			pstmt.setInt(index++, film.getGodinaProizvodnje());
			pstmt.setString(index++, film.getOpis());
			
			return pstmt.executeUpdate() == 1;
		
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}	
	}
	
	public static boolean izmeniFilm(Film film) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		
		try {
			String query = "UPDATE film SET naziv = ?, reziser = ?, glumci = ?, zanrovi = ?, trajanje = ?, distributer = ?, zemljaPorekla = ?, godinaProizvodnje = ?, opis = ? WHERE id = ?";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setInt(index++, film.getId());
			pstmt.setString(index++, film.getNaziv());
			pstmt.setString(index++, film.getReziser());
			pstmt.setString(index++, film.getGlumci());
			pstmt.setString(index++, film.getZanrovi());
			pstmt.setInt(index++, film.getTrajanje());
			pstmt.setString(index++, film.getDistributer());
			pstmt.setString(index++, film.getZemljaPorekla());
			pstmt.setInt(index++, film.getGodinaProizvodnje());
			pstmt.setString(index++, film.getOpis());
			
			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
	}
	
	public static boolean obrisiFilm(int id) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE film SET obrisan=1 WHERE id = ?";

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
	
}
