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
import src.neris.tabl.Guarantee;
import src.neris.tabl.Org;
import src.neris.tabl.Validity;
import src.sql.Equipment;

@WebServlet(urlPatterns = { "/Newguar" })
public class Newguar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Newguar() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Newguar.jsp");

		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Connection conn = logUser.getStoredConnection(session);
		System.out.println("org_name: " + request.getParameter("org_name"));
		Integer organization_id = null;
		Integer validity_id = null;
		String Step = "Error";
		
		
		if (request.getParameter("back") != null) {
			System.out.println("Back on Addguarstep1");
			Step = "Addguarstep1";
			List<Org> org = null;
			try {
				org = Equipment.find_org(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.setAttribute("org", org);
			request.setAttribute("color", "green");

		}
		
		else if (request.getParameter("submit") != null) {
		Org org = new Org(organization_id, (String) request.getParameter("org_name"),
				(String) request.getParameter("tel"), (String) request.getParameter("address"),
				(String) request.getParameter("organization_info"));

		try {
			organization_id = Equipment.org_add(conn, org,logUser.getlogUser(session).getuser_name());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		Validity validity = new Validity(validity_id, (String) request.getParameter("date"),
				(Integer) Integer.parseInt((String) request.getParameter("month")),
				(String) request.getParameter("org_name"), (String) request.getParameter("contract"),
				(String) request.getParameter("validity_info"));

		try {
			validity_id = Equipment.validity_add(conn, validity,logUser.getlogUser(session).getuser_name());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("organization_id: " + organization_id);
		System.out.println("validity_id: " + validity_id);

		if (validity_id == null) {
			request.setAttribute("error", "validity not add");
			Step = "Newguar";
		} else if (organization_id == null) {
			request.setAttribute("error", "organization not add");
			Step = "Newguar";
		}

		else {

			try {
				Equipment.guarantee_add(conn, organization_id, validity_id,logUser.getlogUser(session).getuser_name());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			List<Guarantee> guar = null;
			String error_guar = null;

			try {
				guar = Equipment.find_guar(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				error_guar = e.getMessage();
			}
			Step = "Step3";
			request.setAttribute("guar", guar);
			request.setAttribute("errorguar", error_guar);
		}
		}
		else {
			Step = "Error";

		}
		
		request.setAttribute("sn", request.getParameter("sn"));
		request.setAttribute("group_id", request.getParameter("group_id"));
		request.setAttribute("group_info", request.getParameter("group_info"));
		request.setAttribute("sizegr", request.getParameter("sizegr"));
		
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/" + Step + ".jsp");
		dispatcher.forward(request, response);
	}
}
