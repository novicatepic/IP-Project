package org.unibl.etf.ip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.unibl.etf.ip.bean.AdminBean;
import org.unibl.etf.ip.bean.ConsultantBean;

public class AdminDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_ONE = "SELECT * FROM admin WHERE korisnicko_ime=? AND lozinka=?";

	public static AdminBean selectOne(String username, String password) {
		AdminBean ab = new AdminBean();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { username, password };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ONE, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				ab = new AdminBean(rs.getInt("id"), rs.getString("ime"), rs.getString("prezime")
						, rs.getString("korisnicko_ime"), rs.getString("lozinka"));
			} else {
				return null;
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return ab;
	}

}
