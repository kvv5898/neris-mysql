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

@WebServlet(urlPatterns = { "/Addguarstep1" })
public class Addguarstep1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Addguarstep1() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("doGet Addguarstep1: " + request.getParameter("group_id"));
		
		String Step = "Error";
		if (request.getParameter("sn") != null) {
			Step = "Addguarstep1";
		} else {
			Step = "Step1";
		}
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/" + Step + ".jsp");

		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Step = "Error";
		String error = null;
		String color = null;

		HttpSession session = request.getSession();
		Connection conn = logUser.getStoredConnection(session);
		String org_name = request.getParameter("org");
		System.out.println("doPost Addguarstep1: " + request.getParameter("back"));
		if (request.getParameter("back") != null) {
			System.out.println("Back on Step3");
			Step = "Step3";
			List<Guarantee> guar = null;

			try {
				guar = Equipment.find_guar(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				error = e.getMessage();
			}

			request.setAttribute("guar", guar);

		}

		else {

			if (org_name.length() != 0) {
				System.out.println("doPost Addguarstep1: " + org_name);
				Step = "Error";
				List<Org> org = null;
				try {
					org = Equipment.find_name_org(conn, org_name);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (org == null) {
					Step = "Newguar";
					request.setAttribute("org_name", org_name);
					color = "green";
				} else {

					List<Validity> validity = null;
					try {
						validity = Equipment.find_validity_for_org(conn, org_name);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Step = "Addguarstep2";
					request.setAttribute("org_organization_id", org.get(0).getorganization_id());
					request.setAttribute("org_info",
							org.get(0).getorg_name() + org.get(0).gettel() + org.get(0).getorganization_info());
					request.setAttribute("validity", validity);
					color = "green";
				}
			}

			else {
				System.out.println("Addguarstep1: ");
				List<Org> org = null;
				try {
					org = Equipment.find_org(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				request.setAttribute("org", org);
				Step = "Addguarstep1";
				error = "incorect data";
				color = "red";
			}
		}

		request.setAttribute("sn", request.getParameter("sn"));
		request.setAttribute("group_id", request.getParameter("group_id"));
		request.setAttribute("group_info", request.getParameter("group_info"));
		request.setAttribute("sizegr", request.getParameter("sizegr"));
		request.setAttribute("org_name", org_name);
		request.setAttribute("error", error);
		request.setAttribute("color", color);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/" + Step + ".jsp");
		dispatcher.forward(request, response);
	}
}
