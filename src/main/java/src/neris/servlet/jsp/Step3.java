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
import src.neris.tabl.Ngroup;
import src.neris.tabl.Guarantee;
import src.neris.tabl.Org;
import src.neris.tabl.Received;
import src.sql.Equipment;

@WebServlet(urlPatterns = { "/Step3" })
public class Step3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Step3() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet Step3: ");
		String Step = "Error";
		if ( request.getParameter("sn") == null) {
			Step = "Step1";
		}
				else if (request.getParameter("group_id") == null) {
					Step = "Step2";
				}
		else {
			Step = "Step3";
		}
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/"+Step+".jsp");

		dispatcher.forward(request, response);
	}
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Connection conn = logUser.getStoredConnection(session);
		String Step = "Error";
		String error = null;
		System.out.println("doPost Step3 Addguarstep1:" + request.getParameter("Addguarstep1"));
		if (request.getParameter("Addguarstep1") != null) {

			List<Org> org = null;
			try {
				org = Equipment.find_org(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Step = "Addguarstep1";
			request.setAttribute("org", org);
			request.setAttribute("color", "green");

		}

		else if (request.getParameter("submit") != null) {

			System.out.println("doGet Step3:");
			String sn = (String) request.getParameter("sn");
			String group_idstr = (String) request.getParameter("group_id");
			Integer group_id = Integer.parseInt(group_idstr);
			String guarantee_idstr = (String) request.getParameter("guar");
			
			
			if (sn.length() != 0 & group_id != null & guarantee_idstr != null) {
			
			Integer guarantee_id = Integer.parseInt(guarantee_idstr);
			Integer count_find_received_sn = null;
			try {
				count_find_received_sn = Equipment.find_received_sn(conn, sn);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("doGet Step3 (count_find_received_sn):" + count_find_received_sn);
			if (count_find_received_sn == 0) {
				
				Received add_received = new Received();
				add_received.setsn(sn);
				add_received.setgroup_id(group_id);
				add_received.setguarantee_id(guarantee_id);
				System.out.println("doGet Step3 add_received");
				try {
					Equipment.add_received(conn, add_received,logUser.getlogUser(session).getuser_name());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					error = e.getMessage();
				}
				Step = "Step4";
				request.setAttribute("msg", "The operation was completed successfully!)");

			} else {
				request.setAttribute("error", "Incorrect (SN - duplicate)");
				Step = "Step3";
			}
		}
		  
			else {
				System.out.println("doGet Step3 group_info:" + request.getParameter("guar"));
				List<Guarantee> guar = null;
							

				try {
					guar = Equipment.find_guar(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				request.setAttribute("guar", guar);
				request.setAttribute("color", "red");
				Step = "Step3";
				error = "Incorrect (Guarantee - null)";
			}
						
		}

		
		else if (request.getParameter("cancel") != null) {
			Step = "Step2";
			List<Ngroup> gr = null;

			try {
				gr = Equipment.find_ngroup(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.setAttribute("gr", gr);
		}
		
		else {
			Step = "Error";
		}

		request.setAttribute("group_id", (String) request.getParameter("group_id"));
		request.setAttribute("group_info", (String) request.getParameter("group_info"));
		request.setAttribute("sizegr", ((String) request.getParameter("group_info")).length() + 3);
		request.setAttribute("error", error);
		request.setAttribute("sn", request.getParameter("sn"));
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/" + Step + ".jsp");
		dispatcher.forward(request, response);
	}
}
