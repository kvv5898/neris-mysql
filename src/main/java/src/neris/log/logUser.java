package src.neris.log;

import java.sql.Connection;

import javax.servlet.http.HttpSession;

import src.neris.tabl.User_account;



public class logUser {

	public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";

	public static void storeConnection(HttpSession session, Connection conn) {
		System.out.println("Write request:" + session);
		session.setAttribute(ATT_NAME_CONNECTION, conn);
		System.out.println("Write conn:" + session.getAttribute(ATT_NAME_CONNECTION));

	}

	public static Connection getStoredConnection(HttpSession session) {
		System.out.println("Read reques - " + session);
		Connection conn = (Connection) session.getAttribute(ATT_NAME_CONNECTION);
		System.out.println("Read conn - " + session.getAttribute(ATT_NAME_CONNECTION));
		return conn;
	}

	public static void storelogUser(HttpSession session, User_account logUser) {
		System.out.println("Write logUser for " + logUser.getuser_name() + "(JAVA)");
		session.setAttribute("logUser", logUser);
	}

	public static User_account getlogUser(HttpSession session) {
		User_account logUser = (User_account) session.getAttribute("logUser");
		if (logUser == null) {
			System.out.println("No User Info");
			return null;
		} else {
			System.out.println("session.getId: - " + session.getId());
			System.out.println("To read logUser for " + logUser.getuser_name());
		}

		return logUser;
	}

}