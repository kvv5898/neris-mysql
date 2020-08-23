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
import src.neris.tabl.Org;
import src.sql.Equipment;



@WebServlet(urlPatterns = { "/Editorg" })
public class Editorg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Editorg() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Editorg doGet: ");
		HttpSession session = request.getSession();
		Connection conn = logUser.getStoredConnection(session);
		List<Org> org = null;
		try {
			org=Equipment.find_org(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("org", org);
				
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Editorg.jsp");

		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Connection conn = logUser.getStoredConnection(session);
		Integer count = 0;
		String error = null;
		String Step = "Error";

		if (request.getParameter("submit") != null) {

			String org_name = (String) request.getParameter("org_name");
			System.out.println("org_name: " + org_name);
			String tel = (String) request.getParameter("tel");
			String address = (String) request.getParameter("address");
			String organization_info = (String) request.getParameter("organization_info");
			

			if (org_name.length() == 0 || tel.length() == 0 || address.length() == 0 || organization_info.length() == 0) {
				error = "Date incorrect (not null)";
			}


			else {

				Org org = new Org();
				org.setorg_name(org_name);
				org.settel(tel);
				org.setaddress(address);
				org.setorganization_info(organization_info);
				
				try {
					count = Equipment.find_org_duplicate(conn,org);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (count == 0) {
					try {
						Equipment.org_add(conn, org,logUser.getlogUser(session).getuser_name());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Step = "Editorg";
					request.setAttribute("msg", "The add operation was completed successfully!");
				}

				else {
					Step = "Editorg";
					System.out.println("duplicate count: " + count);
					error = "find_org_duplicate! org_name -" + org_name + "; tel -" + tel + "; address -" + address + ". Repeat please!";
					 
				}
			}
			List<Org> org = null;
			try {
				org=Equipment.find_org(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("org", org);
		}

		else if (request.getParameter("back") != null) {
			Step = "HomeView";
		}

		else {
			Step = "Error";
		}
		
		
		request.setAttribute("error", error);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/" + Step + ".jsp");
		dispatcher.forward(request, response);
	}
}

