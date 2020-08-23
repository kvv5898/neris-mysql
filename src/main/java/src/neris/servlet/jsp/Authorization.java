package src.neris.servlet.jsp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.neris.log.logUser;
import src.neris.tabl.Auth;
import src.other.StoreSession;
import src.sql.Log;


@WebServlet(urlPatterns = { "/auth" })
public class Authorization extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Authorization() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Connection conn = logUser.getStoredConnection(session);
		String errorString = null;
		List<Auth> list = null;
		try {
			list = Log.Find_All(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
System.out.println("gethost: " + list.get(0).gethost());
		request.setAttribute("active_sessions", StoreSession.sizesession());
		request.setAttribute("active_users", StoreSession.sizeuser());
		request.setAttribute("errorString", errorString);
		request.setAttribute("auth", list);

		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/AuthView.jsp");
		dispatcher.forward(request, response);

	}
}