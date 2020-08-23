package src.neris.servlet.jsp;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet(urlPatterns = { "/Error" })
public class Error extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Error() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("doGet Error ");

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Error.jsp");
		dispatcher.forward(request, response);
	}
		
		

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("doPost Error ");
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Error.jsp");
		dispatcher.forward(request, response);
	}
}

