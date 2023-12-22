package org.unibl.etf.ip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.unibl.etf.ip.bean.ConsultantBean;

public class ConsultantDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_ONE = "SELECT * FROM savjetnik WHERE korisnicko_ime=? AND lozinka=?";

	public static ConsultantBean selectOne(String username, String password) {
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {username, password};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ONE, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return new ConsultantBean(rs.getInt("id"), rs.getString("korisnicko_ime"), rs.getString("lozinka")
						,rs.getString("ime"), rs.getString("prezime"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return null;
	}


}
