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

import src.neris.log.logUser;
import src.neris.tabl.Ngroup;
import src.sql.Equipment;

@WebServlet(urlPatterns = { "/Step1" })
public class Step1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Step1() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet Step1 "+ request.getParameter("back"));
		if (request.getParameter("back") != null) {
			request.setAttribute("sn", request.getParameter("sn"));
		}
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Step1.jsp");

		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sn = (String) request.getParameter("sn");
		System.out.println("Step1 sn - " + sn);
		String Step = null;
		if (sn.length() != 0) {

			Connection conn = logUser.getStoredConnection(request.getSession());
			Integer count_find_received_sn = null;
			try {
				count_find_received_sn = Equipment.find_received_sn(conn, sn);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (count_find_received_sn == 0) {
				System.out.println("Go step2");

				List<Ngroup> gr = null;
				String errorString = null;

				try {
					gr = Equipment.find_ngroup(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					errorString = e.getMessage();
				}

				request.setAttribute("sn", sn);
				request.setAttribute("error", errorString);
				request.setAttribute("gr", gr);
				request.setAttribute("color", "green");
				Step = "Step2";

			} else {
				Step = "Step1";
				request.setAttribute("error", "Incorrect (SN - duplicate)");
			}

		} else {
			Step = "Step1";
			request.setAttribute("error", "Incorrect (SN - null)");
		}

		System.out.println("Return " + Step);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/" + Step + ".jsp");
		dispatcher.forward(request, response);

	}
}
