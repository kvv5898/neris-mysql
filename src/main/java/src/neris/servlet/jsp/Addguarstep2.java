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

@WebServlet(urlPatterns = { "/Addguarstep2" })
public class Addguarstep2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Addguarstep2() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String Step = "Error";
		if ( request.getParameter("sn") != null) {
			Step = "Addguarstep2";
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

		String Step = "Error";
		String error = null;
		String color = null;
		Integer count = null;
		Integer validity_id = null;
		String Msg = null;
		List<Guarantee> guar = null;

		HttpSession session = request.getSession();
		Connection conn = logUser.getStoredConnection(session);

		String contract = request.getParameter("validity");
		String org_name = request.getParameter("org_name");

		System.out.println("doPost Addguarstep2 (back): " + request.getParameter("back"));

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

			if (contract.length() != 0) {
				System.out.println("validity.contract: " + contract);

				try {
					validity_id = Equipment.find_validity_for_contract(conn, contract);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (validity_id == null) {
					Step = "Newvalidity";
					request.setAttribute("contract", contract);
					color = "green";
				} else {

					List<Org> org = null;
					try {
						org = Equipment.find_name_org(conn, org_name);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("org.size()	: " + org.size());

					if (org.size() == 1) {

						try {
							count = Equipment.find_guar_orgid_valid(conn, (org.get(0).getorganization_id()),
									validity_id);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if (count == 0) {

							String validity_orgname = null;
							try {
								validity_orgname = Equipment.find_validity_for_contract_return_orgname(conn, contract);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							System.out.println("validity_orgname: " + validity_orgname);
							System.out.println("org_name: " + org_name);

							if (validity_orgname.equals(org_name)) {
								Step = "Step3";
								Msg = "guarantee_add successfully";
								try {
									Equipment.guarantee_add(conn, (org.get(0).getorganization_id()), validity_id,logUser.getlogUser(session).getuser_name());
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

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
								Step = "Addguarstep2";
								error = contract + " cannot be added " + org_name + " as it relates to "
										+ validity_orgname + " Operation canceled.";
								List<Validity> validity = null;
								try {
									validity = Equipment.find_validity_for_org(conn, org_name);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								request.setAttribute("validity", validity);

							}

						}

						else {
							Step = "Addguarstep2";
							error = org_name + " and " + contract + " re-entry! Operation canceled.";
							List<Validity> validity = null;
							try {
								validity = Equipment.find_validity_for_org(conn, org_name);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							request.setAttribute("validity", validity);
						}

					}

					else {
						Step = "Addguarstep2";
						error = "Addguarstep2 org.size()  " + org.size() + " Need to check the Data Base!";
						List<Validity> validity = null;
						try {
							validity = Equipment.find_validity_for_org(conn, org_name);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						request.setAttribute("validity", validity);
					}

				}
			}

			else {
				System.out.println("doPost Addguarstep2");

				request.setAttribute("contract", contract);
				Step = "Addguarstep2";
				error = "incorect data";
				color = "red";
			}

		}

		else {
			Step = "Error";

		}

		request.setAttribute("sn", request.getParameter("sn"));
		request.setAttribute("group_id", request.getParameter("group_id"));
		request.setAttribute("group_info", request.getParameter("group_info"));
		request.setAttribute("org_name", org_name);
		request.setAttribute("contract", contract);
		request.setAttribute("error", error);
		request.setAttribute("color", color);
		request.setAttribute("msg", Msg);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/" + Step + ".jsp");
		dispatcher.forward(request, response);
	}
}
