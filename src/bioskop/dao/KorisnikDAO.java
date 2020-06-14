package bioskop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
	
	public static Korisnik getOne(String username) throws Exception {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT username, password, role, datumRegistracije FROM korisnik WHERE username = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 1;
				String password = rset.getString(index++);
				Role role = Role.valueOf(rset.getString(index++));
				
				java.sql.Date datum = rset.getDate(index++);
				Date datumRegistracije = new Date(datum.getTime());

				return new Korisnik(username, password, datumRegistracije, role);
			}
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}

		return null;
	}
	
	public static ArrayList<Korisnik> getAll() {
		Connection conn = ConnectionManager.getConnection();
		
		ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Korisnik> korisnicii = new ArrayList<Korisnik>();
		
		try {
			String query = "SELECT username, password, role, datumRegistracije FROM korisnik";
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int index = 1;
				String username = rset.getString(index++);
				String password = rset.getString(index++);
				Date datum = rset.getDate(index++);
				Date datumRegistracije = new Date(datum.getTime());
				Role role = Role.valueOf(rset.getString(index++));
				
				Korisnik k = new Korisnik(username, password, datumRegistracije, role);
				korisnici.add(k);
			}
		} catch (Exception e) {
			System.out.println("Greska u SQL upitu.");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException exc) {
				exc.printStackTrace();
			} try {
				rset.close();
			} catch (SQLException exc) {
				exc.printStackTrace();
			}
		}
		return korisnici;
	}
	
	public static boolean dodajKorisnika(Korisnik korisnik) {
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
			
			return pstmt.executeUpdate() == 1;
		} catch (Exception e) {
			System.out.println("Greska u SQL upitu.");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException exc) {
				exc.printStackTrace();
			}
		}
		return false;
	}
	
	public static boolean izmeniKorisnika(Korisnik korisnik) {
		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement prSt = null;
		
		try {
			String query = "UPDATE korisnik SET password = ? WHERE username = ?";
			
			prSt = conn.prepareStatement(query);
			
			int index = 1;
			prSt.setString(index++, korisnik.getPassword());
			prSt.setString(index++, korisnik.getUsername());
			
			return prSt.executeUpdate() == 1;
		} catch (Exception e) {
			System.out.println("Greska u SQL upitu.");
			e.printStackTrace();
		} finally {
			try {
				prSt.close();
			} catch (SQLException exc) {
				exc.printStackTrace();
			}
		}
		return false;
	}
	
	/*public static ArrayList<Korisnik> pretraziKorisnikaPoUlozi(String korisnik) {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
		PreparedStatement prSt = null;
		ResultSet rSet = null;
		
		try {
			String query = "SELECT * FROM korisnik WHERE role LIKE ?";
			prSt = conn.prepareStatement(query);
			prSt.setString(1, "%" + korisnik + "%");
			rSet = prSt.executeQuery();
			
			while(rSet.next()) {
				int index = 1;
				String username = rSet.getString(index++);
				String password = rSet.getString(index++);
				Date datumRegistracije = rSet.getDate(index++);
				String dRegistracije = dateToString(datumRegistracije);
				Role role = Role.valueOf(rSet.getString(index++));
				
				Korisnik k = new Korisnik(username, password, dRegistracije, role);
				
				korisnici.add(k);
			}
		} catch (Exception e) {
			System.out.println("Greska u SQL upitu.");
			e.printStackTrace();
		} finally {
			try {
				prSt.close();
			} catch (SQLException exc) {
				exc.printStackTrace();
			}
		}
		return korisnici;
	}
	
	public static ArrayList<Korisnik> pretraziKorisnikaPoUsername(String korisnik) {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
		PreparedStatement prSt = null;
		ResultSet rSet = null;
		
		try {
			String query = "SELECT * FROM korisnik WHERE username LIKE ?";
			prSt = conn.prepareStatement(query);
			prSt.setString(1, "%" + korisnik + "%");
			rSet = prSt.executeQuery();
			
			while(rSet.next()) {
				int index = 1;
				String username = rSet.getString(index++);
				String password = rSet.getString(index++);
				Date datumRegistracije = rSet.getDate(index++);
				String dRegistracije = dateToString(datumRegistracije);
				Role role = Role.valueOf(rSet.getString(index++));
				
				Korisnik k = new Korisnik(username, password, dRegistracije, role);
				
				korisnici.add(k);
			}
		} catch (Exception e) {
			System.out.println("Greska u SQL upitu.");
			e.printStackTrace();
		} finally {
			try {
				prSt.close();
			} catch (SQLException exc) {
				exc.printStackTrace();
			}
		}
		return korisnici;
	}*/
	
	public static boolean obrisiKorisnika(String username) {
		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		try {
			String query = "DELETE * FROM korisnik WHERE username = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			
			return pstmt.executeUpdate() == 1;
		} catch (Exception e) {
			System.out.println("Greska u SQL upitu.");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
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
