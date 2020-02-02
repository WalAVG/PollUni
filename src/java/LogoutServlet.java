import java.io.IOException;  
import java.io.PrintWriter;  
import java.util.HashMap;
import java.util.Map;
  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
public class LogoutServlet extends HttpServlet {  
        protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {  
 
            Map<String, String> errMessage = new HashMap<String, String>(); 
            errMessage.put("txt1", "You are successfully logged out!");
            errMessage.put("error", "");

            HttpSession session=request.getSession();  

            
            request.getRequestDispatcher("/login.html").forward(request, response);
            session.invalidate();  

    }  
}  
