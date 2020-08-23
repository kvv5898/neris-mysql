package src.ajax;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.*;

import src.neris.log.logUser;
import src.neris.tabl.Validity;
import src.sql.Equipment;

public class SelectValidity extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private final Gson gson = new Gson();

    protected void doGet(HttpServletRequest request, 
                         HttpServletResponse response) 
                                 throws ServletException, IOException
    {
    	
    	System.out.println("SelectValidity");
        String org_info = request.getParameter("org").trim();
        System.out.println("org_info - " + org_info);
        
        HttpSession session = request.getSession();
    	Connection conn = logUser.getStoredConnection(session);
    	
    	//String error = null;
        List<Validity> validity = null;
        try {
        	validity = Equipment.find_validity_for_org(conn, org_info);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
//        System.out.println(validity.get(0).getvalidity_info());
        
        String json = gson.toJson(validity);
//        response.setContentType("text/html; charset=windows-1251"); 
       
        response.getWriter().write(json);

        response.flushBuffer();
        
    }
}