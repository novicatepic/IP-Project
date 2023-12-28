package org.unibl.etf.ip.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.unibl.etf.ip.bean.CategoryBean;
import org.unibl.etf.ip.bean.ConsultantBean;
import org.unibl.etf.ip.password.PasswordHasher;

public class ConsultantDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_ALL = "SELECT * FROM savjetnik";
	private static final String SQL_INSERT = "INSERT INTO savjetnik (korisnicko_ime, lozinka, ime, prezime)"
			+ " VALUES (?, ?, ?, ?)";

	public static ArrayList<ConsultantBean> selectAll() {
		ArrayList<ConsultantBean> retVal = new ArrayList<ConsultantBean>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ALL, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(new ConsultantBean(rs.getInt("id"), rs.getString("korisnicko_ime"), rs.getString("lozinka")
						, rs.getString("ime"), rs.getString("prezime")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}

	public static boolean insert(ConsultantBean consultant) {
		boolean result = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		try {
			Object values[] = { consultant.getUsername(), PasswordHasher.hashPassword(consultant.getPassword()) , consultant.getName(), consultant.getLastName() };
		
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if(pstmt.getUpdateCount()>0) {
				result = true;
			}
			if (generatedKeys.next())
				consultant.setId(generatedKeys.getInt(1));
			pstmt.close();
		} catch (SQLException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}

}
