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
import src.neris.tabl.Validity;
import src.sql.Equipment;



@WebServlet(urlPatterns = { "/Editvalidity" })
public class Editvalidity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Editvalidity() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Editvalidity doGet: ");
		HttpSession session = request.getSession();
		Connection conn = logUser.getStoredConnection(session);
		List<Validity> validity = null;
		try {
			validity=Equipment.find_validity(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Org> org = null;
		try {
			org=Equipment.find_org(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("org",org);
		
		request.setAttribute("validity", validity);
				
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Editvalidity.jsp");

		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Editvalidity doPost: ");
		HttpSession session = request.getSession();
		Connection conn = logUser.getStoredConnection(session);
		Integer count = 0;
		String error = null;
		String Step = "Error";
		System.out.println("Editvalidity doPost getParameter(submit): " + request.getParameter("submit"));
		if (request.getParameter("submit") != null) {
			System.out.println("Editvalidity doPost submit: ");
			String date = (String) request.getParameter("date");
			Integer month = Integer.parseInt(request.getParameter("month"));
			System.out.println("Editvalidity doPost org_name: " + request.getParameter("org"));
			String org_name = (String) request.getParameter("org");
			String contract = (String) request.getParameter("contract");
			String validity_info = (String) request.getParameter("validity_info");
			System.out.println("Editvalidity doPost validity_info: " + request.getParameter("validity_info"));
			

			if (date.length() == 0 || month == 0 || org_name.length() == 0 || contract.length() == 0 || validity_info.length() == 0) {
				System.out.println("Editvalidity doPost error: ");
				error = "Date incorrect (not null)";
			}


			else {

				Validity validity = new Validity();
				
				validity.setdate(date);
				validity.setmonth(month);
				validity.setorg_name(org_name);
				validity.setcontract(contract);
				validity.setvalidity_info(validity_info);
				
				System.out.println("Editvalidity doPost find_validity_duplicate: ");
				try {
					count = Equipment.find_validity_duplicate(conn, validity);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (count == 0) {
					
					System.out.println("Editvalidity doPost count == 0: ");
					try {
						Equipment.validity_add(conn, validity, logUser.getlogUser(session).getuser_name());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Step = "Editvalidity";
					request.setAttribute("msg", "The add operation was completed successfully!");
				}

				else {
					System.out.println("Editvalidity doPost duplicate count: ");
					Step = "Editvalidity";
					System.out.println("duplicate count: " + count);
					error = "find_validity_duplicate: org_name -" + org_name + "; contract -" + contract + ". Repeat please!";
					 
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
		
		List<Validity> validity = null;
		try {
			validity=Equipment.find_validity(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("validity", validity);
		request.setAttribute("error", error);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/" + Step + ".jsp");
		dispatcher.forward(request, response);
	}
}

