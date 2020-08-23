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
import src.neris.tabl.Received;
import src.sql.Equipment;



@WebServlet(urlPatterns = { "/equipmentreceived" })
public class EquipmentReceived extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EquipmentReceived() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
    	Connection conn = logUser.getStoredConnection(session);
        
        String errorString = null;
        List<Received> list = null;
        try {
            list = Equipment.find_er(conn);
        } catch (SQLException e) {
            e.printStackTrace();
          errorString = e.getMessage();
        }
      
        request.setAttribute("errorString", errorString);
        request.setAttribute("add", list);
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/ERView.jsp");


		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}
}