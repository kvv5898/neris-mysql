package src.neris.servlet.jsp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
import src.neris.tabl.Received;
import src.sql.Equipment;



@WebServlet(urlPatterns = { "/Sngroup" })
public class Sngroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Sngroup() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
    	Connection conn = logUser.getStoredConnection(session);
    	
    	List<Guarantee> guarantee = null;
    	try {
			guarantee = Equipment.find_guar(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	List<Ngroup> groups = null;
    	try {
			groups=Equipment.find_ngroup(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	List<Received> list = null;
        try {
            list = Equipment.find_er(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
      
        
        request.setAttribute("add", list);
    	request.setAttribute("guarantee", guarantee);
    	request.setAttribute("groups", groups);
    	
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Sngroup.jsp");
		dispatcher.forward(request, response);

	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
    	Connection conn = logUser.getStoredConnection(session);
    	
		String Step = "Error";
		
		ArrayList<String> Error = new ArrayList<String>();
		ArrayList<String> Msg = new ArrayList<String>();
		
		
		
		if (request.getParameter("submit") != null && request.getParameter("sn") != null && request.getParameter("groups") != null && request.getParameter("guarantee") != null ) {
			Step = "Sngroup";
			Integer count = 0;
			for (String sn: request.getParameter("sn").split("\\,")) {
				  try {
					count=Equipment.find_received_sn(conn, sn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  if (count==0) {
					  
					 Received add_received =  new Received();
					 add_received.setsn(sn);
					 add_received.setgroup_id(Integer.parseInt(request.getParameter("groups")));
					 add_received.setguarantee_id(Integer.parseInt(request.getParameter("guarantee")));
					 
					try {
						Equipment.add_received(conn, add_received,logUser.getlogUser(session).getuser_name());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Msg.add(sn);
				}
				  else {
					Error.add(sn);
				}
				}
			
			
			List<Guarantee> guarantee = null;
	    	try {
				guarantee = Equipment.find_guar(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	List<Ngroup> groups = null;
	    	try {
				groups=Equipment.find_ngroup(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	List<Received> list = null;
	        try {
	            list = Equipment.find_er(conn);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	      
	        
	        request.setAttribute("add", list);
	    	request.setAttribute("error", Error);
	    	request.setAttribute("msg", Msg);
	    	request.setAttribute("guarantee", guarantee);
	    	request.setAttribute("groups", groups);
			
			
		}
		else {
			
		}
		
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/" + Step + ".jsp");
		dispatcher.forward(request, response);
	}
}

