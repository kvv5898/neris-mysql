package src.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.neris.tabl.User_account;



public class User {

	public static User_account finduser(Connection conn, //
			String user_name, String password) throws SQLException {

		String sql = "Select * from user_account" //
				+ " where user_name = ? and password= ?";
		System.out.println("Find (" + user_name + ") in DB");
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, user_name);
		pstm.setString(2, password);
		ResultSet rs = pstm.executeQuery();
		User_account userfind = new User_account();
		if (rs.next()) {
			Integer user_id = rs.getInt("user_id");
			String date = rs.getString("date");
			String surname = rs.getString("surname");
			String first_Name = rs.getString("first_Name");
			String otchestvo = rs.getString("otchestvo");
			String otdel = rs.getString("otdel");
			String tel = rs.getString("tel");
			String email = rs.getString("email");
			String rule = rs.getString("rule");

			userfind.setuser_id(user_id);
			userfind.setdate(date);
			userfind.setsurname(surname);
			userfind.setfirst_name(first_Name);
			userfind.setotchestvo(otchestvo);
			userfind.setotdel(otdel);
			userfind.settel(tel);
			userfind.setemail(email);
			userfind.setuser_name(user_name);
			userfind.setpassword(password);
			userfind.setrule(rule);
			return userfind;
		}
		return null;

	}

}
