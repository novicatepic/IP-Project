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
	private static final String SQL_SELECT_ONE = "SELECT * FROM savjetnik_poruka WHERE id=?";
	private static final String UPDATE_ONE_READ = "UPDATE savjetnik_poruka SET procitana=1 WHERE id=?";
	private static final String UPDATE_ONE_ANSWERED = "UPDATE savjetnik_poruka SET odgovorena=1 WHERE id=?";
	private static final String SQL_SELECT_UNREAD = "SELECT * FROM savjetnik_poruka WHERE procitana=0";
	private static final String SQL_SELECT_UNANSWERED = "SELECT * FROM savjetnik_poruka WHERE odgovorena=0";
	private static final String SQL_SELECT_WRITER = "SELECT * FROM korisnik WHERE id=?";
	private static final String FILTER_BY_TEXT = "SELECT * FROM savjetnik_poruka WHERE tekst LIKE ? AND procitana=0";
	private static final String FILTER_BY_TEXT_UNANSWERED = "SELECT * FROM savjetnik_poruka WHERE tekst LIKE ? AND odgovorena=0";

	public static MessageBean selectOne(int id) {
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ONE, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return new MessageBean(rs.getInt("id"), rs.getString("naslov"), rs.getString("tekst"), 
						rs.getDate("datum"), rs.getBoolean("procitana"), rs.getBoolean("odgovorena"), rs.getInt("korisnik_id"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return null;
	}
	
	public static boolean updateOneRead(int id) {
	    Connection connection = null;
	    try {
	        connection = connectionPool.checkOut();
	        PreparedStatement pstmt = DAOUtil.prepareStatement(connection, UPDATE_ONE_READ, false, id);
	        int rowsAffected = pstmt.executeUpdate();

	        if (rowsAffected > 0) {
	            pstmt.close();
	            return true;
	        } else {
	            pstmt.close();
	            return false;
	        }
	    } catch (SQLException exp) {
	        exp.printStackTrace();
	    } finally {
	        connectionPool.checkIn(connection);
	    }
	    return false;
	}
	
	public static boolean updateOneAnswered(int id) {
	    Connection connection = null;
	    try {
	        connection = connectionPool.checkOut();
	        PreparedStatement pstmt = DAOUtil.prepareStatement(connection, UPDATE_ONE_ANSWERED, false, id);
	        int rowsAffected = pstmt.executeUpdate();

	        if (rowsAffected > 0) {
	            pstmt.close();
	            return true;
	        } else {
	            pstmt.close();
	            return false;
	        }
	    } catch (SQLException exp) {
	        exp.printStackTrace();
	    } finally {
	        connectionPool.checkIn(connection);
	    }
	    return false;
	}
	
	public static ArrayList<MessageBean> selectAllUnread() {
		ArrayList<MessageBean> retVal = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_UNREAD, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(new MessageBean(rs.getInt("id"), rs.getString("naslov"), rs.getString("tekst"), 
						rs.getDate("datum"), rs.getBoolean("procitana"), rs.getBoolean("odgovorena"), rs.getInt("korisnik_id")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
	
	public static ArrayList<MessageBean> selectAllUnanswered() {
		ArrayList<MessageBean> retVal = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_UNANSWERED, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(new MessageBean(rs.getInt("id"), rs.getString("naslov"), rs.getString("tekst"), 
						rs.getDate("datum"), rs.getBoolean("procitana"), rs.getBoolean("odgovorena"), rs.getInt("korisnik_id")));
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
		text = /*"%" +*/text + "%";
		ArrayList<MessageBean> retVal = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {text};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, FILTER_BY_TEXT, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(new MessageBean(rs.getInt("id"), rs.getString("naslov"),
						rs.getString("tekst"), rs.getDate("datum"), 
						rs.getBoolean("procitana"), rs.getBoolean("odgovorena"),
						rs.getInt("korisnik_id")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
	
	public static ArrayList<MessageBean> filterByTextUnanswered(String text) {
		text = /*"%" +*/ text + "%";
		ArrayList<MessageBean> retVal = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {text};
		
		
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, FILTER_BY_TEXT_UNANSWERED, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(new MessageBean(rs.getInt("id"), rs.getString("naslov"),
						rs.getString("tekst"), rs.getDate("datum"), 
						rs.getBoolean("procitana"), rs.getBoolean("odgovorena"),
						rs.getInt("korisnik_id")));
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
