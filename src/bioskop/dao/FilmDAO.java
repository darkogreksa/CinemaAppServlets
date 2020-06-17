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
					+ "distributer LIKE ? AND zemljaPorekla LIKE ?";
			
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
				int trajanjeFilma = rset.getInt(index++);
				String distributerFilma = rset.getString(index++);
				String zemljaPoreklaFilma = rset.getString(index++);
				int godinaProizvodnjeFilma = rset.getInt(index++);
				String opisFilma = rset.getString(index++);
				
				Film f = new Film(id, nazivFilma,reziserFilma, glumciFilma, zanroviFilma, trajanjeFilma, distributerFilma, zemljaPoreklaFilma, godinaProizvodnjeFilma, opisFilma);
				filmovi.add(f);
			}
		} catch (Exception e) {
			System.out.println("Greska u SQL upitu.");
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}
		return filmovi;
	}
	
	public static ArrayList<Film> orderAll(String column, String ascDesc){
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Film> filmovi = new ArrayList<Film>();
		
		PreparedStatement prSt = null;
		ResultSet rSet = null;
		try {
			String query = "SELECT * FROM film ORDER BY "+column+" "+ascDesc;
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
	
	public static ArrayList<Projekcija> getAllProjekcije() {
		Connection conn = ConnectionManager.getConnection();
		
		ArrayList<Projekcija> projekcije = new ArrayList<Projekcija>();
		PreparedStatement prSt = null;
		ResultSet rSet = null;
		
		try {
			String query = "SELECT * FROM projekcija";
			prSt = conn.prepareStatement(query);
			rSet = prSt.executeQuery();
		} catch (Exception e) {
			System.out.println("Greska u SQL upitu.");
			e.printStackTrace();
		} finally {
			try {
				prSt.close();
			} catch (SQLException exc1) {
				exc1.printStackTrace();
			} try {
				rSet.close();
			} catch (SQLException exc1) {
				exc1.printStackTrace();
			}
		}
		return projekcije;
	}
	
	public static Film get(int id) throws Exception {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT naziv, reziser, glumci, zanrovi, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis FROM film WHERE id=?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 1;
				String naziv = rset.getString(index++);
				String reziser = rset.getString(index++);
				String glumci = rset.getString(index++);
				String zanrovi = rset.getString(index++);
				int trajanje = rset.getInt(index++);
				String distributer = rset.getString(index++);
				String zemljaPorekla = rset.getString(index++);
				int godinaProizvodnje = rset.getInt(index++);
				String opis = rset.getString(index++);
				
				return new Film(id,naziv,reziser,glumci,zanrovi,trajanje,distributer,zemljaPorekla,godinaProizvodnje,opis);
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
	
	public static boolean izmeniFilm(Film film) {
		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement prSt = null;
		
		try {
			String query = "UPDATE film SET naziv = ?, reziser = ?, glumci = ?, zanrovi = ?, trajanje = ?, distributer = ?, zemljaPorekla = ?, godinaProizvodnje = ?, opis = ? WHERE id = ?";
			
			prSt = conn.prepareStatement(query);
			int index = 1;
			prSt.setString(index++, film.getNaziv());
			prSt.setString(index++, film.getReziser());
			prSt.setString(index++, film.getGlumci());
			prSt.setString(index++, film.getZanrovi());
			prSt.setInt(index++, film.getTrajanje());
			prSt.setString(index++, film.getDistributer());
			prSt.setString(index++, film.getZemljaPorekla());
			prSt.setInt(index++, film.getGodinaProizvodnje());
			prSt.setString(index++, film.getOpis());
			prSt.setInt(index++, film.getId());
			
			
			return prSt.executeUpdate() == 1;
			
		}  catch (Exception e) {
			System.out.println("Greska u SQL upitu!");
			e.printStackTrace();
		}finally {
			try {
				prSt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return false;
	}
	
	public static ArrayList<Film> pretraziFilmPoNazivu(String naz) {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Film> filmovi = new ArrayList<Film>();
		PreparedStatement prSt = null;
		ResultSet rSet = null;
		
		try {
			String query = "SELECT * FROM film WHERE naziv LIKE ?";
			prSt = conn.prepareStatement(query);
			prSt.setString(1, "%" + naz + "%" );
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
		}catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
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
		return filmovi;
	}
	
	public static ArrayList<Film> pretraziFilmPoZanrovima(String zanr) {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Film> filmovi = new ArrayList<Film>();
		PreparedStatement prSt = null;
		ResultSet rSet = null;
		
		try {
			String query = "SELECT * FROM film WHERE zanrovi LIKE ?";
			prSt = conn.prepareStatement(query);
			prSt.setString(1, "%" + zanr + "%" );
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
		}catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
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
		return filmovi;
	}

	public static ArrayList<Film> pretraziFilmPoTrajanju(int trj) {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Film> filmovi = new ArrayList<Film>();
		PreparedStatement prSt = null;
		ResultSet rSet = null;
		
		try {
			String query = "SELECT * FROM film WHERE trajanje LIKE ?";
			prSt = conn.prepareStatement(query);
			prSt.setInt(1,trj);
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
		}catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
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
		return filmovi;
	}

	public static ArrayList<Film> pretraziFilmPoDistributeru(String naz) {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Film> filmovi = new ArrayList<Film>();
		PreparedStatement prSt = null;
		ResultSet rSet = null;
		
		try {
			String query = "SELECT * FROM film WHERE distributer LIKE ?";
			prSt = conn.prepareStatement(query);
			prSt.setString(1, "%" + naz + "%" );
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
		}catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
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
		return filmovi;
	}
	
	public static ArrayList<Film> pretraziFilmZemljiPorekla(String zemlja) {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Film> filmovi = new ArrayList<Film>();
		PreparedStatement prSt = null;
		ResultSet rSet = null;
		
		try {
			String query = "SELECT * FROM film WHERE zemljaPorekla LIKE ?";
			prSt = conn.prepareStatement(query);
			prSt.setString(1, "%" + zemlja + "%" );
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
		}catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
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
		return filmovi;
	}
	
	public static ArrayList<Film> pretraziFilmPoGodiniProizvodnje(int godina) {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Film> filmovi = new ArrayList<Film>();
		PreparedStatement prSt = null;
		ResultSet rSet = null;
		
		try {
			String query = "SELECT * FROM film WHERE godinaProizvodnje LIKE ?";
			prSt = conn.prepareStatement(query);
			prSt.setInt(1,godina);
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
		}catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
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
		return filmovi;
	}
	
	public static ArrayList<Film> returnFilm(int ids) {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Film> filmovi = new ArrayList<Film>();
		PreparedStatement prSt = null;
		ResultSet rSet = null;
		
		try {
			String query = "";
			prSt = conn.prepareStatement(query);
			prSt.setInt(1, ids);
			rSet = prSt.executeQuery();

			while (rSet.next()) {
				int index = 1;
				int id = rSet.getInt(index++);
				String naziv = rSet.getString(index++);
				
				Film f = new Film(id,naziv,null,null,null,0,null,null,0,null);
				filmovi.add(f);
			}
		}catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
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
		return filmovi;
	}
	
	public static boolean obrisiFilm(int id) {
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
	
	public static boolean obrisiById(int id) {
		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement prSt = null;
		try {
			String query = "DELETE film WHERE id = ?";
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
	
	public static int getFilmId() {
		Connection conn = ConnectionManager.getConnection();
		int id=0;
		PreparedStatement prSt = null;
		ResultSet rSet = null;
		try {
			String query = "SELECT MAX(id) FROM film";
			prSt = conn.prepareStatement(query);
			rSet = prSt.executeQuery();
		
			if (rSet.next()) {
				id = rSet.getInt(1);
			}
			id++;
			return id;
		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				prSt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				prSt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return 0;
	}
	
}
