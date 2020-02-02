import java.io.IOException;  
import java.io.PrintWriter;  
import java.util.HashMap;
import java.util.Map;
  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
import poll.data.dao.PollDAO;
import poll.data.dao.PollDAO_MySQL;
public class CloseServlet extends HttpServlet {  
        protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {  
        
            HttpSession session=request.getSession(false);  
            
            if(session!=null){  
            
                /*Map<String, String> closePoll= new HashMap<String, String>();
                closePoll = (HashMap<String,String>)request.getAttribute("closePoll");
                int pollId = Integer.parseInt(closePoll.get("pollId"));*/
                PollDAO pollDao = new PollDAO_MySQL();

                //Map<String, String> user = (Map<String, String>)session.getAttribute("user"); 
                int userId = Integer.parseInt(request.getParameter("userId"));
                int pollId = Integer.parseInt(request.getParameter("pollId"));
                
                String success = pollDao.closePoll(pollId, userId);

                if (success=="SUCCESS"){
                    Map<String,String> message = new HashMap<String,String>();

                    message.put("txt1", "You've successfully closed this poll");
                    message.put("name", "");
                    message.put("link1", "http://localhost:8084/Progetto_1/PollByIdController?pollId="+pollId);
                    request.setAttribute("message", message); //If authenticateUser() function returnsother than SUCCESS string it will be sent to Login page again. Here the error message returned from function has been stored in a errMessage key.
                    request.getRequestDispatcher("success.jsp").include(request, response);
                }  
                else  {
                    Map<String,String> errMessage = new HashMap<String,String>();
                    errMessage.put("error","Can't delete "+pollId + " User " + userId + " " + success);
                    request.setAttribute("errMessage", errMessage); //If authenticateUser() function returnsother than SUCCESS string it will be sent to Login page again. Here the error message returned from function has been stored in a errMessage key.            
                    request.getRequestDispatcher("error.jsp").include(request, response);
                }  

            }  
        
            else{  
            
                request.getRequestDispatcher("login.html").include(request, response);  
        
            }   

    }  
        protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {  
            doGet(request, response);
        }
        
}  
