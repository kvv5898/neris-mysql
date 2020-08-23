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
import src.neris.tabl.Validity;
import src.sql.Equipment;

@WebServlet(urlPatterns = { "/Newvalidity" })
public class Newvalidity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Newvalidity() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Newvalidity.jsp");

		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Connection conn = logUser.getStoredConnection(session);
		String Step = "Error";
		
		if (request.getParameter("back") !=null) {
			System.out.println("Back on Newvalidity");
			Step = "Addguarstep2";

		}
		else if (request.getParameter("submit") !=null) {
			Integer validity_id = null;
			Validity validity = new Validity();
			validity.setdate(request.getParameter("date"));
			validity.setmonth(Integer.parseInt(request.getParameter("month")));
			validity.setorg_name(request.getParameter("org_name"));
			validity.setcontract(request.getParameter("contract"));
			validity.setvalidity_info(request.getParameter("validity_info"));
			
			try {
				validity_id=Equipment.validity_add(conn, validity,logUser.getlogUser(session).getuser_name());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (validity_id == null) {
				request.setAttribute("error", "validity not add");
				Step = "Newvalidity";
			}
			
			else {
				Step = "Addguarstep2";
				request.setAttribute("msg", request.getParameter("contract") + " for " + request.getParameter("org_name") + " added successfully! ");
			}
			
		}
	else {
		
	}

		List<Validity> validity = null;
		
		try {
			validity = Equipment.find_validity_for_org(conn, request.getParameter("org_name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    request.setAttribute("validity", validity);
	    request.setAttribute("color", "green");
		request.setAttribute("sn", request.getParameter("sn"));
		request.setAttribute("group_id", request.getParameter("group_id"));
		request.setAttribute("group_info", request.getParameter("group_info"));
		request.setAttribute("org_name", request.getParameter("org_name"));
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/" + Step + ".jsp");
		dispatcher.forward(request, response);
	}
}
