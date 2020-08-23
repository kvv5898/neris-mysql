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
import src.sql.Equipment;

@WebServlet(urlPatterns = { "/Addgroups" })
public class Addgroups extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Addgroups() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Addgroups doGet: ");
		String Step = "Error";
		if ( request.getParameter("sn") != null) {
			Step = "Addgroups";
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
		String group_add = null;
		Integer count = 0;
		String error = null;
		String Step = "Error";

		if (request.getParameter("submit") != null) {

			String description = (String) request.getParameter("description");
			System.out.println("description: " + description);
			String model = (String) request.getParameter("model");
			String group_info = (String) request.getParameter("group_info");
			Integer group_id = null;

			if (description.length() == 0) {
				error = "Description incorrect (not null)";
				request.setAttribute("desccolor", "red");

			}

			else if (model.length() == 0) {
				error = "model incorrect (not null)";
				request.setAttribute("modelcolor", "red");
				request.setAttribute("desccolor", "green");
			}

			else if (group_info.length() == 0) {
				error = "group_info incorrect (not null)";
				request.setAttribute("group_infocolor", "red");
				request.setAttribute("desccolor", "green");
				request.setAttribute("modelcolor", "green");
			}

			else {

				Ngroup groups = new Ngroup(group_id, description, model, group_info);

				try {
					count = Equipment.find_ngroup_duplicate(conn, groups);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (count == 0) {
					group_add = "group_add";
					try {
						Equipment.group_add(conn, groups,logUser.getlogUser(session).getuser_name());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}

				else {
					Step = "Addgroups";
					System.out.println("duplicate count: " + count);
					error = "find_groups_duplicate! description -" + description + "; model -" + model + "; group_info -" + group_info + ". Repeat please!";
					 
				}
			}
			if (group_info.length() == 0 || model.length() == 0 || group_info.length() == 0 || count != 0) {
				for (int i = 0; i < Ngroup.class.getDeclaredFields().length; i++) {
					String name_column = Ngroup.class.getDeclaredFields()[i].getName();
					if (name_column == "description" && description.length() != 0 && count == 0) {
						request.setAttribute("repeatdesc", description);
					} else if (name_column == "model" && model.length() != 0 && count == 0) {
						request.setAttribute("repeatmodel", model);
					} else if (name_column == "group_info" && group_info.length() != 0 && count == 0) {
						request.setAttribute("repeatgroup_info", group_info);
					} else {
						List<String> find = null;
						try {
							find = Equipment.find_ngroup_name_column(conn, name_column);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Step = "Addgroups";
						request.setAttribute(name_column, find);
					}
				}
			}
		}

		if (request.getParameter("submit") == null || group_add == "group_add" ) {
			List<Ngroup> gr = null;
			try {
				gr = Equipment.find_ngroup(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				error = e.getMessage();
			}
			Step = "Step2";
			request.setAttribute("gr", gr);
			request.setAttribute("color", "green");

		}

		request.setAttribute("error", error);
		request.setAttribute("sn", request.getParameter("sn"));
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/" + Step + ".jsp");
		dispatcher.forward(request, response);
	}
}
