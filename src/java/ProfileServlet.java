import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
public class ProfileServlet extends HttpServlet {  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                      throws ServletException, IOException {  

        
        HttpSession session=request.getSession(false);  
        if(session!=null){  
            request.getRequestDispatcher("profile.jsp").include(request, response);  

        }  
        else{  
            request.getRequestDispatcher("login.html").include(request, response);  
        }  
    }  
}  