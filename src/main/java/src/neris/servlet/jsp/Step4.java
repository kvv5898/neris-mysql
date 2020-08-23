package src.neris.servlet.jsp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = { "/Step4" })
public class Step4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Step4() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("doGet Step4 ");
		String Step = "Error";
		if ( request.getParameter("sn") == null) {
			Step = "Step1";
		}
				else if (request.getParameter("group_id") == null) {
					Step = "Step2";
				}
				else if (request.getParameter("guarantee_id") == null) {
					Step = "Step3";
				}
		
		else {
			Step = "Step4";
		}
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/"+Step+".jsp");

		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
	}
}
