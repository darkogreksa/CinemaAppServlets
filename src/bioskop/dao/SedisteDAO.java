package bioskop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Sediste;

public class SedisteDAO {
	
	public static Sediste get(int sala, int sediste) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "SELECT redniBroj FROM sediste_sala WHERE sala_id = ? AND id = ? AND zauzeto=0";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setInt(index++, sala);
			pstmt.setInt(index++, sediste);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				index = 1;
				int redniBroj = rset.getInt(index++);

				return new Sediste(redniBroj);
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

	public static List<Sediste> getSedistaFromSala(int sala) {
		List<Sediste> sedista = new ArrayList<>();
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "SELECT redniBroj FROM sediste_sala WHERE sala_id = ? AND zauzeto=0";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, sala);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				int index = 1;
				int redniBroj = rset.getInt(index++);

				Sediste sediste = new Sediste(redniBroj);
				sedista.add(sediste);
			}
			return sedista;
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
	
	public static boolean zauzmiSediste(int sala, int sediste) {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;

		try {
			String query = "UPDATE sediste_sala SET zauzeto = 1 WHERE sala_id = ? and redniBroj = ?";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setInt(index++, sala);
			pstmt.setInt(index++, sediste);
			
			return pstmt.executeUpdate() == 1;

		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		return false;

	}
}
