package src.neris.servlet.jsp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.neris.log.logUser;
import src.neris.tabl.History;
import src.sql.Equipment;

@WebServlet(urlPatterns = { "/StatusEdit" })
public class StatusEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StatusEdit() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Connection conn = logUser.getStoredConnection(session);

		List<String> find_er = null;

		try {
			find_er = Equipment.find_sn_er(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<String> status = new ArrayList<String>();

		try {
			status = Equipment.find_history_status(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<String> org = new ArrayList<String>();

		try {
			org = Equipment.find_org_name_column(conn, "org_name");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<History> history = null;
		
		try {
			history = Equipment.find_history(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("org", org);
		request.setAttribute("status", status);
		request.setAttribute("sn", find_er);
		request.setAttribute("history", history);

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/StatusEdit.jsp");

		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Connection conn = logUser.getStoredConnection(session);

		String Step = "Error";
		String Error = null;
		String Msg = null;

		if (request.getParameter("submit") != null && request.getParameter("sn") != null
				&& request.getParameter("status") != null && request.getParameter("org") != null) {
			Step = "StatusEdit";
			Integer count = 0;

			History find_history_duplicate = new History();
			find_history_duplicate.setsn(request.getParameter("sn"));
			find_history_duplicate.setstatus(request.getParameter("status"));
			find_history_duplicate.setorganization(request.getParameter("org"));

			try {
				count = Equipment.find_history_duplicate(conn, find_history_duplicate);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (count == 0) {

				try {
					Equipment.add_history(conn, find_history_duplicate);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Msg = "SN " + (request.getParameter("sn")) + " status changed successfully!";
			} else {
				Error = request.getParameter("sn") + " - Duplicate! ";
		}
			
			List<String> find_er = null;

			try {
				find_er = Equipment.find_sn_er(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			List<String> status = new ArrayList<String>();

			try {
				status = Equipment.find_history_status(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			List<String> org = new ArrayList<String>();

			try {
				org = Equipment.find_org_name_column(conn, "org_name");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			List<History> history = null;
			try {
				history = Equipment.find_history(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("org", org);
			request.setAttribute("status", status);
			request.setAttribute("sn", find_er);
			request.setAttribute("history", history);
			
			request.setAttribute("error", Error);
			request.setAttribute("msg", Msg);

			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/" + Step + ".jsp");
			dispatcher.forward(request, response);
			
		}
		
		else {
			Step = "Error";
		}

		
	}

}