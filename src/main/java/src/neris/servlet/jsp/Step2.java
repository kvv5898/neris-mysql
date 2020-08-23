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
import src.sql.Equipment;

@WebServlet(urlPatterns = { "/Step2" })
public class Step2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Step2() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet Step2: ");
		String Step = "Error";
		if ( request.getParameter("sn") != null) {
			Step = "Step2";
		}
		else {
			Step = "Step1";
		}
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/"+Step+".jsp");

		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Connection conn = logUser.getStoredConnection(session);
		String sn = (String) request.getParameter("sn");
		System.out.println("sn - " + sn);
		String Step = null;
		System.out.println("doPost Step2 ");
		if (request.getParameter("Addgroups") != null) {

			for (int i = 0; i < Ngroup.class.getDeclaredFields().length; i++) {
				String name_column = Ngroup.class.getDeclaredFields()[i].getName();

				List<String> find = null;
				try {
					find = Equipment.find_ngroup_name_column(conn, name_column);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute(name_column, find);
			}

			request.setAttribute("sn", request.getParameter("sn"));

			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Addgroups.jsp");
			dispatcher.forward(request, response);
		} 
		
		
		else if (request.getParameter("submit") != null) {
			System.out.println("doPost Step2 submit: ");
						
			System.out.println("gr: " + request.getParameter("gr"));
			String GRstr = (String) request.getParameter("gr");
			List<Ngroup> gr = null;
			Integer group_id = null;
			String group_info = null;
			Integer sizegr = null;
			String error_gr = null;
			List<Guarantee> guar = null;

			if (sn.length() != 0 & GRstr != null) {
				try {
					group_id = Integer.parseInt(GRstr);
				} catch (Exception e) {
				}
				System.out.println("group_id - /" + group_id + "/");

				String error_guar = null;
				try {
					gr = Equipment.find_id_gr(conn, group_id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					error_gr = e.getMessage();
				}

				group_info = gr.get(0).getdescription() + " / " + gr.get(0).getmodel() + " / "
						+ gr.get(0).getgroup_info();

				try {
					guar = Equipment.find_guar(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					error_guar = e.getMessage();
				}
				sizegr = group_info.length() + 3;
				System.out.println("group_info: " + group_info);
				System.out.println("size group_info: " + sizegr);
				request.setAttribute("errorgr", error_gr);
				request.setAttribute("group_id", group_id);
				request.setAttribute("group_info", group_info);
				request.setAttribute("sizegr", sizegr);
				request.setAttribute("guar", guar);
				request.setAttribute("errorguar", error_guar);
				request.setAttribute("color", "green");
				Step = "Step3";
			}

			else {
				try {
					gr = Equipment.find_ngroup(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					error_gr = e.getMessage();
				}
				request.setAttribute("gr", gr);
				request.setAttribute("error", "Incorrect (group_info - null)");
				request.setAttribute("color", "red");
				Step = "Step2";
			}
					}
		else {
			Step = "Error";
		}
		
		
		request.setAttribute("sn", sn);
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/WEB-INF/jsp/" + Step + ".jsp");
		dispatcher.forward(request, response);
	}
}
