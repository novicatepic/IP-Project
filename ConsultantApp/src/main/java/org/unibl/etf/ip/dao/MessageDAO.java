package org.unibl.etf.ip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.unibl.etf.ip.bean.ConsultantBean;
import org.unibl.etf.ip.bean.FitnessUserBean;
import org.unibl.etf.ip.bean.MessageBean;

public class MessageDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_ALL = "SELECT * FROM savjetnik_poruka WHERE procitana=0";
	private static final String SQL_SELECT_WRITER = "SELECT * FROM korisnik WHERE id=?";
	private static final String FILTER_BY_TEXT = "SELECT * FROM savjetnik_poruka WHERE tekst LIKE ?";

	public static ArrayList<MessageBean> selectAll() {
		ArrayList<MessageBean> retVal = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ALL, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(new MessageBean(rs.getInt("id"), rs.getString("tekst"), rs.getDate("datum"), rs.getBoolean("procitana"), rs.getInt("korisnik_id")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
	
	public static FitnessUserBean selectUserWhoWroteMessage(int id) {
		
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_WRITER, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return new FitnessUserBean(rs.getInt("id"), rs.getString("ime"), rs.getString("prezime"),rs.getString("grad"), rs.getString("korisnicko_ime"), 
						rs.getString("lozinka"), rs.getString("avatar"), rs.getString("mail"), rs.getBoolean("aktivan"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return null;
	}
	
	public static ArrayList<MessageBean> filterByText(String text) {
		text = "%" + text + "%";
		ArrayList<MessageBean> retVal = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {text};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, FILTER_BY_TEXT, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(new MessageBean(rs.getInt("id"), rs.getString("tekst"), rs.getDate("datum"), rs.getBoolean("procitana"), rs.getInt("korisnik_id")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}

}
