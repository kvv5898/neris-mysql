package src.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.neris.tabl.Auth;
import src.other.date_time;



public class Log {

	public static void Auth(Connection conn, //
			String ip, String host, String getway, String user_name, String password, String session, String status)
			throws SQLException {
		String sql = "Insert into auth(date_start, ip, host, getway, user_in, pass_in, session, status) values (?,?,?,?,?,?,?,?)";

		PreparedStatement add = conn.prepareStatement(sql);

		add.setString(1, date_time.date());
		add.setString(2, ip);
		add.setString(3, host);
		add.setString(4, getway);
		add.setString(5, user_name);
		add.setString(6, password);
		add.setString(7, session);
		add.setString(8, status);

		add.executeUpdate();

	}

	public static List<Auth> Find_All(Connection conn) throws SQLException {
		String sql = "Select * from auth";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<Auth> list = new ArrayList<Auth>();
		while (rs.next()) {

			Integer id = rs.getInt("id");
			String date_start = rs.getString("date_start");
			String date_end = rs.getString("date_end");
			String ip = rs.getString("ip");
			String host = rs.getString("host");
			String getway = rs.getString("getway");
			String user_in = rs.getString("user_in");
			String pass_in = rs.getString("pass_in");
			String session = rs.getString("session");
			String status = rs.getString("status");

			Auth us = new Auth(id, date_start, date_end, ip, host, getway, user_in, pass_in, session, status);

			us.setid(id);
			us.setdate_start(date_start);
			us.setdate_end(date_end);
			us.setip(ip);
			us.sethost(host);
			us.setgetway(getway);
			us.setuser_in(user_in);
			us.setpass_in(pass_in);
			us.setsession(session);
			us.setstatus(status);
			list.add(us);
		}
		return list;

	}

	public static String session(String user, Connection conn) throws SQLException {

		System.out.println("Search session in Auth DB for user: " + user);

		String sql = "SELECT session FROM auth WHERE user_in =? AND status = ? ORDER BY id DESC LIMIT 1";

		PreparedStatement s = conn.prepareStatement(sql);
		s.setString(1, user);
		s.setString(2, "ok");
		s.executeQuery();

		ResultSet rs = s.executeQuery();

		String session = null;
		while (rs.next()) {
			session = rs.getString("session");
		}
		return session;
	}

	public static void date_end(Connection conn, String session_id) throws SQLException {
		String sql = "Update auth set date_end=? where session=? ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		System.out.println("Entry date_end for session_id: - " + session_id);
		pstm.setString(1, date_time.date());
		pstm.setString(2, session_id);

		pstm.executeUpdate();
	}

}