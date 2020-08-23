package src.ajax;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;


public class SearchAddgroups extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private final Gson gson = new Gson();

    protected void doGet(HttpServletRequest request, 
                         HttpServletResponse response) 
                                 throws ServletException, IOException
    {
    	
    	System.out.println("SearchAddgroups");
        String description = request.getParameter("description").trim();
        System.out.println("SearchAddgroups description - " + description);
        String model = request.getParameter("model").trim();
        System.out.println("SearchAddgroups model - " + model);
        String group_info = request.getParameter("group_info").trim();
        System.out.println("SearchAddgroups group_info - " + group_info);
//        HttpSession session = request.getSession();
//    	Connection conn = logUser.getStoredConnection(session);
//    	
//    	//String error = null;
//        List<Groups> gr = null;
//       
//        gr = Equipment.find_id_gr(conn, group_id)
//        
        String json = gson.toJson(group_info);
        response.setContentType("text/html; charset=windows-1251"); 
       
        response.getWriter().write(json);

        response.flushBuffer();
        
    }
}