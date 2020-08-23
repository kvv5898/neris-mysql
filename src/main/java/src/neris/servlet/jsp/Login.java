package src.neris.servlet.jsp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.neris.conn.ConnMySQL;
import src.neris.log.logUser;
import src.neris.tabl.User_account;
import src.other.StoreSession;
import src.other.WebUtils;
import src.sql.User;
import src.sql.Log;


@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Login servlet is working, request in LoginView.jsp");

		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/LoginView.jsp");

		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Start conn: ");
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60 * 10);

		Connection conn = null;

		try {
			System.out.println("Start conn: ");
			conn = ConnMySQL.conni();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String errorMessage = null;
		String user_name = request.getParameter("userName");
		String password = request.getParameter("password");
		Map<String, String> map = new HashMap<String, String>();
		map = WebUtils.getHeadersInfo(request);
		String host = map.get("host");
		System.out.println("host:" + host);
		String getway = request.getHeader("VIA"); // Gateway
		System.out.println("getWay: " + getway);

		String ip = null;
		ip = request.getHeader("X-FORWARDED-FOR"); // proxy
		if (ip == null) {
			ip = request.getRemoteAddr();
		}

		System.out.println("IP Address: " + ip);

		User_account userAccount = null;
		try {
			userAccount = User.finduser(conn, user_name, password);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (userAccount == null) {
			errorMessage = "Invalid user_name or Password";
			try {
				Log.Auth(conn, ip, host, getway, user_name, password, session.getId(), "fault");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("errorMessage", errorMessage);

			RequestDispatcher dispatcher //
					= this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/LoginView.jsp");

			dispatcher.forward(request, response);
			return;
		} else if (userAccount.getuser_name() == user_name) {

			if (StoreSession.getuser().contains(user_name)) {
				errorMessage = "user online";
				request.setAttribute("errorMessage", errorMessage);

				RequestDispatcher dispatcher //
						= this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/LoginView.jsp");

				dispatcher.forward(request, response);
				return;
			}

			else {
				StoreSession.adduser(user_name, session.getId());
				logUser.storelogUser(session, userAccount);
				logUser.storeConnection(session, conn);
				try {
					Log.Auth(conn, ip, host, getway, user_name, password, session.getId(), "ok");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("Request in /home");
				response.sendRedirect(request.getContextPath() + "/home");
			}
		}

	}

}