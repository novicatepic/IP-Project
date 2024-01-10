package org.unibl.etf.ip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.unibl.etf.ip.bean.CategoryBean;
import org.unibl.etf.ip.bean.FitnessUserBean;

public class FitnessUserDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_ONE = "SELECT * FROM korisnik WHERE id=?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM korisnik";
	private static final String SQL_INSERT = "INSERT INTO korisnik (ime, prezime, grad, korisnicko_ime, lozinka, avatar, mail, aktivan)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE korisnik SET ime=?, prezime=?, grad=?, korisnicko_ime=?, lozinka=? "
			+ ", avatar=?, mail=? WHERE id=?";
	private static final String SQL_DELETE = "DELETE FROM korisnik WHERE id=?";
	
	private static final String SQL_TERMINATE = "UPDATE korisnik SET terminiran=1 WHERE id=?";
	private static final String SQL_UNTERMINATE = "UPDATE korisnik SET terminiran=0 WHERE id=?";

	public static FitnessUserBean selectOne(int id) {
		FitnessUserBean c = new FitnessUserBean();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ONE, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				c = new FitnessUserBean(rs.getInt("id"), rs.getString("ime"), rs.getString("prezime"),
						rs.getString("grad"), rs.getString("korisnicko_ime"), rs.getString("lozinka"), rs.getString("avatar"), 
						rs.getString("mail"), rs.getBoolean("aktivan"), rs.getBoolean("terminiran"));
			} else {
				return null;
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return c;
	}
	
	public static ArrayList<FitnessUserBean> selectAll() {
		ArrayList<FitnessUserBean> retVal = new ArrayList<FitnessUserBean>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ALL, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(new FitnessUserBean(rs.getInt("id"), rs.getString("ime"), rs.getString("prezime"),
						rs.getString("grad"), rs.getString("korisnicko_ime"), rs.getString("lozinka"), rs.getString("avatar"), 
						rs.getString("mail"), rs.getBoolean("aktivan"), rs.getBoolean("terminiran")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}

	public static boolean insert(FitnessUserBean user) {
		boolean result = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { user.getName(),user.getLastName(), user.getCity(), user.getUsername(), user.getPassword(), user.getAvatar(), user.getMail(), false };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if(pstmt.getUpdateCount()>0) {
				result = true;
			}
			if (generatedKeys.next())
				user.setId(generatedKeys.getInt(1));
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	
	public static boolean update(FitnessUserBean user) {
		boolean result = false;
		Connection connection = null;
		Object values[] = { user.getName(),user.getLastName(), user.getCity(), user.getUsername(), user.getPassword(), user.getAvatar(), user.getMail(), user.getId() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_UPDATE, false, values);
			pstmt.executeUpdate();
			if(pstmt.getUpdateCount()>0) {
				result = true;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	
	public static boolean updateTerminate(Integer id) {
		boolean result = false;
		Connection connection = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_TERMINATE, false, values);
			pstmt.executeUpdate();
			if(pstmt.getUpdateCount()>0) {
				result = true;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	
	public static boolean updateUnterminate(Integer id) {
		boolean result = false;
		Connection connection = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_UNTERMINATE, false, values);
			pstmt.executeUpdate();
			if(pstmt.getUpdateCount()>0) {
				result = true;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	
	public static boolean delete(Integer id) {
		boolean result = false;
		Connection connection = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_DELETE, false, values);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}

}
