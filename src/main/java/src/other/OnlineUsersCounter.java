package src.other;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import src.neris.log.logUser;
import src.neris.tabl.User_account;
import src.sql.Log;

@WebListener
public class OnlineUsersCounter implements HttpSessionListener {

	private static int numberOfUsersOnline;

	public OnlineUsersCounter() {
		numberOfUsersOnline = 0;
	}

	public static int getNumberOfUsersOnline() {
		return numberOfUsersOnline;
	}

	public void sessionCreated(HttpSessionEvent event) {

		System.out.println("Session created by Id : " + event.getSession().getId());
		synchronized (this) {
			String ssid = event.getSession().getId();
			StoreSession.addsessionid(ssid);
			numberOfUsersOnline++;
		}

	}

	public void sessionDestroyed(HttpSessionEvent event) {
		Connection conn = logUser.getStoredConnection(event.getSession());
		try {
			Log.date_end(conn, event.getSession().getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User_account logUser = (User_account) event.getSession().getAttribute("logUser");
		String user = logUser.getuser_name();
		System.out.println("Session destroyed by User : " + user);
		StoreSession.deleteuser(user);

		synchronized (this) {

			numberOfUsersOnline--;
		}

	}

}