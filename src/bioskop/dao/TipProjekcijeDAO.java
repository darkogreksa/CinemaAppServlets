package bioskop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Sala;
import model.TipProjekcije;

public class TipProjekcijeDAO {
	
	public static TipProjekcije get(String naziv) {
		java.sql.Connection conn = ConnectionManager.getConnection();
		java.sql.PreparedStatement prSt = null;
		java.sql.ResultSet rSet = null;
		
		try {
			String query = "SELECT * FROM tipProjekcije WHERE naziv = ?";
			prSt = conn.prepareStatement(query);
			prSt.setString(1, naziv);
			rSet = prSt.executeQuery();
			
			if (rSet.next()) {
				int index = 2;
				int id = rSet.getInt(index++);
				TipProjekcije t = new TipProjekcije(id, naziv);
				
			return t;
			}
		}catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				prSt.close();
			} catch (java.sql.SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rSet.close();
			} catch (java.sql.SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return null;
	}
	
	public static TipProjekcije getId(int id) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement prSt = null;
		ResultSet rSet = null;
		
		try {
			String query = "SELECT * FROM tipProjekcije WHERE id = ?";
			prSt = conn.prepareStatement(query);
			prSt.setInt(1, id);
			rSet = prSt.executeQuery();
			
			if (rSet.next()) {
				int index = 2;
				String nazivTipaProjekcije = rSet.getString(index++);
				TipProjekcije t = new TipProjekcije(id, nazivTipaProjekcije);
				
			return t;
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
		return null;
	}

}
