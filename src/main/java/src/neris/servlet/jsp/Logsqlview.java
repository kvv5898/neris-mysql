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
import src.neris.tabl.Logsql;
import src.sql.Equipment;



@WebServlet(urlPatterns = { "/Logsqlview" })
public class Logsqlview extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Logsqlview() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
    	Connection conn = logUser.getStoredConnection(session);
        
        String errorString = null;
        List<Logsql> logsql = null;
       
        try {
			logsql = Equipment.find_logsql(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
      
        request.setAttribute("errorString", errorString);
        request.setAttribute("logsql", logsql);
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Logsqlview.jsp");


		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}
}