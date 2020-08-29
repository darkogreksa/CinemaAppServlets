package bioskop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Korisnik;
import model.Sala;
import model.Korisnik.Role;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

public class KorisnikDAO {
	
	public static SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public static Korisnik get(String username, String password) throws Exception {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT role FROM korisnik WHERE username = ? AND password = ?";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, username);
			pstmt.setString(index++, password);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				Role role = Role.valueOf(rset.getString(1));

				return new Korisnik(username, password, role);
			}
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}

		return null;
	}
	
	public static Korisnik get(String username) throws Exception {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM korisnik WHERE username = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 2;
				String password = rset.getString(index++);
				
				java.sql.Date datum = rset.getDate(index++);
				Date datumRegistracije = new Date(datum.getTime());
				Role role = Role.valueOf(rset.getString(index++));

				return new Korisnik(username, password, datumRegistracije, role);
			}
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}

		return null;
	}
	
	public static ArrayList<Korisnik> getAll(String username, String datumRegistracije, String role) {
		List<Korisnik> korisnici = new ArrayList<>();
		
		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT username, datumRegistracije, role "
					+ "FROM korisnik WHERE username LIKE ? AND datumRegistracije LIKE ? "
					+ "AND role LIKE ?";
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, "%" + username + "%");
			pstmt.setString(index++, "%" + datumRegistracije + "%");
			pstmt.setString(index++, "%" + role + "%");
			System.out.println(pstmt);

			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				index = 1;
				String usernameK = rset.getString(index++);
				java.sql.Date datum = rset.getDate(index++);
				Date datumRegistracijeK = new Date(datum.getTime());
				Role roleK = Role.valueOf(rset.getString(index++));
				
				Korisnik k = new Korisnik(usernameK, datumRegistracijeK, roleK);
				korisnici.add(k);
			}
		} catch (Exception e) {
			System.out.println("Greska u SQL upitu.");
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}
		return (ArrayList<Korisnik>) korisnici;
	}
	
	public static boolean dodajKorisnika(Korisnik korisnik) throws Exception {
		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		
		try {
			String query = "INSERT INTO korisnik (username, password, datumRegistracije, role) VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			
			int index = 1;
			pstmt.setString(index++, korisnik.getUsername());
			pstmt.setString(index++, korisnik.getPassword());
			pstmt.setDate(index++, (java.sql.Date) korisnik.getDatumRegistracije());
			pstmt.setString(index++, korisnik.getRole().toString());
			System.out.println(pstmt);
			
			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}
	}
	
	public static boolean izmeniKorisnika(Korisnik korisnik, String newUsername) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;

		try {
			String query = "UPDATE korisnik SET username = '" + newUsername +"', role = ?, password = ?, WHERE username = ?";

			pstmt = conn.prepareStatement(query);

			int index = 1;
			pstmt.setString(index++, korisnik.getUsername());
			pstmt.setString(index++, korisnik.getPassword());
			pstmt.setString(index++, korisnik.getRole().toString());

			System.out.println(pstmt);

			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		}finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return false;
	}
	
	public static boolean obrisiKorisnika(String username) {
		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		try {
			String query = "DELETE * FROM korisnik WHERE username = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			
			return pstmt.executeUpdate() == 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return false;
	}

	private static String dateToString(Date date) {
		SimpleDateFormat formatvr = new SimpleDateFormat("dd.MM.yyyy");
		String datum;
		datum = formatvr.format(date);
		return datum;
	}
	
	public static Date stringToDate(String datum) {

		try {
			DateFormat formatvr = new SimpleDateFormat("dd.MM.yyyy");
			return formatvr.parse(datum);
		} catch (Exception e) {
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
