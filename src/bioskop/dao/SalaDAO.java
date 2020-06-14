package bioskop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Sala;

public class SalaDAO {
	
	public static Sala get(String naziv) {
		java.sql.Connection conn = ConnectionManager.getConnection();
		java.sql.PreparedStatement prSt = null;
		java.sql.ResultSet rSet = null;
		
		try {
			String query = "SELECT * FROM sala WHERE naziv = ?";
			prSt = conn.prepareStatement(query);
			prSt.setString(1, naziv);
			rSet = prSt.executeQuery();
			
			if (rSet.next()) {
				int index = 2;
				int id = rSet.getInt(index++);
				Sala s = new Sala(id, naziv);
				
			return s;
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
	
	public static Sala getId(int id) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement prSt = null;
		ResultSet rSet = null;
		
		try {
			String query = "SELECT * FROM sala WHERE id = ?";
			prSt = conn.prepareStatement(query);
			prSt.setInt(1, id);
			rSet = prSt.executeQuery();
			
			if (rSet.next()) {
				int index = 2;
				String nazivSale = rSet.getString(index++);
				Sala s = new Sala(id, nazivSale);
				
			return s;
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
